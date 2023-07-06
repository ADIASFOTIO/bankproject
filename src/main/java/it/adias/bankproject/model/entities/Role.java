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
public class Role extends AbstractEntity{
    private String roleName;
    @ManyToOne()
    @JoinColumn(name = "id_user")
    private User user;

}
