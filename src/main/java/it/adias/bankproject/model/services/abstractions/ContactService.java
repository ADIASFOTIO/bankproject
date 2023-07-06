package it.adias.bankproject.model.services.abstractions;

import it.adias.bankproject.model.dto.ContactDto;
import it.adias.bankproject.model.entities.Contact;

import java.util.List;

public interface ContactService extends AbstractService<ContactDto>{
    List<ContactDto> findAllByUsrId(Integer userId);
}
