package jay.librarymanagementsystem.Controller;

import jay.librarymanagementsystem.Model.Category.Category;
import jay.librarymanagementsystem.Model.Category.CategoryDao;
import jay.librarymanagementsystem.Model.Category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    /*LIST*/
    @GetMapping(value = "/categories")
    public String getAllCategories(Model model) {
        model.addAttribute("categories", categoryService.findAllCategory());
        return "views/backend/category/index";
    }

    /*SHOW FORM*/
    @GetMapping(value = "/addCategory")
    public String showCreateForm(Model model, Category category) {
        model.addAttribute("category", category);
        return "views/backend/category/create";
    }

    /*SAVE*/
    @PostMapping(value = "/save")
    public String createCategory(Category category, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "views/backend/category/create";
        }

        categoryService.createCategory(category);
        model.addAttribute("category", categoryService.findAllCategory());
        return "redirect:/categories";
    }


    @GetMapping("/updateCategory/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("category", categoryService.findCategoryById(id));
        return "views/backend/category/update";
    }

    /*UPDATE*/
    @RequestMapping("/edit-category/{id}")
    public String updateCategory(@PathVariable("id") Long id, Category category, BindingResult result, Model model) {
        if (result.hasErrors()) {
            category.setId(id);
            return "views/backend/category/update";
        }

        categoryService.updateCategory(category);
        model.addAttribute("category", categoryService.findAllCategory());
        return "redirect:/categories";
    }

    /*DELETE*/
    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable(name = "id") Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/categories";
    }
}
