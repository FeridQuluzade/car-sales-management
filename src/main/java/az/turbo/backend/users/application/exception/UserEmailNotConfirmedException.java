package az.turbo.backend.users.application.exception;

public class UserEmailNotConfirmedException extends RuntimeException {
    public UserEmailNotConfirmedException(String message) {
        super(message);
    }
}
