package az.turbo.backend;

import az.turbo.backend.cities.application.CityService;
import az.turbo.backend.cities.application.CityServiceImpl;
import az.turbo.backend.cities.domain.CityRepository;
import az.turbo.backend.colors.application.ColorService;
import az.turbo.backend.colors.application.ColorServiceImpl;
import az.turbo.backend.colors.application.dto.ColorCreateDto;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

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

//        ColorService colorService= new ColorServiceImpl();
//        ColorCreateDto colorCreateDto= new ColorCreateDto();
//        colorCreateDto.setName("Black");
//        colorCreateDto.setCreatedBy(22L);
//        colorCreateDto.setCreatedDate(LocalDateTime.now());
//        System.out.println(colorService.create(colorCreateDto));
//

        CityService cityService =new CityServiceImpl();
        cityService.deleteById(7,2,LocalDateTime.now());

        cityService.retrieveAll().stream().forEach(System.out::println);


    }
}
