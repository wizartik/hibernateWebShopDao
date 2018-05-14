package dao;

import dao.impl.products.ProductDaoImpl;
import dao.impl.responses.products.ProductDaoResponse;
import entities.products.Category;
import entities.products.Product;

import java.util.List;

public interface ProductDao {

    default ProductDao getInstance(){
        return new ProductDaoImpl();
    }

    Product getProduct(int id);

    Product getProductBySKU(String SKU);

    Product getProductByName(String name);

    ProductDaoResponse addProduct(Product product);

    List<Product> getByPriceCheapFirst(Category category);

    List<Product> getByPriceExpensiveFirst(Category category);

    List<Product> getProductsByWeight(Double from, Double to);

    List<Product> getProductsByCategory(Category category);

    List<Product> getLiveProducts();
}
