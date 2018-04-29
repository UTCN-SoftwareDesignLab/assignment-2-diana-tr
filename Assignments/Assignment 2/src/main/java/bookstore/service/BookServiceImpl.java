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

    @Override
    public void update(Book book) {
       Book newBook=bookRepository.getOne(book.getId());
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
    public List<Book> findAllByTitle(String title) {
        return bookRepository.findAllByTitle(title);
    }

    @Override
    public List<Book> findAllByAuthor(String author) {
        return bookRepository.findAllByAuthor(author);
    }

    @Override
    public List<Book> findAllByGenre(String genre) {
        return bookRepository.findAllByGenre(genre);
    }

    @Override
    public boolean sellBook(String title, Long quantity) {
        Book book=bookRepository.findByTitle(title);
        if(book.getQuantity()!=0){
            book.setQuantity(book.getQuantity()-1);
            bookRepository.save(book);
            return true;
        }else{
            return false;
        }
    }

}
