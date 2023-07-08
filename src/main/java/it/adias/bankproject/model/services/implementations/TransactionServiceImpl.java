package it.adias.bankproject.model.services.implementations;

import it.adias.bankproject.model.dto.TransactionDto;
import it.adias.bankproject.model.entities.Transaction;
import it.adias.bankproject.model.entities.TransactionType;
import it.adias.bankproject.model.repositories.TransactionRepository;
import it.adias.bankproject.model.services.abstractions.TransactionService;
import it.adias.bankproject.model.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository repository;
    private final ObjectsValidator<TransactionDto> validator;
    @Override
    public Integer save(TransactionDto dto) {
        validator.validate(dto);
        Transaction transaction = TransactionDto.convertDtoToEntity(dto);
        transaction.setAmount(transaction.getAmount().multiply(
                BigDecimal.valueOf(transactionMultiplier(transaction.getType()))
        ));
        return repository.save(transaction).getId();
    }

    @Override
    public List<TransactionDto> findAll() {
        return repository.findAll().stream()
                .map(TransactionDto::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public TransactionDto findById(Integer id) {
        return repository.findById(id)
                .map(TransactionDto::convertEntityToDto)
                .orElseThrow(()-> new EntityNotFoundException("No transaction was found with the provided ID : " + id));
    }

    @Override
    public void delete(Integer id) {
        //TODO
        repository.deleteById(id);
    }
    private int transactionMultiplier(TransactionType type){
        return TransactionType.TRANSFERT == type ? -1 : 1;
    }

    @Override
    public List<TransactionDto> findAllByUserId(Integer userId) {
        return repository.findAllByUserId(userId)
                .stream()
                .map(TransactionDto::convertEntityToDto)
                .collect(Collectors.toList());
    }
}
