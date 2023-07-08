package it.adias.bankproject.model.services.abstractions;

import it.adias.bankproject.model.dto.TransactionDto;

import java.util.List;

public interface TransactionService extends AbstractService<TransactionDto>{
    List<TransactionDto> findAllByUserId(Integer userId);
}
