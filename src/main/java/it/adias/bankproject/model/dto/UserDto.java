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
    @NotNull
    @NotEmpty
    @NotBlank
    private String firstname;
    @NotNull
    @NotEmpty
    @NotBlank
    private String lastname;
    @NotNull
    @NotEmpty
    @NotBlank
    private String cf;
    @NotNull
    @NotEmpty
    @NotBlank
    @Email
    private String email;
    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min = 8, max = 16)
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
