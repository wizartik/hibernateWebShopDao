package dao.impl.users;

import dao.users.UserDao;
import dao.impl.responses.users.UserDaoResponse;
import dao.util.MD5Generator;
import dao.util.Validator;
import entities.users.User;
import entities.users.User_;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static dao.impl.responses.users.UserDaoResponse.*;

public class UserDaoImpl implements UserDao {

    private EntityManager entityManager;

    {
        entityManager = Persistence
                .createEntityManagerFactory("entities")
                .createEntityManager();
    }

    @Override
    public UserDaoResponse registerUser(User user) {
        UserDaoResponse userDaoResponse = OK;

        Validator validator = new Validator();

        if (!validator.validateEmail(user.getUserEmail())) {
            return UserDaoResponse.INVALID_EMAIL;
        } else if (validator.isPasswordLong(user.getUserPassword())) {
            return UserDaoResponse.LONG_PASSWORD;
        } else if (validator.isPasswordShort(user.getUserPassword())) {
            return UserDaoResponse.SHORT_PASSWORD;
        }

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
        UserDaoResponse response = OK;
        try {
            entityManager.remove(user);
        } catch (Exception e) {
            response = CONNECTION_PROBLEMS;
        }
        return response;
    }

    @Override
    public User login(String email, String password) {
        MD5Generator md5Generator = new MD5Generator();

        String generatedPassword = md5Generator.generateMD5(password);

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        Root<User> from = query.from(User.class);

        query.select(from);

        Predicate and = criteriaBuilder.and(criteriaBuilder.equal(from.get(User_.userEmail), email),
                criteriaBuilder.equal(from.get(User_.userPassword), generatedPassword));

        query.where(and);

        User user = null;
        try {
            user = entityManager.createQuery(query).getSingleResult();
        } catch (Exception ignored) {
        }
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        return getUserByString(User_.userEmail, email);
    }

    @Override
    public User getUser(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User getUserByIP(String ip) {
        return getUserByString(User_.userIp, ip);
    }

    @Override
    public User getUserByPhone(String phoneNumber) {
        return getUserByString(User_.userPhone, phoneNumber);
    }

    @Override
    public List<User> getUsersSinceDate(Timestamp timestamp) {

        String query = "SELECT u " +
                "from User u WHERE u.userRegistrationDate > :timestamp";

        TypedQuery<User> typedQuery = entityManager.createQuery(query, User.class);
        typedQuery.setParameter("timestamp", timestamp);
        List<User> users;

        try {
            users = typedQuery.getResultList();
        } catch (Exception e) {
            users = new ArrayList<>(0);
        }

        return users;
    }

    @Override
    public List<User> getNonVerifiedUsers() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        Root<User> from = query.from(User.class);
        query.select(from);
        query.where(criteriaBuilder.equal(from.get(User_.userEmailVerified), false));

        List<User> users;

        try {
            users = entityManager.createQuery(query).getResultList();
        } catch (Exception e) {
            users = new ArrayList<>(0);
        }

        return users;
    }

    @Override
    public List<User> getAllUsers() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        Root<User> from = query.from(User.class);
        query.select(from);
        TypedQuery<User> typedQuery = entityManager.createQuery(query);
        return typedQuery.getResultList();
    }

    private boolean isEmailExists(String email) {
        return getUserByString(User_.userEmail, email) != null;
    }

    private void encryptPassword(User user) {
        MD5Generator md5Generator = new MD5Generator();
        user.setUserPassword(md5Generator.generateMD5(user.getUserPassword()));
    }

    private User getUserByString(SingularAttribute<User, String> attribute,
                                 String string) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        Root<User> from = query.from(User.class);

        query.select(from);
        query.where(criteriaBuilder.equal(from.get(attribute), string));

        User user = null;
        try {
            user = entityManager.createQuery(query).getSingleResult();
        } catch (Exception ignored) {
        }

        return user;
    }
}
