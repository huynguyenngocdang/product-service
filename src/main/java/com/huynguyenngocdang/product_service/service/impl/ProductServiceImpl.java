package com.huynguyenngocdang.product_service.service.impl;

import com.huynguyenngocdang.commons.common.PageResponse;
import com.huynguyenngocdang.product_service.dto.ProductCriteria;
import com.huynguyenngocdang.product_service.dto.ProductRequest;
import com.huynguyenngocdang.product_service.dto.ProductResponse;
import com.huynguyenngocdang.product_service.mapper.ProductMapper;
import com.huynguyenngocdang.product_service.model.Product;
import com.huynguyenngocdang.product_service.repository.ProductRepository;
import com.huynguyenngocdang.product_service.service.ProductService;
import com.huynguyenngocdang.product_service.specification.ProductSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final MongoTemplate mongoTemplate;

    @Override
    public ProductResponse findById(String id) {
        log.info("Find product by id: {}", id);
        return productMapper.toProductResponse(productRepository.findById(id).orElseThrow());
    }

    @Override
    public PageResponse<ProductResponse> findByCriteria(ProductCriteria criteria, Pageable pageable) {
        Query query = ProductSpecification.buildQuery(criteria);
        log.info("Find products by criteria: {}", query);
        List<Product> products = mongoTemplate.find(query.with(pageable), Product.class);
        long total = mongoTemplate.count(Query.of(query).limit(-1).skip(-1), Product.class);
        Page<Product> page = new PageImpl<>(products, pageable, total);
        return PageResponse.of(page, productMapper::toProductResponse);
    }

    @Override
    public ProductResponse save(ProductRequest request) {
        return productMapper.toProductResponse(productRepository.save(Product.builder()
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .quantity(request.quantity())
                .build()));
    }

    @Override
    public ProductResponse update(String id, ProductRequest request) {
        Query query = Query.query(Criteria.where(Product.Fields.id).is(id));
        Update update = new Update()
                .set(Product.Fields.name, request.name())
                .set(Product.Fields.description, request.description())
                .set(Product.Fields.price, request.price())
                .set(Product.Fields.quantity, request.quantity());
        Product product = mongoTemplate.findAndModify(query, update, FindAndModifyOptions.options().returnNew(true), Product.class);
        if(Objects.isNull(product)) throw new RuntimeException("Product not found");
        return productMapper.toProductResponse(product);
    }

    @Override
    public void delete(String id) {
        Product product = productRepository.findById(id).orElseThrow();
        productRepository.delete(product);
    }
}
