package az.turbo.backend;

import az.turbo.backend.bodytypes.application.BodyTypeService;
import az.turbo.backend.bodytypes.application.BodyTypeServiceImpl;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TurboazBackendApplication {

    public static void main(String[] args) {
        //SpringApplication.run(TurboazBackendApplication.class, args);

        //update - Yunus
        //deleteById - Ferid
        //findById - Jalal,
        //findByEmail - Yunus
        //bulkInsert - Jalal
        //bulkDelete - Ferid
        BodyTypeService bodyTypeService= new BodyTypeServiceImpl();
        System.out.println(bodyTypeService.retrieveAll());

    }
}
