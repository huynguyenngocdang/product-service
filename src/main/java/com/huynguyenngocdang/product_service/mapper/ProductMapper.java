package com.huynguyenngocdang.product_service.mapper;

import com.huynguyenngocdang.product_service.dto.ProductResponse;
import com.huynguyenngocdang.product_service.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.math.BigDecimal;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {
    ProductResponse toProductResponse(Product product);
}
