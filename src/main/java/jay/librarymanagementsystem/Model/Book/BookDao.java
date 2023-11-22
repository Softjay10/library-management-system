package jay.librarymanagementsystem.Model.Book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookDao extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE b.name LIKE %?1%" + "OR b.isbn LIKE %?1%" + "OR b.serialName LIKE  %?1%")
    public List<Book> searchBook(String keyword);

    @Query("SELECT COUNT(b.id) FROM Book b")
    long countBookById();
}
