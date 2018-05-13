package testing;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import entities.users.User;

public class Main {


    public static void main(final String[] args) throws Exception {
//        EntityManager entityManager = Persistence
//                .createEntityManagerFactory("entities").createEntityManager();
//
//
//        entityManager.getTransaction().begin();
//        User user = new UserFactory().getRandomUser();
//        System.out.println(user);
//        entityManager.persist(user);
//        entityManager.getTransaction().commit();

        UserDao userDao = new UserDaoImpl();

        User user = new UserFactory().getRandomUser();

        user.setUserEmail("qwe");
        user.setUserPassword("123");

        userDao.registerUser(user);

        System.out.println(userDao.login("qwe", "1213"));

    }

}