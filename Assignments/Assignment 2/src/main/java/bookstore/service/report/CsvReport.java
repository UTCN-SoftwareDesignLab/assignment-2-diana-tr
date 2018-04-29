package bookstore.service.report;

import bookstore.entity.Book;
import bookstore.repository.BookRepository;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvReport implements ReportGenerator{
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";
    private static final String FILE_LOCATION = "src/main/resources/reports/report.csv";
    private static final String FILE_HEADER = "Id, Title, Author, Genre, Price";


    public CsvReport(List<Book> books) {
        generateReport(books);
    }

    @Override
    public void generateReport(List<Book> books){
        List<Book> result=new ArrayList<>();

        FileWriter fileWriter=null;

        try{
            fileWriter=new FileWriter(FILE_LOCATION);
            fileWriter.append(FILE_HEADER);
            fileWriter.append(NEW_LINE_SEPARATOR);

            for(Book book:books){
                if(book.getQuantity()==0){
                    result.add(book);
                }
            }

            for(Book book:result){
                fileWriter.append(String.valueOf(book.getId()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(book.getTitle());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(book.getAuthor());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(book.getGenre());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(book.getPrice()));
                fileWriter.append(NEW_LINE_SEPARATOR);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
