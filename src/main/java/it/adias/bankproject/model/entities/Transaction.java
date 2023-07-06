package it.adias.bankproject.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

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
    @ManyToOne()
    @JoinColumn(name = "id_user")
    private User user;
}
