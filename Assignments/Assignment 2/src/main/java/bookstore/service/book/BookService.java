package bookstore.service.book;

import bookstore.dto.BookDto;
import bookstore.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> getAll();
    Book create(BookDto bookDto);
    void delete(Long id);
    void update(Book book);
    Book findById(Long id);
    List<Book> findAllByField(String searchField);
    boolean sellBook(String title,Long quantity);
}
