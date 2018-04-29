package bookstore.service.report;

import bookstore.entity.Book;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class PdfReport implements ReportGenerator {
    static final String FILE_LOCATION = "src/main/resources/reports/reportPdf.pdf";

    public PdfReport(List<Book> books){
        generateReport(books);
    }
    @Override
    public void generateReport(List<Book> books) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getQuantity() == 0) {
                result.add(book);
            }
        }

        try {
            Document document=new Document();
            PdfWriter.getInstance(document,new FileOutputStream(FILE_LOCATION));
            document.open();
            Paragraph paragraph=new Paragraph();
            paragraph.add("Table with out of stock books");
            document.add(paragraph);
            PdfPTable table=new PdfPTable(5);

            PdfPCell cell1=new PdfPCell(new Phrase("Id"));
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell1);

            PdfPCell cell2=new PdfPCell(new Phrase("Title"));
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell2);

            PdfPCell cell3=new PdfPCell(new Phrase("Author"));
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell3);

            PdfPCell cell4=new PdfPCell(new Phrase("Genre"));
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell4);

            PdfPCell cell5=new PdfPCell(new Phrase("Price"));
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell5);
            table.setHeaderRows(1);
            for(Book book:result){
                table.addCell(String.valueOf(book.getId()));
                table.addCell(book.getTitle());
                table.addCell(book.getAuthor());
                table.addCell(book.getGenre());
                table.addCell(String.valueOf(book.getPrice()));
            }

            document.add(table);
            document.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
