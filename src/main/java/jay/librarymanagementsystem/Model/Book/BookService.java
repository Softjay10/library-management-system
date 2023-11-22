package jay.librarymanagementsystem.Model.Book;

import jay.librarymanagementsystem.Exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookDao bookDao;

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Book> findAllBooks(){
        return bookDao.findAll();
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Book> searchBooks(String keyword) {
        if (keyword != null) {
            return bookDao.searchBook(keyword);
        }
        return bookDao.findAll();
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Book findBookById(Long id) {
        return bookDao.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Book not found with ID %d", id)));
    }

    public void createBook(Book book) {
        bookDao.save(book);
    }

    public void updateBook(Book book) {
        bookDao.save(book);
    }

    public void deleteBook(Long id) {
        var book = bookDao.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Book not found with ID %d", id)));

        bookDao.deleteById(book.getId());
    }

    public Page<Book> findPaginated(Pageable pageable) {

        var pageSize = pageable.getPageSize();
        var currentPage = pageable.getPageNumber();
        var startItem = currentPage * pageSize;
        List<Book> list;

        if (findAllBooks().size() < startItem) {
            list = Collections.emptyList();
        } else {
            var toIndex = Math.min(startItem + pageSize, findAllBooks().size());
            list = findAllBooks().subList(startItem, toIndex);
        }

        var bookPage = new PageImpl<>(list, PageRequest.of(currentPage, pageSize), findAllBooks().size());

        return bookPage;
    }

}
