package bookstore.service;

import bookstore.dto.BookDto;
import bookstore.entity.Book;
import bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book create(BookDto bookDto) {
        Book book = new Book(bookDto.getTitle(), bookDto.getAuthor(), bookDto.getGenre(), bookDto.getQuantity(), bookDto.getPrice());
        return bookRepository.save(book);
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

}
