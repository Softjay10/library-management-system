package jay.librarymanagementsystem.Controller;

import jay.librarymanagementsystem.Model.Publisher.Publisher;
import jay.librarymanagementsystem.Model.Publisher.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    /*LIST*/
    @GetMapping(value ="/publishers")
    public String findAllPublishers(Model model) {
        model.addAttribute("publishers", publisherService.findAllPublishers());
        return "views/backend/publisher/index";
    }

    /*SHOW FORM*/
    @GetMapping("/addPublisher")
    public String showCreateForm(Publisher publisher, Model model) {
        model.addAttribute("publisher", publisher);
        return "views/backend/publisher/create";
    }


    /*SAVE*/
    @PostMapping(value = "/add-publisher")
    public String createPublisher(Publisher publisher, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "views/backend/publisher/create";
        }

        publisherService.createPublisher(publisher);
        return "redirect:/publishers";
    }

    /*DELETE*/
    @RequestMapping(value = "/delete-publisher/{id}")
    public String deletePublisher(@PathVariable(name = "id") Long id) {
        publisherService.deletePublisher(id);
        return "redirect:/publishers";
    }

    /*UPDATE*/
    @GetMapping(value = "/edit-publisher/{id}")
    public String updatePublisher(@PathVariable(value = "id") Long id, Model model) {
        Publisher publisher = publisherService.findPublisherById(id);
        model.addAttribute("publisher", publisher);
        return "views/backend/publisher/update";
    }

}
