package it.adias.bankproject.model.services.implementations;

import it.adias.bankproject.model.dto.AccountDto;
import it.adias.bankproject.model.dto.UserDto;
import it.adias.bankproject.model.entities.Account;
import it.adias.bankproject.model.entities.User;
import it.adias.bankproject.model.repositories.AccountRepository;
import it.adias.bankproject.model.repositories.UserRepository;
import it.adias.bankproject.model.services.abstractions.AccountService;
import it.adias.bankproject.model.services.abstractions.UserService;
import it.adias.bankproject.model.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final ObjectsValidator<UserDto> validator;
    private final AccountService accountService;
    @Override
    public Integer save(UserDto dto) {
        validator.validate(dto);
        return repository.save(UserDto.convertDtoToEntity(dto)).getId();
    }
    @Override
    public List<UserDto> findAll() {
        return repository.findAll().stream()
                .map(UserDto::convertEntityToDto)
                .collect(Collectors.toList());
    }
    @Override
    public UserDto findById(Integer id) {
        return repository.findById(id)
                .map(UserDto::convertEntityToDto)
                .orElseThrow(()-> new EntityNotFoundException("No user was found with the provided ID : " + id));
    }
    @Override
    public void delete(Integer id) {
        //TODO
        repository.deleteById(id);
    }

    @Override
    public Integer validateAccount(Integer id) {
        User user = repository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("No user was found for user account validation"));
        user.setActive(true);
        // adesso poso creare un conto
        // uso accountService visto che c'è già la logica per generare l'iban
        AccountDto account = AccountDto.builder()
                .userDto(UserDto.convertEntityToDto(user))
                .build();
        accountService.save(account);
        repository.save(user);
        return user.getId();
    }

    @Override
    public Integer invalidateAccount(Integer id) {
        User user = repository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("No user was found for user account validation"));
        user.setActive(false);
        repository.save(user);
        return user.getId();
    }
}
