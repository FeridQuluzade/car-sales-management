package az.turbo.backend.users.application.exception;

public class UserFailAuthenticationException extends RuntimeException {
    public UserFailAuthenticationException(String message) {
        super(message);
    }
}
