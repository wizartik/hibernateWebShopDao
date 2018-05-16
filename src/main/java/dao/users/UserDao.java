package dao.users;

import dao.impl.users.UserDaoImpl;
import dao.impl.responses.users.UserDaoResponse;
import entities.users.User;

import java.sql.Timestamp;
import java.util.List;

public interface UserDao {

    static UserDao getInstance(){
        return new UserDaoImpl();
    }

    UserDaoResponse registerUser(User user);

    UserDaoResponse updateAll();

    UserDaoResponse deleteUser(User user);

    User login(String email, String password);

    User getUserByEmail(String email);

    User getUser(long id);

    User getUserByIP(String ip);

    User getUserByPhone(String phoneNumber);

    List<User> getUsersSinceDate(Timestamp timestamp);

    List<User> getNonVerifiedUsers();

    List<User> getAllUsers();
}
