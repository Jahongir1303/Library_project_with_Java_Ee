package com.company.library.service.file;

public class FileStorageServiceImpl {
    private static FileStorageServiceImpl fileStorageService;

    public static FileStorageServiceImpl getInstance() {

        if (fileStorageService == null) {
            fileStorageService = new FileStorageServiceImpl();
        }
        return fileStorageService;
    }
}
