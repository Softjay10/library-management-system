package jay.librarymanagementsystem.Controller;

import jay.librarymanagementsystem.Model.Author.Author;
import jay.librarymanagementsystem.Model.Author.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @RequestMapping(value = "/authors")
    public String findAllAuthors(Model model,
                                 @RequestParam("page") Optional<Integer> page,
                                 @RequestParam("size") Optional<Integer> size) {

        var currentPage = page.orElse(1);
        var pageSize = size.orElse(5);
        var bookPage = authorService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("authors", bookPage);

        int totalPages = bookPage.getTotalPages();
        if (totalPages > 0) {
            var pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "views/backend/author/index";
    }

    @GetMapping("/addAuthor")
    public String showCreateForm(Model model,Author author) {
        model.addAttribute("author", author);
        return "views/backend/author/create";
    }

    @PostMapping("/add-author")
    public String createAuthor(Author author, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "views/backend/author/create";
        }

        authorService.createAuthor(author);
        model.addAttribute("author", authorService.findAllAuthors());
        return "redirect:/authors";
    }

    @GetMapping("/updateAuthor/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {

        model.addAttribute("author", authorService.findAuthorById(id));
        return "views/backend/author/update";
    }

    @RequestMapping("/edit/{id}")
    public String updateAuthor(@PathVariable("id") Long id, Author author, BindingResult result, Model model) {
        if (result.hasErrors()) {
            author.setId(id);
            return "views/backend/author/update";
        }

        authorService.updateAuthor(author);
        model.addAttribute("author", authorService.findAllAuthors());
        return "redirect:/authors";
    }

    @RequestMapping("/delete-author/{id}")
    public String deleteAuthor(@PathVariable("id") Long id, Model model) {
        authorService.deleteAuthor(id);

        model.addAttribute("author", authorService.findAllAuthors());
        return "redirect:/authors";
    }
}
