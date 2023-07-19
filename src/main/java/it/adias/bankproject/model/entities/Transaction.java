package it.adias.bankproject.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction extends AbstractEntity{
    private BigDecimal amount;
    private String destinationIban;
    @Enumerated(EnumType.STRING)
    private TransactionType type;
    @Column(updatable = false)
    private LocalDate transactionDate;
    @ManyToOne()
    @JoinColumn(name = "id_user")
    private User user;
}
