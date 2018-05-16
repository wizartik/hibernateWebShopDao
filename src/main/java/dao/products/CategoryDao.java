package dao.products;

import dao.impl.products.CategoryDaoImpl;
import entities.products.Category;

import java.util.List;

public interface CategoryDao {

    static CategoryDao getInstance(){
        return new CategoryDaoImpl();
    }

    Category createCategory(Category category);

    Category getCategory(long id);

    Category getCategory(Category category);

    List<Category> getCategoriesByParent(Category category);

    boolean isCategoryExists(Category category);
}
