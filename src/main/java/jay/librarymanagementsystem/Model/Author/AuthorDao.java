package jay.librarymanagementsystem.Model.Author;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorDao extends JpaRepository<Author, Long> {

    @Query("SELECT COUNT(a.id) FROM Author a")
    long countById();
}
