package bookstore.repository;

import bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(value="SELECT * FROM book b WHERE b.title LIKE ?1 OR b.author LIKE ?1 OR b.genre LIKE ?1",nativeQuery = true)
    List<Book> findAllByField(String searchField);

    Book findByTitle(String title);
}
