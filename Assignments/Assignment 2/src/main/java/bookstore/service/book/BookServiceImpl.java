package bookstore.service.book;

import bookstore.converter.BookDtoToBookConverter;
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
    private BookDtoToBookConverter converter;

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
        return bookRepository.save(converter.apply(bookDto));
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void update(Book book) {
        Book newBook = bookRepository.getOne(book.getId());
        newBook.setTitle(book.getTitle());
        newBook.setAuthor(book.getAuthor());
        newBook.setGenre(book.getGenre());
        newBook.setQuantity(book.getQuantity());
        newBook.setPrice(book.getPrice());

        bookRepository.save(newBook);
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.getOne(id);
    }


    @Override
    public List<Book> findAllByField(String searchField) {
        return bookRepository.findAllByField(searchField);
    }

    @Override
    public boolean sellBook(String title, Long quantity) {
        Book book = bookRepository.findByTitle(title);
        if (book.getQuantity() != 0) {
            book.setQuantity(book.getQuantity() - 1);
            bookRepository.save(book);
            return true;
        } else {
            return false;
        }
    }

}
