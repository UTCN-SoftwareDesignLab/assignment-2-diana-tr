package bookstore.service.report;

import org.springframework.stereotype.Service;

@Service
public class ReportFactory {
    public ReportGenerator getReport(String reportType) {
        if (reportType == null) {
            return null;
        }
        if (reportType.equals("PDF")) {
            return new PdfReport();
        } else if (reportType.equals("CSV")) {
            return new CsvReport();
        }
        return null;
    }
}
