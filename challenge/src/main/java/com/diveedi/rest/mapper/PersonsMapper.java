package com.diveedi.rest.mapper;

import com.diveedi.rest.dto.PersonDto;
import com.diveedi.storage.entity.TLCPERS;
import com.diveedi.storage.util.DateUtils;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PersonsMapper {

    public PersonDto fromEntity(TLCPERS entity) {
        PersonDto dto = new PersonDto();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setBirthDate(DateUtils.date8toDate(entity.getBirthDate()));

        return dto;
    }
}
