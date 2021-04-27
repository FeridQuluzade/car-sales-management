package az.turbo.backend.supplies.application;

import az.turbo.backend.supplies.application.dto.SupplyCreateDto;
import az.turbo.backend.supplies.application.dto.SupplyDto;
import az.turbo.backend.supplies.application.dto.SupplyUpdateDto;
import az.turbo.backend.supplies.application.exception.SupplyNotFoundException;
import az.turbo.backend.supplies.domain.SupplyRepository;
import az.turbo.backend.supplies.domain.model.Supply;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<SupplyDto> retrieveAll() {
        return supplyRepository
                .findAll()
                .stream()
                .map(supply -> modelMapper.map(supply,SupplyDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public SupplyUpdateDto retrieveById(long id) {
      Supply supply=supplyRepository
              .findById(id)
              .orElseThrow(()->new SupplyNotFoundException("Supply not found! by Id"));

      return modelMapper.map(supply,SupplyUpdateDto.class);
    }

    @Override
    public long create(SupplyCreateDto supplyCreateDto) {
        Supply supply = modelMapper.map(supplyCreateDto, Supply.class);
        return supplyRepository.create(supply);
    }

    @Override
    public void update(SupplyUpdateDto supplyUpdateDto) {
        Supply supply=modelMapper.map(supplyUpdateDto,Supply.class);
        supplyRepository.update(supply);
    }
}
