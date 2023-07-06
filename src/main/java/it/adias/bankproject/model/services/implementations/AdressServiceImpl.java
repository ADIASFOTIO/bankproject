package it.adias.bankproject.model.services.implementations;

import it.adias.bankproject.model.dto.AdressDto;
import it.adias.bankproject.model.entities.Adress;
import it.adias.bankproject.model.repositories.AdressRepository;
import it.adias.bankproject.model.services.abstractions.AdressService;
import it.adias.bankproject.model.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdressServiceImpl implements AdressService {
    private final AdressRepository repository;
    private final ObjectsValidator<AdressDto> validator;

    @Override
    public Integer save(AdressDto dto) {
        validator.validate(dto);
        Adress adress = AdressDto.convertDtoToEntity(dto);
        return repository.save(adress).getId();
    }

    @Override
    public List<AdressDto> findAll() {
        return repository.findAll().stream()
                .map(AdressDto::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public AdressDto findById(Integer id) {

        return repository.findById(id)
                .map(AdressDto::convertEntityToDto)
                .orElseThrow(()-> new  EntityNotFoundException("No adress was found with the provided ID : " + id));
    }

    @Override
    public void delete(Integer id) {
        //TODO
        repository.deleteById(id);
    }
}
