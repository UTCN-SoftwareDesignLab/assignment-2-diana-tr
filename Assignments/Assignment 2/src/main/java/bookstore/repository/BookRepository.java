package bookstore.repository;

import bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByTitle(String title);

    List<Book> findAllByAuthor(String author);

    List<Book> findAllByGenre(String genre);

    Book findByTitle(String title);
}
