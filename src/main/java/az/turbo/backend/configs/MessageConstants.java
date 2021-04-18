package az.turbo.backend.configs;

public class MessageConstants {

    public static final String INVALID_EMAIL_FORMAT = "Incorrect email format";
    public static final String INVALID_PASSWORD_FORMAT_MESSAGE = "Password should contain at least 6 chars, Password should contain at least one capital letter and number";
    public static final String INVALID_MOBILE_FORMAT_MESSAGE = "Incorrect mobile number";
    public static final String PASSWORD_VALIDATION = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$";

    private MessageConstants() {
    }
}
