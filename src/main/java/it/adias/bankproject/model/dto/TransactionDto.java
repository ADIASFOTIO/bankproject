package it.adias.bankproject.model.dto;

import it.adias.bankproject.model.entities.Transaction;
import it.adias.bankproject.model.entities.TransactionType;
import it.adias.bankproject.model.entities.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionDto {
    private Integer id;
    @Positive
    @Max(value = 1000000)
    @Min(value = 10)
    private BigDecimal amount;
    private String destinationIban;
    private TransactionType type;
    private LocalDate transactionDate;
    private Integer userId;
    public static TransactionDto convertEntityToDto(Transaction transaction){
        return TransactionDto.builder()
                .id(transaction.getId())
                .transactionDate(transaction.getTransactionDate())
                .amount(transaction.getAmount())
                .destinationIban(transaction.getDestinationIban())
                .type(transaction.getType())
                .userId(transaction.getUser().getId())
                .build();
    }
    public static Transaction convertDtoToEntity(TransactionDto transactionDto){
        return Transaction.builder()
                .id(transactionDto.getId())
                .amount(transactionDto.getAmount())
                .transactionDate(LocalDate.now())
                .destinationIban(transactionDto.getDestinationIban())
                .type(transactionDto.getType())
                .user(User.builder()
                .id(transactionDto.getUserId())
                                .build()
                )
                .build();
    }
}
