package com.huynguyenngocdang.product_service.common;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;

public record PageResponse<T>(List<T> content, PageMetadata metadata) {
    public static <T> PageResponse <T> of(Page<T> page) {
        return new PageResponse<>(page.getContent(), PageMetadata.from(page));
    }

    public static <S, T> PageResponse<T> of(Page<S> page, Function<S, T> mapper) {
        return new PageResponse<>(page.getContent().stream().map(mapper).toList(), PageMetadata.from(page));
    }
}
