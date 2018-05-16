package testing;

import dao.products.CategoryDao;

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
//
//        UserDao userDao = new UserDaoImpl();
//
//        User user = new UserFactory().getRandomUser();
//
//        user.setUserEmail("qwe");
//        user.setUserPassword("123");
//
//        userDao.registerUser(user);
//
////        System.out.println("get all users " + userDao.getAllUsers());
////        System.out.println("by id " + userDao.getUser(100));
////        System.out.println("by email " + userDao.getUserByEmail("calin@gmail" +
////                ".com"));
////        System.out.println("nonverified " + userDao.getNonVerifiedUsers());
//
//        Timestamp timestamp = new UserFactory().getRandomUser()
//                .getUserRegistrationDate();
//
//        System.out.println(timestamp);
//        System.out.println(userDao.getUsersSinceDate(timestamp));

        CategoryDao categoryDao = CategoryDao.getInstance();

        System.out.println(categoryDao.getCategoriesByParent(categoryDao
                .getCategory(7)));

    }

}