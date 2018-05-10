package dao.responses.users;

import static dao.responses.users.Messages.*;

public enum UserDaoResponse {
    OK,
    CONNECTION_PROBLEMS(CONNECTION_PROBLEMS_MESSAGE),
    EMAIL_TAKEN(EMAIL_TAKEN_MESSAGE),
    INVALID_MESSAGE(INVALID_EMAIL_MESSAGE),
    SHORT_PASSWORD(SHORT_PASSWORD_MESSAGE),
    LONG_PASSWORD(LONG_PASSWORD_MESSAGE),
    WRONG_LOGIN(WRONG_LOGIN_MESSAGE);


    private String message;

    UserDaoResponse() {
    }

    private UserDaoResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "UserDaoResponse{" +
                "message='" + message + '\'' +
                '}';
    }
}
