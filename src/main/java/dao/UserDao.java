package dao;

import dao.impl.responses.users.UserDaoResponse;
import entities.orders.Order;
import entities.users.User;

import java.sql.Timestamp;
import java.util.List;

public interface UserDao {

    UserDaoResponse registerUser(User user);

    UserDaoResponse updateAll();

    UserDaoResponse deleteUser(User user);

    User login(String email, String password);

    User getUserByEmail(String email);

    User getUser(int id);

    User getUserByIP(String ip);

    User getUserByPhone(String phoneNumber);

    User getUserByOrder(Order order);

    List<User> getUsersSinceDate(Timestamp timestamp);

    List<User> getNonVerifiedUsers();

    List<User> getAllUsers();
}
