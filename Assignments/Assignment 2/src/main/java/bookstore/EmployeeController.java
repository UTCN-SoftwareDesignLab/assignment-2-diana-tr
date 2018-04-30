package bookstore;

import bookstore.service.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/employee")
public class EmployeeController {
    @Autowired
    private BookService bookService;

    @RequestMapping()
    public String employee() {
        return "employee";
    }

    @RequestMapping("/book")
    public String book() {
        return "book";
    }


    @RequestMapping(value = "/searchByField", method = RequestMethod.GET)
    public String searchByField(Model model, @RequestParam(name = "searchField") String searchField) {
        model.addAttribute("books", bookService.findAllByField(searchField));
        return "book";
    }

    @RequestMapping(value = "/sellBook", method = RequestMethod.POST)
    public String sellBook(@RequestParam(name = "title") String title,
                           @RequestParam(name = "quantity") String quantity,
                           Model model) {
        boolean result = bookService.sellBook(title, Long.parseLong(quantity));
        if (result == true) {
            model.addAttribute("message", "Book sold");
        } else {
            model.addAttribute("message", "Can't sell book");
        }
        return "employee";
    }


}
