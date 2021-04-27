package az.turbo.backend.supplies.application;

import az.turbo.backend.supplies.application.dto.SupplyCreateDto;
import az.turbo.backend.supplies.domain.SupplyRepository;
import az.turbo.backend.supplies.domain.model.Supply;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplyServiceImpl implements SupplyService {

    private SupplyRepository supplyRepository;
    private ModelMapper modelMapper;

    @Autowired
    public SupplyServiceImpl(SupplyRepository supplyRepository, ModelMapper modelMapper) {
        this.supplyRepository = supplyRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public long create(SupplyCreateDto supplyCreateDto) {
        Supply supply = modelMapper.map(supplyCreateDto, Supply.class);
        return supplyRepository.create(supply);
    }
}
