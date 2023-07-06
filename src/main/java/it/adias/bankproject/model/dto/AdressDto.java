package it.adias.bankproject.model.dto;

import it.adias.bankproject.model.entities.Adress;
import it.adias.bankproject.model.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdressDto {
    private Integer id;
    private Integer userId;
    private String street;
    private Integer houseNumber;
    private Integer cap;
    private String city;
    private String country;
    public static AdressDto convertEntityToDto(Adress adress){
        return AdressDto.builder()
                .id(adress.getId())
                .street(adress.getStreet())
                .houseNumber(adress.getHouseNumber())
                .cap(adress.getCap())
                .city(adress.getCity())
                .country(adress.getCountry())
                .userId(adress.getUser().getId())
                .build();
    }
    public static Adress convertDtoToEntity(AdressDto adressDto){
        return Adress.builder()
                .id(adressDto.getId())
                .street(adressDto.getStreet())
                .houseNumber(adressDto.getHouseNumber())
                .cap(adressDto.getCap())
                .city(adressDto.getCity())
                .country(adressDto.getCountry())
                .user(User.builder()
                        .id(adressDto.getUserId())
                        .build()
                )
                .build();
    }
}
