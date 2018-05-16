package dao.impl.products;

import dao.products.OptionDao;
import dao.products.OptionGroupDao;
import entities.products.Option;
import entities.products.OptionGroup;
import entities.products.Option_;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class OptionDaoImpl implements OptionDao {

    private static EntityManager entityManager;
    private static CriteriaBuilder criteriaBuilder;

    static {
        entityManager = Persistence
                .createEntityManagerFactory("entities")
                .createEntityManager();
        criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    @Override
    public Option createOption(Option option) {


        if (isOptionExists(option)) {
            option = getOption(option);
        } else {

            OptionGroupDao optionGroupDao = OptionGroupDao.getInstance();
            option.setOptionGroup(optionGroupDao
                    .createOptionGroup(option.getOptionGroup()));

            entityManager.getTransaction().begin();
            entityManager.persist(option);
            entityManager.getTransaction().commit();
        }
        return option;
    }

    @Override
    public Option getOption(Option option) {
        return !isOptionExists(option) ? createOption(option)
                : entityManager.createQuery(createOptionQuery(option))
                .getSingleResult();
    }

    @Override
    public Option getOption(long id) {
        return entityManager.find(Option.class, id);
    }

    @Override
    public boolean isOptionExists(Option option) {

        TypedQuery<Option> query = entityManager.createQuery(createOptionQuery(option));
        try {
            query.getSingleResult();
            return true;
        } catch (Exception ignored) {
        }
        return false;
    }

    @Override
    public List<Option> getOptions(OptionGroup optionGroup) {

        OptionGroupDao instance = OptionGroupDao.getInstance();
        optionGroup = instance.createOptionGroup(optionGroup);

        CriteriaQuery<Option> query = criteriaBuilder.createQuery(Option.class);
        Root<Option> from = query.from(Option.class);

        query.select(from);
        query.where(criteriaBuilder.equal(from.get(Option_.optionGroup), optionGroup));

        List<Option> options;

        try {
            options = entityManager.createQuery(query).getResultList();
        } catch (Exception e) {
            options = new ArrayList<>(0);
        }

        return options;
    }

    private CriteriaQuery<Option> createOptionQuery(Option option) {
        CriteriaQuery<Option> query = criteriaBuilder.createQuery(Option.class);

        Root<Option> from = query.from(Option.class);

        query.select(from);

        Predicate equalNames = criteriaBuilder
                .equal(from.get(Option_.optionName), option.getOptionName());
        Predicate equalGroups = criteriaBuilder
                .equal(from.get(Option_.optionGroup), option.getOptionGroup());

        query.where(criteriaBuilder.and(equalGroups, equalNames));

        return query;
    }
}
