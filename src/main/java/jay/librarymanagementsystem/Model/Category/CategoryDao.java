package jay.librarymanagementsystem.Model.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDao extends JpaRepository<Category, Long> {

    @Query("SELECT COUNT(c.id) FROM Category c")
    long countById();
}
