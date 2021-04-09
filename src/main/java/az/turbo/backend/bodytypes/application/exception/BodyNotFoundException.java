package az.turbo.backend.bodytypes.application.exception;

public class BodyNotFoundException extends  RuntimeException{
    public BodyNotFoundException(String  message) {
        super(message);
    }
}
