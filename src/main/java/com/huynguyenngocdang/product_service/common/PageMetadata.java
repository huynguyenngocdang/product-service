package com.huynguyenngocdang.product_service.common;

import org.springframework.data.domain.Page;

public record PageMetadata(
        Integer pageNumber,
        Integer pageSize,
        Long totalElements,
        Integer totalPages,
        Boolean isLast
) {
    public static PageMetadata from(Page<?> page) {
        return new PageMetadata(
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isLast());
    }
}
