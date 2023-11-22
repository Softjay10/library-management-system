package jay.librarymanagementsystem.Controller;

import jay.librarymanagementsystem.Model.Author.AuthorService;
import jay.librarymanagementsystem.Model.Book.Book;
import jay.librarymanagementsystem.Model.Book.BookService;
import jay.librarymanagementsystem.Model.Category.CategoryService;
import jay.librarymanagementsystem.Model.Publisher.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private PublisherService publisherService;


    /*LIST*/
    @RequestMapping({ "/books", "/" })
    public String findAllBooks(Model model, @RequestParam("page") Optional<Integer> page,
                               @RequestParam("size") Optional<Integer> size) {

        var currentPage = page.orElse(1);
        var pageSize = size.orElse(5);

        var bookPage = bookService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("books", bookPage);

        var totalPages = bookPage.getTotalPages();
        if (totalPages > 0) {
            var pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "views/backend/book/index";
    }

    /*SEARCH*/
    @RequestMapping("/searchBook")
    public String searchBook(@Param("keyword") String keyword, Model model) {

        model.addAttribute("books", bookService.searchBooks(keyword));
        model.addAttribute("keyword", keyword);
        return "views/backend/book/index";
    }

    /*FIND ID*/
    @RequestMapping("/book/{id}")
    public String findBookById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("book", bookService.findBookById(id));
        return "views/backend/book/index";
    }

    /*SHOW FORM*/
    @GetMapping(value = "/addBooks")
    public String showCreateForm(Book book, Model model) {
        model.addAttribute("categories", categoryService.findAllCategory());
        model.addAttribute("authors", authorService.findAllAuthors());
        model.addAttribute("publishers", publisherService.findAllPublishers());
        model.addAttribute("books", book);
        return "views/backend/book/create";
    }

    /*SAVE*/
    @RequestMapping("/add-book")
    public String createBook(Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "views/backend/book/create";
        }

        bookService.createBook(book);
        model.addAttribute("book", bookService.findAllBooks());
        return "redirect:/books";
    }

    /*DELETE*/
    @RequestMapping("/delete-book/{id}")
    public String deleteBook(@PathVariable("id") Long id, Model model) {
        bookService.deleteBook(id);

        model.addAttribute("book", bookService.findAllBooks());
        return "redirect:/books";
    }


    /*SHOW UPDATE FORM*/
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("book", bookService.findBookById(id));

        return "views/backend/book/update";
    }


    /*UPDATE*/
    @RequestMapping("/update-book/{id}")
    public String updateBook(@PathVariable("id") Long id, Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            book.setId(id);
            return "views/backend/book/update";
        }

        bookService.updateBook(book);
        model.addAttribute("book", bookService.findAllBooks());
        return "redirect:/books";
    }

}
