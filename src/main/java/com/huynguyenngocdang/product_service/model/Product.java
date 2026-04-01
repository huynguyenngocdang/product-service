package com.huynguyenngocdang.product_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(value = "product")
@FieldNameConstants
public class Product {
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    private BigDecimal quantity;
}
