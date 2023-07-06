package it.adias.bankproject.model.repositories;

import it.adias.bankproject.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAllByFirstname(String firstname);
    List<User> findAllByFirstnameContaining(String part);
    List<User> findAllByFirstnameContainingIgnoreCase(String part);
    List<User> findAllByAccountIban(String iban);
    User findByFirstnameContainingIgnoreCaseAndEmail(String firstname, String email);
    @Query("from User where firstname = :fn")
    List<User> searchByFirstname(@Param("fn") String firstname);
    @Query("from User where firstname = '%:firstname%'")
    List<User> searchByFirstnameContaining(@Param("fn") String firstname);
    @Query("from User u inner join Account a on u.id = a.user.id where a.iban = :iban")
    List<User> searchByIban(String iban);
    // requete SQL native
    @Query(value = "select * from user u inner join account a on u.id = a.id_user where a.iban = :iban",nativeQuery = true)
    List<User> searchByIbanTwo(String iban);

}
