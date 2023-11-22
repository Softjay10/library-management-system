package jay.librarymanagementsystem.Model.Category;

import jay.librarymanagementsystem.Exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    /*LIST*/
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Category> findAllCategory() {
        return categoryDao.findAll();
    }

    /*FIND BY ID*/
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Category findCategoryById(Long id) {
        return categoryDao.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Category not found  with ID %d", id)));
    }

    public void createCategory(Category category){
        categoryDao.save(category);
    }

    public void updateCategory(Category category){
        categoryDao.save(category);
    }

    public void deleteCategory(Long id) {
        var category = categoryDao.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Category not found  with ID %d", id)));

        categoryDao.deleteById(category.getId());
    }
}
