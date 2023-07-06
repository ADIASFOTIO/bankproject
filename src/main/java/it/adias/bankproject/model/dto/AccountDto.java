package it.adias.bankproject.model.dto;

import it.adias.bankproject.model.entities.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDto {
    private Integer id;
    private String iban;
    private UserDto userDto;
    public static AccountDto convertEntityToDto(Account account){
        return AccountDto.builder()
                .id(account.getId())
                .iban(account.getIban())
                .userDto(UserDto.convertEntityToDto(account.getUser()))
                .build();

    }
    public static Account convertDtoToEntity(AccountDto accountDto){
        return Account.builder()
                .id(accountDto.getId())
                .iban(accountDto.getIban())
                .user(UserDto.convertDtoToEntity(accountDto.getUserDto()))
                .build();

    }
}
