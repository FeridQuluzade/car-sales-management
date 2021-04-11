package az.turbo.backend.bodytypes.application.exception;

public class BodyTypeNotFoundException extends  RuntimeException{
    public BodyTypeNotFoundException(String  message) {
        super(message);
    }
}
