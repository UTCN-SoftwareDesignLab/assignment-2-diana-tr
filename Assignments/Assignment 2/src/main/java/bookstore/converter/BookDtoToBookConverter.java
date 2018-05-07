package bookstore.converter;

import bookstore.dto.BookDto;
import bookstore.entity.Book;
import org.springframework.stereotype.Service;

@Service
public class BookDtoToBookConverter implements SuperConverter<BookDto, Book> {
    @Override
    public Book apply(final BookDto input) {
        final Book book = new Book();
        book.setId(input.getId());
        book.setTitle(input.getTitle());
        book.setAuthor(input.getAuthor());
        book.setGenre(input.getGenre());
        book.setQuantity(input.getQuantity());
        book.setPrice(input.getPrice());
        return book;
    }
}
