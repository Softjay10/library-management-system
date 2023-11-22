package jay.librarymanagementsystem.Service;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import jay.librarymanagementsystem.Constant.Item;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class FileService {

    public void exportCSV(String fileName, HttpServletResponse response) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {

        var item = Item.getItemByValue(fileName);
        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + item.get().getFileName() + "\"");

//        switch (item.get()) {
//            case BOOK:
//                StatefulBeanToCsv<BookRecord> writer1 = getWriter(response.getWriter());
//                writer1.write(Mapper.bookModelToVo(bookService.findAllBooks()));
//                break;
//            case AUTHOR:
//                StatefulBeanToCsv<AuthorRecord> writer2 = getWriter(response.getWriter());
//                writer2.write(Mapper.authorModelToVo(authorService.findAllAuthors()));
//                break;
//            case CATEGORY:
//                StatefulBeanToCsv<CategoryRecord> writer3 = getWriter(response.getWriter());
//                writer3.write(Mapper.categoryModelToVo(categoryService.findAllCategories()));
//                break;
//            case PUBLISHER:
//                StatefulBeanToCsv<PublisherRecord> writer4 = getWriter(response.getWriter());
//                writer4.write(Mapper.publisherModelToVo(publisherService.findAllPublishers()));
//                break;
//        }

//        private static <T > StatefulBeanToCsv < T > getWriter(PrintWriter printWriter) {
//            return new StatefulBeanToCsvBuilder<T>(printWriter).withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
//                    .withSeparator(CSVWriter.DEFAULT_SEPARATOR).withOrderedResults(false).build();
//        }
    }
}