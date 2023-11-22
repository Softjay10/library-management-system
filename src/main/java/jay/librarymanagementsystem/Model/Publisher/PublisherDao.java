package jay.librarymanagementsystem.Model.Publisher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherDao extends JpaRepository<Publisher, Long> {

    @Query("SELECT COUNT(p.id) FROM Publisher p")
    long countById();
}
