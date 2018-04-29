package bookstore;

import bookstore.dto.BookDto;
import bookstore.entity.Book;
import bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/books")
public class BookCRUDController {
    @Autowired
    private BookService bookService;

    @RequestMapping(method = RequestMethod.GET)
    public String getAll(Model model) {
        model.addAttribute("books", bookService.getAll());
        return "books";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@ModelAttribute @Valid BookDto bookDto) {
        bookService.create(bookDto);
        return "redirect:create?success";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showCreateForm(Model model) {
        model.addAttribute("book", new BookDto());
        return "book-form";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam(name = "id") String id, Model model) {
        bookService.delete(Long.parseLong(id));
        model.addAttribute("deleteMessage", "Book was successfully deleted");
        return "redirect:/books";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String showUpdateForm(Model model) {
        model.addAttribute("book", new Book());
        return "book-update-form";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestParam(name = "id") Long id,
                         @RequestParam(name = "title") String title,
                         @RequestParam(name = "author") String author,
                         @RequestParam(name = "genre") String genre,
                         @RequestParam(name = "quantity") Long quantity,
                         @RequestParam(name = "price") Long price,
                         @ModelAttribute Book book) {
        Book book1 = bookService.findById(id);
        book1.setTitle(title);
        book1.setAuthor(author);
        book1.setGenre(genre);
        book1.setQuantity(quantity);
        book1.setPrice(price);

        bookService.update(book1);
        return "redirect:update?success";
    }
}
