package jay.librarymanagementsystem.Model.Book;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "isbn", length = 50, nullable = false, unique = true)
    private String isbn;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "serialName", length = 50, nullable = false)
    private String serialName;

    @Column(name = "description", length = 250, nullable = false)
    private String description;

    @CreationTimestamp
    private Timestamp createdAt;

//    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
//    @JoinTable(name = "books_authors", joinColumns = { @JoinColumn(name = "book_id") }, inverseJoinColumns = { @JoinColumn(name = "author_id") })
//    private Set<Author> authors = new HashSet<Author>();
//
//    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
//    @JoinTable(name = "books_categories", joinColumns = { @JoinColumn(name = "book_id") }, inverseJoinColumns = { @JoinColumn(name = "category_id") })
//    private Set<Category> categories = new HashSet<Category>();
//
//    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
//    @JoinTable(name = "books_publishers", joinColumns = { @JoinColumn(name = "book_id") }, inverseJoinColumns = {@JoinColumn(name = "publisher_id") })
//    private Set<Publisher> publishers = new HashSet<Publisher>();
}
