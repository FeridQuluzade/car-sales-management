package az.turbo.backend.supplies;

import az.turbo.backend.shared.UserContextHolder;
import az.turbo.backend.supplies.application.SupplyService;
import az.turbo.backend.supplies.application.dto.SupplyCreateDto;
import az.turbo.backend.supplies.application.dto.SupplyDto;
import az.turbo.backend.supplies.application.dto.SupplyUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/supplies")
public class SupplyController {
    private SupplyService supplyService;

    @Autowired
    public SupplyController(SupplyService supplyService) {
        this.supplyService = supplyService;
    }

    @GetMapping(value = "/retrieve-all")
    public List<SupplyDto> retrieveAll(){
         return supplyService.retrieveAll();
    }

    @GetMapping(value = "/retrieve-by-id/{id}")
    @ResponseBody
    public SupplyUpdateDto retrieveById(@PathVariable Long id){
        return supplyService.retrieveById(id);
    }
    @PostMapping(value = "create")
    public long create(@Valid @RequestBody SupplyCreateDto supplyCreateDto) {
        supplyCreateDto.setCreatedBy(UserContextHolder.getUserId());
        supplyCreateDto.setCreatedDate(LocalDateTime.now());
        return supplyService.create(supplyCreateDto);
    }

    @PutMapping(value = "/update")
    public void update(@Valid @RequestBody SupplyUpdateDto supplyUpdateDto){
        supplyUpdateDto.setUpdatedBy(UserContextHolder.getUserId());
        supplyUpdateDto.setUpdatedDate(LocalDateTime.now());
        supplyService.update(supplyUpdateDto);
    }
}
