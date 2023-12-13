package com.diveedi.rest.pagination;

import io.github.ulisse1996.jaorm.dsl.query.common.trait.WithPage;
import io.github.ulisse1996.jaorm.entity.Page;

import java.util.function.Function;
import java.util.stream.Collectors;

public class PageBuilder {
    private static final int DEFAULT_PAGE_SIZE = 25;

    public static <T, R> PaginatedResponse<T> build(WithPage<R> selected, Function<R, T> mapper, int page) {
        return build(selected, mapper, page, DEFAULT_PAGE_SIZE);
    }

    public static <T,R> PaginatedResponse<T> build(WithPage<R> selected, Function<R, T> mapper, int page, int perPage) {
        Page<R> paged = selected.page(page, perPage);

        long totalItems = paged.getCount();
        long totalPages = (totalItems + perPage - 1) / perPage;

        return new PaginatedResponse<>(
                paged.getData().stream().map(mapper).collect(Collectors.toList()),
                page,
                totalPages,
                totalItems
        );
    }
}
