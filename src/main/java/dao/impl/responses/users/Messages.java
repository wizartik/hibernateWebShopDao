package dao.impl.responses.users;

public interface Messages {
   String EMAIL_TAKEN_MESSAGE = "This email is already taken";
   String INVALID_EMAIL_MESSAGE = "Please enter a valid email address.";
   String SHORT_PASSWORD_MESSAGE = "Password should be longer then 6 characters.";
   String LONG_PASSWORD_MESSAGE = "Password should be shorter then 12 characters.";
   String WRONG_LOGIN_MESSAGE = "Wrong login or password.";
   String CONNECTION_PROBLEMS_MESSAGE = "There were some problems with " +
           "connection, please wait a minute.";
   String UNKNOWN_ERROR_MESSAGE = "Server error, please try again later.";


}
