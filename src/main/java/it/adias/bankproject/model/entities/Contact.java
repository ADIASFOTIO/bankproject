package it.adias.bankproject.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Contact extends AbstractEntity{
    private String firstname;
    private String lastname;
    private String email;
    private String iban;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
}
