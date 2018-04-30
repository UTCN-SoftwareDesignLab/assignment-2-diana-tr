package bookstore;

import bookstore.dto.BookDto;
import bookstore.dto.UserDto;
import bookstore.service.book.BookService;
import bookstore.service.report.ReportFactory;
import bookstore.service.report.ReportGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private static final String FILE_PATH_CSV = "src/main/resources/reports/report.csv";
    private static final String FILE_PATH_PDF = "src/main/resources/reports/reportPdf.pdf";

    @Autowired
    private BookService bookService;
    @Autowired
    private ReportFactory reportFactory;


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


    @GetMapping("/downloadPdf")
    public ResponseEntity<InputStreamResource> downloadPdf() throws IOException {
        ReportGenerator reportGenerator = reportFactory.getReport("PDF");
        reportGenerator.generateReport(bookService.getAll());
        File file = new File(FILE_PATH_PDF);

        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment;filename=" + file.getName())
                .contentType(MediaType.APPLICATION_PDF).contentLength(file.length())
                .body(resource);
    }

    @GetMapping("/downloadCsv")
    public void downloadCsv(HttpServletResponse response) throws IOException {
        ReportGenerator reportGenerator = reportFactory.getReport("CSV");
        reportGenerator.generateReport(bookService.getAll());
        File file = new File(FILE_PATH_CSV);

        response.setContentType("/application/csv");
        response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));
        BufferedOutputStream outputStream = new BufferedOutputStream(response.getOutputStream());

        byte[] buffer = new byte[1024];
        int bytesRead = 0;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        outputStream.flush();
        inputStream.close();
    }
}
