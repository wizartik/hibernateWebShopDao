package dao.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    private static final String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";

    private static Pattern pattern;

    private Matcher matcher;

    public Validator() {
        pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
    }

    /**
     * This method validates the input email address with EMAIL_REGEX pattern
     *
     * @param email string.
     * @return true if email is valid.
     */
    public boolean validateEmail(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean isPasswordShort(String password){
        return password.length() < 6;
    }

    public boolean isPasswordLong(String password){
        return password.length() > 11;
    }

}
