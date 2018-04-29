package bookstore.service;

import bookstore.dto.BookDto;
import bookstore.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> getAll();
    Book create(BookDto bookDto);
    void delete(Long id);
    void update(Book book);
    Book findById(Long id);
    List<Book> findAllByTitle(String title);
    List<Book> findAllByAuthor(String author);
    List<Book> findAllByGenre(String genre);
    boolean sellBook(String title,Long quantity);
}
