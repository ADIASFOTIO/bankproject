package it.adias.bankproject.model.services.abstractions;

import it.adias.bankproject.model.dto.UserDto;

public interface UserService extends AbstractService<UserDto>{
    Integer validateAccount(Integer id);
    Integer invalidateAccount(Integer id);
}
