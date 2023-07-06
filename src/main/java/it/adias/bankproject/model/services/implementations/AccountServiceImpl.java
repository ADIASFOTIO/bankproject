package it.adias.bankproject.model.services.implementations;

import it.adias.bankproject.model.dto.AccountDto;
import it.adias.bankproject.model.entities.Account;
import it.adias.bankproject.model.exceptions.OperationNonPermittedException;
import it.adias.bankproject.model.repositories.AccountRepository;
import it.adias.bankproject.model.services.abstractions.AccountService;
import it.adias.bankproject.model.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository repository;
    private final ObjectsValidator<AccountDto> validator;

    @Override
    public Integer save(AccountDto dto) {
        // impedisce di cambiare l'iban
        validator.validate(dto);
        if (dto.getId() != null){
            throw new OperationNonPermittedException(
                    "non puo modificare un account",
                    "save account",
                    "Account",
                    "impossibile modificare un account"
            );
        }
        Account account = AccountDto.convertDtoToEntity(dto);
        //prima di fare il save devo verificare che non c'è un user con lo stesso Account uguale
        boolean userHasAlreadyAccount = repository.findByUserId(account.getUser().getId()).isPresent();
        if (userHasAlreadyAccount){
            throw  new OperationNonPermittedException(
                    "Questo conto appartiene già ad un user",
                    "Create account",
                    "AccountService",
                    "Creazione dell'account"
            );
        }
        // generate random iban
        account.setIban(generateRandomIban());
        return repository.save(account).getId();
    }

    @Override
    public List<AccountDto> findAll() {
        return repository.findAll()
                .stream()
                .map(AccountDto::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public AccountDto findById(Integer id) {
        return repository.findById(id)
                .map(AccountDto::convertEntityToDto)
                .orElseThrow(()-> new EntityNotFoundException("No account was found with the ID :" + id));
    }

    @Override
    public void delete(Integer id) {
        //TODO
        repository.deleteById(id);
    }
    // generate random iban
    private String generateRandomIban(){
        String iban = Iban.random(CountryCode.IT).toFormattedString();
       boolean ibanExist = repository.findByIban(iban).isPresent();
         if (ibanExist){
             generateRandomIban();
         }
        return iban;
    }
}
