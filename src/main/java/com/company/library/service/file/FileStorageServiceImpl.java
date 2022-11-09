package com.company.library.service.file;

import com.company.library.configs.ApplicationContextHolder;
import com.company.library.dao.AbstractDAO;
import com.company.library.dao.UploadDAO;
import com.company.library.domains.Uploads;
import com.company.library.dto.UploadsDTO;
import com.company.library.exceptions.NotFoundException;
import lombok.NonNull;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

public class FileStorageServiceImpl extends AbstractDAO<UploadDAO> implements FileStorageService {
    private static FileStorageServiceImpl fileStorageService;

    private Path rootPath = Paths.get("/home/jahongir/Documents/upload");

    public FileStorageServiceImpl() {
        super(ApplicationContextHolder.getBean(UploadDAO.class));
    }


    public static FileStorageServiceImpl getInstance() {

        if (fileStorageService == null) {
            fileStorageService = new FileStorageServiceImpl();
        }
        return fileStorageService;
    }

    @Override
    public Uploads upload(Part part) {
        try {
            String contentType = part.getContentType();
            String originalFileName = part.getSubmittedFileName();

            originalFileName = originalFileName.replaceAll(",", "_");
            long size = part.getSize();

            String[] split = originalFileName.split("\\.");

            String fileNameExtension = split[split.length - 1];

            String generatedName = System.currentTimeMillis() + "." + fileNameExtension;

            String path = "/upload/" + generatedName;

            Uploads uploads = Uploads.builder()
                    .contentType(contentType)
                    .originalName(originalFileName)
                    .generatedName(generatedName)
                    .size(size)
                    .path(path)
                    .build();

            Path uploadPath = rootPath.resolve(generatedName);

            dao.save(uploads);

            Files.copy(part.getInputStream(), uploadPath, StandardCopyOption.REPLACE_EXISTING);
            return uploads;

        } catch (IOException e) {
            throw new RuntimeException("Something wrong try again");
        }

    }

    @Override
    public File download(@NonNull String fileName) {
        return null;
    }

    @Override
    public UploadsDTO get(@NonNull String fileName) {
        return null;
    }

    @Override
    public List<Uploads> getAll() {
        return null;
    }

    @Override
    public UploadsDTO getByPath(String path) throws NotFoundException {

        Optional<Uploads> byPath = dao.getByPath(path);

        if (byPath.isEmpty()) {
            throw new NotFoundException("File not found");
        }

        Uploads uploads = byPath.get();

        return UploadsDTO.builder()
                .id(uploads.getId())
                .path(uploads.getPath())
                .generatedName(uploads.getGeneratedName())
                .size(uploads.getSize())
                .contentType(uploads.getContentType())
                .originalName(uploads.getOriginalName())
                .build();
    }

    @Override
    public Uploads extractCover(Part part) {
        try {
            String contentType = "image/png";

            String[] split = part.getSubmittedFileName().split("\\.");

            String originalFileName = split[0] + "\\.png";

            originalFileName = originalFileName.replaceAll(",", "_");

            long size = part.getSize();

            String generatedName = System.currentTimeMillis() + "\\.png";

            String path = "/upload/" + generatedName;

            Uploads uploads = Uploads.builder()
                    .contentType(contentType)
                    .originalName(originalFileName)
                    .generatedName(generatedName)
                    .size(size)
                    .path(path)
                    .build();

            String uploadPath = rootPath.resolve(generatedName).toString();

            dao.save(uploads);

            PDDocument document = PDDocument.load(part.getInputStream());
            PDFRenderer pdfRenderer = new PDFRenderer(document);
            BufferedImage bufferedImage = pdfRenderer.renderImageWithDPI(0, 300, ImageType.RGB);

            ImageIOUtil.writeImage(bufferedImage, uploadPath, 300);

            return uploads;


        } catch (IOException e) {
            throw new RuntimeException("Something wrong try again");
        }
    }
}
