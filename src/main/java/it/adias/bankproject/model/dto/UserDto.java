package it.adias.bankproject.model.dto;

import it.adias.bankproject.model.entities.User;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Integer id;
    @NotNull(message = "il firstname non può essere null")
    @NotEmpty(message = "il firstname non può essere vuoto")
    @NotBlank(message = "il firstname non può essere null")
    private String firstname;
    @NotNull(message = "il lastname non può essere null")
    @NotEmpty(message = "il lastname non può essere vuoto")
    @NotBlank(message = "il lastname non può essere null")
    private String lastname;
    @NotNull(message = "il cf non può essere null")
    @NotEmpty(message = "il cf non può essere vuoto")
    @NotBlank(message = "il cf non può essere null")
    private String cf;
    @NotNull(message = "l'email non può esser null")
    @NotEmpty(message = "l'email non può esser vuoto")
    @NotBlank(message = "l'email non può esser null")
    @Email(message = "l'email non è conforme")
    private String email;
    @NotNull(message = "la password non può essere null")
    @NotEmpty(message = "la password non può essere vuoto")
    @NotBlank(message = "la password non può essere null")
    @Size(min = 8, max = 16, message = "la password deve avere un numero de caratteri compreso tra 8 e 16")
    private String password;
    public static UserDto convertEntityToDto(User user){
        return UserDto.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .cf(user.getCf())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }
    public static User convertDtoToEntity(UserDto userDto){
        return User.builder()
                .id(userDto.getId())
                .firstname(userDto.getFirstname())
                .lastname(userDto.getLastname())
                .cf(userDto.getCf())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();
    }
}
