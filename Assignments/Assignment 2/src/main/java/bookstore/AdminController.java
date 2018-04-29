package bookstore;

import bookstore.dto.BookDto;
import bookstore.dto.UserDto;
import bookstore.entity.Book;
import bookstore.service.BookService;
import bookstore.service.report.CsvReport;
import bookstore.service.report.PdfReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private BookService bookService;

    @RequestMapping()
    public String admin() {
        return "admin";
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String books(@ModelAttribute BookDto bookDto) {
        return "redirect:/books";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String users(@ModelAttribute UserDto userDto) {
        return "redirect:/users";
    }

    @RequestMapping(value = "/generateCsv", method = RequestMethod.GET)
    public String generateCsv(Model model) {
       List<Book> bookList=bookService.getAll();
       new CsvReport(bookList);
       model.addAttribute("csvMessage","Csv file successfully created!");
       return "admin";
    }

    @RequestMapping(value="/generatePdf",method = RequestMethod.GET)
    public String generatePdf(Model model){
        List<Book> bookList=bookService.getAll();
        new PdfReport(bookList);
        model.addAttribute("pdfMessage","Pdf file successfully created!");
        return "admin";
    }


}
