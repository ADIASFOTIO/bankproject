package it.adias.bankproject.model.services.implementations;

import it.adias.bankproject.model.dto.ContactDto;
import it.adias.bankproject.model.entities.Contact;
import it.adias.bankproject.model.repositories.ContactRepository;
import it.adias.bankproject.model.services.abstractions.ContactService;
import it.adias.bankproject.model.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {
    private final ContactRepository repository;
    private final ObjectsValidator<ContactDto> validator;

    @Override
    public Integer save(ContactDto dto) {
        validator.validate(dto);
        Contact contact = ContactDto.convertDtoToEntity(dto);
        return repository.save(contact).getId();
    }

    @Override
    public List<ContactDto> findAll() {
        return repository.findAll().stream()
                .map(ContactDto::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ContactDto findById(Integer id) {
        return repository.findById(id)
                .map(ContactDto::convertEntityToDto)
                .orElseThrow(()-> new EntityNotFoundException("No contact was found with the provided ID : " + id));
    }

    @Override
    public void delete(Integer id) {
     //TODO
        repository.deleteById(id);
    }

    @Override
    public List<ContactDto> findAllByUsrId(Integer userId) {
        return repository.findAllByUserId(userId)
                .stream()
                .map(ContactDto::convertEntityToDto)
                .collect(Collectors.toList());
    }
}
