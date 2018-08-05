package dao.impl.products;

import dao.products.OptionGroupDao;
import entities.products.OptionGroup;
import entities.products.OptionGroup_;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class OptionGroupDaoImpl implements OptionGroupDao {

    private EntityManager entityManager;
    private CriteriaBuilder criteriaBuilder;

    {
        entityManager = Persistence
                .createEntityManagerFactory("entities")
                .createEntityManager();
        criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    @Override
    public OptionGroup getOptionGroup(String name) {
        CriteriaQuery<OptionGroup> query = criteriaBuilder.createQuery(OptionGroup.class);
        Root<OptionGroup> from = query.from(OptionGroup.class);
        query.select(from);

        query.where(criteriaBuilder.equal(from.get(OptionGroup_.optionGroupName), name));

        OptionGroup optionGroup = null;

        try {
            optionGroup = entityManager.createQuery(query).getSingleResult();
        } catch (Exception ignored) {
        }

        return optionGroup;
    }

    @Override
    public OptionGroup getOptionGroup(long id) {
        return entityManager.find(OptionGroup.class, id);
    }

    @Override
    public OptionGroup getOptionGroup(OptionGroup optionGroup) {
        return getOptionGroup(optionGroup.getOptionGroupName());
    }

    @Override
    public OptionGroup createOptionGroup(OptionGroup optionGroup) {

        if (isOptionGroupExists(optionGroup)){
            optionGroup = getOptionGroup(optionGroup);
        } else {
            entityManager.getTransaction().begin();
            entityManager.persist(optionGroup);
            entityManager.getTransaction().commit();
        }

        return optionGroup;
    }

    @Override
    public boolean isOptionGroupExists(OptionGroup optionGroup) {
        return getOptionGroup(optionGroup) != null;
    }
}
