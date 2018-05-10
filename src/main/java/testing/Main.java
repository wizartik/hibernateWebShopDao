package testing;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import dao.responses.users.UserDaoResponse;
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

        int count = 0;
        for (int i = 0; i < 1000; i++) {
            User user = new UserFactory().getRandomUser();

            if(userDao.registerUser(user) == UserDaoResponse.OK){
                count++;
            }
        }

        System.out.println(count);
    }

}