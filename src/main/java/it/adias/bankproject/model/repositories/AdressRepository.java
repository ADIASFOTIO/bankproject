package it.adias.bankproject.model.repositories;

import it.adias.bankproject.model.entities.Adress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdressRepository extends JpaRepository<Adress, Integer> {
}
