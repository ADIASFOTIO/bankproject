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
public class Adress extends AbstractEntity{
    private String street;
    private Integer houseNumber;
    private Integer cap;
    private String city;
    private String country;
    @ManyToOne()
    @JoinColumn(name = "id_user")
    private User user;
}
