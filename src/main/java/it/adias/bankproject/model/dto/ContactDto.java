package it.adias.bankproject.model.dto;

import it.adias.bankproject.model.entities.Contact;
import it.adias.bankproject.model.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactDto {
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String iban;
    private Integer userId;
    public static ContactDto convertEntityToDto(Contact contact){
        return ContactDto.builder()
                .id(contact.getId())
                .firstname(contact.getFirstname())
                .lastname(contact.getLastname())
                .email(contact.getEmail())
                .iban(contact.getIban())
                .userId(contact.getUser().getId())
                .build();
    }
    public static Contact convertDtoToEntity(ContactDto contactDto){
        return Contact.builder()
                .id(contactDto.getId())
                .firstname(contactDto.getFirstname())
                .lastname(contactDto.getLastname())
                .email(contactDto.getEmail())
                .iban(contactDto.getIban())
                .user(
                        User.builder()
                                .id(contactDto.getId())
                                .build()
                )
                .build();
    }
}
