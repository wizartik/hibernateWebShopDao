package dao.impl.products;

import dao.products.CategoryDao;
import entities.products.Category;
import entities.products.Category_;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {

    private EntityManager entityManager;
    private CriteriaBuilder criteriaBuilder;

    {
        entityManager = Persistence
                .createEntityManagerFactory("entities")
                .createEntityManager();
        criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    @Override
    public Category createCategory(Category category) {

        if (isCategoryExists(category)){
            return getCategory(category);
        } else {
            Category superCategory = category.getSuperCategory();
            if (isCategoryExists(superCategory)){
                category.setSuperCategory(createCategory(superCategory));
            }

            entityManager.getTransaction().begin();
            entityManager.persist(category);
            entityManager.getTransaction().commit();
        }

        return category;
    }

    @Override
    public Category getCategory(long id) {
        return entityManager.find(Category.class, id);
    }

    @Override
    public Category getCategory(Category category) {
        if (!isCategoryExists(category)){
            return null;
        } else {

            CriteriaQuery<Category> query = createCategoryQuery(category);

            try {
                category = entityManager.createQuery(query).getSingleResult();
            } catch (Exception e) {
                category = null;
            }
        }
        return category;
    }

    @Override
    public List<Category> getCategoriesByParent(Category category) {
        List<Category> categories;

        CriteriaQuery<Category> query = criteriaBuilder.createQuery(Category.class);
        Root<Category> from = query.from(Category.class);

        query.select(from);

        if (category != null){
            query.where(criteriaBuilder.equal(from.get(Category_.superCategory),
                    category));
        } else {
            query.where(criteriaBuilder.isNull(from.get(Category_.superCategory)));
        }

        try {
            categories = entityManager.createQuery(query).getResultList();
        } catch (Exception e) {
            categories = new ArrayList<>(0);
        }

        return categories;
    }

    @Override
    public boolean isCategoryExists(Category category) {

        CriteriaQuery<Category> categoryQuery = createCategoryQuery(category);

        try {
            entityManager.createQuery(categoryQuery).getSingleResult();
            return true;
        } catch (Exception ignored) {
        }

        return false;
    }

    private CriteriaQuery<Category> createCategoryQuery(Category category){
        CriteriaQuery<Category> query = criteriaBuilder.createQuery(Category.class);
        Root<Category> from = query.from(Category.class);

        query.select(from);

        Predicate equalNames = criteriaBuilder
                .equal(from.get(Category_.categoryName), category.getCategoryName());
        Predicate equalParents = criteriaBuilder
                .equal(from.get(Category_.superCategory), category.getSuperCategory());

        query.where(criteriaBuilder.and(equalNames, equalParents));
        return query;
    }
}
