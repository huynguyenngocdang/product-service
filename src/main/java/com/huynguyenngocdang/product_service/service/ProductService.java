package com.huynguyenngocdang.product_service.service;

import com.huynguyenngocdang.product_service.common.PageResponse;
import com.huynguyenngocdang.product_service.dto.ProductCriteria;
import com.huynguyenngocdang.product_service.dto.ProductRequest;
import com.huynguyenngocdang.product_service.dto.ProductResponse;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    ProductResponse findById(String id);
    PageResponse<ProductResponse> findByCriteria(ProductCriteria criteria, Pageable pageable);
    ProductResponse save(ProductRequest request);
    ProductResponse update(String id, ProductRequest request);
    void delete(String id);
}
