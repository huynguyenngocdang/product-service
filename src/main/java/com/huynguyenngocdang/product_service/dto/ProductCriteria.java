package com.huynguyenngocdang.product_service.dto;

import java.math.BigDecimal;

public record ProductCriteria(String keySearch, BigDecimal priceMin, BigDecimal priceMax) {
}
