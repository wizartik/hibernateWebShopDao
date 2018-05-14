package dao.impl.products;

import dao.ProductDao;
import dao.impl.responses.products.ProductDaoResponse;
import entities.products.Category;
import entities.products.Product;
import entities.products.Product_;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;
import java.util.ArrayList;
import java.util.List;

import static dao.impl.responses.products.ProductDaoResponse.OK;

public class ProductDaoImpl implements ProductDao {

    private static EntityManager entityManager;
    private static CriteriaBuilder criteriaBuilder;

    static {
        entityManager = Persistence
                .createEntityManagerFactory("entities")
                .createEntityManager();
        criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    @Override
    public Product getProduct(int id) {
        CriteriaQuery<Product> query = criteriaBuilder.createQuery(Product.class);
        Root<Product> from = query.from(Product.class);

        query.select(from);

        query.where(criteriaBuilder.equal(from.get(Product_.productId), id));

        Product product = null;

        try {
            product = entityManager.createQuery(query).getSingleResult();
        } catch (Exception ignored) {
        }

        return product;
    }

    @Override
    public Product getProductBySKU(String SKU) {
        return getByString(Product_.productSku, SKU);
    }

    @Override
    public Product getProductByName(String name) {
        return getByString(Product_.productName, name);
    }

    @Override
    public ProductDaoResponse addProduct(Product product) {

        if (isProductExists(product)){
            return ProductDaoResponse.PRODUCT_EXISTS;
        }

        entityManager.getTransaction().begin();
        entityManager.persist(product);
        entityManager.getTransaction().commit();
        
        return OK;
    }

    @Override
    public List<Product> getByPriceCheapFirst(Category category) {

        if (category == null){
            return new ArrayList<>(0);
        }

        CriteriaQuery<Product> query = criteriaBuilder.createQuery(Product.class);
        Root<Product> from = query.from(Product.class);

        query.select(from);
        query.where(criteriaBuilder.equal(from.get(Product_.category), category));
        query.orderBy(criteriaBuilder.asc(from.get(Product_.category)));

        return listFromQuery(query);
    }

    @Override
    public List<Product> getByPriceExpensiveFirst(Category category) {
        if (category == null){
            return new ArrayList<>(0);
        }

        CriteriaQuery<Product> query = criteriaBuilder.createQuery(Product.class);
        Root<Product> from = query.from(Product.class);

        query.select(from);
        query.where(criteriaBuilder.equal(from.get(Product_.category), category));
        query.orderBy(criteriaBuilder.desc(from.get(Product_.category)));

        return listFromQuery(query);
    }

    @Override
    public List<Product> getProductsByWeight(Double from, Double to) {
        CriteriaQuery<Product> query = criteriaBuilder.createQuery(Product.class);
        Root<Product> from1 = query.from(Product.class);

        query.select(from1);
        query.where(criteriaBuilder.between(from1.get(Product_.productWeight),
                from, to));

        return listFromQuery(query);
    }

    @Override
    public List<Product> getProductsByCategory(Category category) {

        CriteriaQuery<Product> query = criteriaBuilder.createQuery(Product.class);
        Root<Product> from = query.from(Product.class);

        query.select(from);
        query.where(criteriaBuilder.equal(from.get(Product_.category), category));

        return listFromQuery(query);
    }

    @Override
    public List<Product> getLiveProducts() {

        CriteriaQuery<Product> query = criteriaBuilder.createQuery(Product.class);
        Root<Product> from = query.from(Product.class);

        query.select(from);
        query.where(criteriaBuilder.equal(from.get(Product_.productLive), true));
        return listFromQuery(query);
    }

    private Product getByString(SingularAttribute<Product, String> attribute,
                                String string){
        CriteriaQuery<Product> query = criteriaBuilder.createQuery(Product.class);
        Root<Product> from = query.from(Product.class);

        query.select(from);
        query.where(criteriaBuilder.equal(from.get(attribute), string));
        Product product = null;

        try {
            product = entityManager.createQuery(query).getSingleResult();
        } catch (Exception ignored) {
        }

        return product;
    }

    private boolean isProductExists(Product product){
        CriteriaQuery<Product> query = criteriaBuilder.createQuery(Product.class);

        Root<Product> from = query.from(Product.class);

        query.select(from);

        Predicate equalNames = criteriaBuilder
                .equal(from.get(Product_.productName),
                        product.getProductName());

        Predicate equalCategories = criteriaBuilder
                .equal(from.get(Product_.category),
                        product.getCategory());
        query.where(criteriaBuilder.and(equalNames, equalCategories));

        try {
            entityManager.createQuery(query).getSingleResult();
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    private List<Product> listFromQuery(CriteriaQuery<Product> query){
        List<Product> products;

        try {
            products = entityManager.createQuery(query).getResultList();
        } catch (Exception e) {
            products = new ArrayList<>(0);
        }
        return products;
    }
}
