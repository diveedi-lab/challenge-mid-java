package com.diveedi.rest.service;

import com.diveedi.rest.dto.PersonDto;
import com.diveedi.rest.mapper.PersonsMapper;
import com.diveedi.rest.pagination.PageBuilder;
import com.diveedi.rest.pagination.PaginatedResponse;
import com.diveedi.storage.entity.TLCPERS;
import io.github.ulisse1996.jaorm.dsl.query.QueryBuilder;
import io.github.ulisse1996.jaorm.dsl.query.common.Selected;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class PersonsService {
    @Inject
    private PersonsMapper mapper;

    public PaginatedResponse<PersonDto> getPersons(int page) {
        Selected<TLCPERS> tlcpers = QueryBuilder.select(TLCPERS.class);

        return PageBuilder.build(
                tlcpers,
                mapper::fromEntity,
                page
        );
    }
}
