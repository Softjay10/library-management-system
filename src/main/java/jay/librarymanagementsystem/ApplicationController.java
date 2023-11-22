package jay.librarymanagementsystem;

import jay.librarymanagementsystem.Model.Author.AuthorDao;
import jay.librarymanagementsystem.Model.Book.BookDao;
import jay.librarymanagementsystem.Model.Category.CategoryDao;
import jay.librarymanagementsystem.Model.Publisher.PublisherDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationController {

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private BookDao bookDao;

    @Autowired
    private PublisherDao publisherDao;

    @Autowired
    private AuthorDao authorDao;

    @GetMapping(value = "/")
    public String getDashboard(Model model) {
        model.addAttribute("categories", categoryDao.countById());
        model.addAttribute("books", bookDao.countBookById());
        model.addAttribute("authors", authorDao.countById());
        model.addAttribute("publishers", publisherDao.countById());
        return "views/dashboard/index";
    }

    @GetMapping("/login")
    public String login() {
        return "views/login";
    }
}
