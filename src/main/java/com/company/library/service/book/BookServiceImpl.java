package com.company.library.service.book;

import com.company.library.configs.ApplicationContextHolder;
import com.company.library.dao.AbstractDAO;
import com.company.library.dao.BookDAO;
import com.company.library.domains.Book;
import com.company.library.dto.BookCreateDTO;
import com.company.library.dto.BookUpdateDTO;
import com.company.library.enums.BookStatus;
import com.company.library.exceptions.NotFoundException;
import com.company.library.service.file.FileStorageServiceImpl;

import javax.servlet.http.Part;
import java.awt.print.PageFormat;
import java.awt.print.Pageable;
import java.awt.print.Printable;
import java.util.List;

public class BookServiceImpl extends AbstractDAO<BookDAO> implements BookService, Pageable {
    private static BookServiceImpl bookService;

    private final FileStorageServiceImpl fileStorageService = ApplicationContextHolder.getBean(FileStorageServiceImpl.class);

    public BookServiceImpl() {
        super(ApplicationContextHolder.getBean(BookDAO.class));
    }

    @Override
    public void create(BookCreateDTO dto, Part book) {

    }

    @Override
    public Book get(long id) {
        return null;
    }

    @Override
    public void delete(long l) {

    }

    @Override
    public void update(BookUpdateDTO dto) throws NotFoundException {

    }

    @Override
    public List<Book> getAll() {
        return null;
    }

    @Override
    public List<Book> getAll(String searchQuery, Integer page, Integer limit) {
        return null;
    }

    @Override
    public List<Book> getAllByStatus(BookStatus pending) {
        return null;
    }

    @Override
    public void updateDownloadCount(Long uploadFileId) {

    }

    @Override
    public int getNumberOfPages() {
        return 0;
    }

    @Override
    public PageFormat getPageFormat(int i) throws IndexOutOfBoundsException {
        return null;
    }

    @Override
    public Printable getPrintable(int i) throws IndexOutOfBoundsException {
        return null;
    }

    public static BookServiceImpl getInstance() {
        if (bookService == null) {
            bookService = new BookServiceImpl();
        }
        return bookService;
    }
}
