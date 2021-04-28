package az.turbo.backend.supplies;

import az.turbo.backend.shared.UserContextHolder;
import az.turbo.backend.supplies.application.SupplyService;
import az.turbo.backend.supplies.application.dto.SupplyCreateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/supplies")
public class SupplyController {
    private SupplyService supplyService;

    @Autowired
    public SupplyController(SupplyService supplyService) {
        this.supplyService = supplyService;
    }

    @PostMapping(value = "create")
    public long create(@Valid @RequestBody SupplyCreateDto supplyCreateDto) {
        supplyCreateDto.setCreatedBy(UserContextHolder.getUserId());
        supplyCreateDto.setCreatedDate(LocalDateTime.now());
        return supplyService.create(supplyCreateDto);
    }
}
