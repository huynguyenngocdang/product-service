package com.huynguyenngocdang.product_service.controller;


import com.huynguyenngocdang.commons.common.PageResponse;
import com.huynguyenngocdang.commons.common.ResponseApi;
import com.huynguyenngocdang.product_service.dto.ProductCriteria;
import com.huynguyenngocdang.product_service.dto.ProductRequest;
import com.huynguyenngocdang.product_service.dto.ProductResponse;
import com.huynguyenngocdang.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseApi<ProductResponse>> findById(@PathVariable String id) {
        return ResponseEntity.ok(ResponseApi.success(productService.findById(id)));
    }

    @GetMapping
    public ResponseEntity<PageResponse<ProductResponse>> findByCriteria(
            @RequestParam(required = false) String keySearch,
            @RequestParam(required = false) BigDecimal priceMin,
            @RequestParam(required = false) BigDecimal priceMax,
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize
    ) {
        ProductCriteria criteria = new ProductCriteria(keySearch, priceMin, priceMax);
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return ResponseEntity.ok(productService.findByCriteria(criteria, pageable));
    }

    @PostMapping
    public ResponseEntity<ResponseApi<ProductResponse>> save(@RequestBody ProductRequest request) {
        return ResponseEntity.ok(ResponseApi.success(productService.save(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseApi<ProductResponse>> update(@PathVariable String id, @RequestBody ProductRequest request) {
        return ResponseEntity.ok(ResponseApi.success(productService.update(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseApi<Void>> delete(@PathVariable String id) {
        productService.delete(id);
        return ResponseEntity.ok(ResponseApi.success(null));
    }
}
