package az.turbo.backend.engineVolumes.application.exception;

public class EngineVolumeNotFoundException extends  RuntimeException{
    public  EngineVolumeNotFoundException( String message){
        super(message);
    }
}
