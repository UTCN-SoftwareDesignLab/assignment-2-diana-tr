package bookstore.service;

import bookstore.dto.BookDto;
import bookstore.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> getAll();
    Book create(BookDto bookDto);
    void delete(Long id);
}
