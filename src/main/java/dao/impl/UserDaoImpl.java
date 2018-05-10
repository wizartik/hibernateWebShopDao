package dao.impl;

import dao.UserDao;
import dao.responses.users.UserDaoResponse;
import dao.util.MD5Generator;
import entities.orders.Order;
import entities.users.User;
import entities.users.User_;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.util.List;

import static dao.responses.users.UserDaoResponse.*;

public class UserDaoImpl implements UserDao {

    private static EntityManager entityManager;

    static {
        entityManager = Persistence.createEntityManagerFactory("entities")
                .createEntityManager();
    }

    @Override
    public UserDaoResponse registerUser(User user) {
        UserDaoResponse userDaoResponse = OK;

        entityManager.getTransaction().begin();
        if (user.getUserEmail() != null && isEmailExists(user.getUserEmail())) {
            userDaoResponse = EMAIL_TAKEN;
        } else if (user.getUserPassword() != null) {
            encryptPassword(user);
            entityManager.persist(user);
        } else {
            userDaoResponse = UNKNOWN_ERROR;
        }
        entityManager.getTransaction().commit();

        return userDaoResponse;
    }

    @Override
    public UserDaoResponse updateAll() {
        UserDaoResponse userDaoResponse = OK;

        try {
            entityManager.flush();
        } catch (Exception e) {
            userDaoResponse = CONNECTION_PROBLEMS;
        }
        return userDaoResponse;
    }

    @Override
    public UserDaoResponse deleteUser(User user) {
        return null;
    }

    @Override
    public User login(String email, String password) {
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        return null;
    }

    @Override
    public User getUser(int id) {
        return null;
    }

    @Override
    public User getUserByIP(String ip) {
        return null;
    }

    @Override
    public User getUserByPhone(String phoneNumber) {
        return null;
    }

    @Override
    public User getUserByOrder(Order order) {
        return null;
    }

    @Override
    public List<User> getUsersSinceDate(Timestamp timestamp) {
        return null;
    }

    @Override
    public List<User> getNonVerifiedUsers() {
        return null;
    }

    private boolean isEmailExists(String email) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> from = criteria.from(User.class);
        criteria.select(from);
        criteria.where(builder.equal(from.get(User_.userEmail), email));
        TypedQuery<User> typed = entityManager.createQuery(criteria);
        try {
            typed.getSingleResult();
            return true;
        } catch (final NoResultException nre) {
            return false;
        }
    }

    private void encryptPassword(User user) {
        MD5Generator md5Generator = new MD5Generator();
        user.setUserPassword(md5Generator.generateMD5(user.getUserPassword()));
    }
}
