package com.huynguyenngocdang.product_service.specification;

import com.huynguyenngocdang.product_service.dto.ProductCriteria;
import com.huynguyenngocdang.product_service.model.Product;
import lombok.experimental.UtilityClass;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class ProductSpecification {
    public static Query buildQuery(ProductCriteria criteria) {
        List<Criteria> filters = new ArrayList<>();
        if(StringUtils.hasText(criteria.keySearch())){
            String pattern = criteria.keySearch().trim();
            filters.add(new Criteria().orOperator(
                    Criteria.where(Product.Fields.name).regex(pattern, "i"),
                    Criteria.where(Product.Fields.description).regex(pattern, "i")
            ));
        }
        if(criteria.priceMin() !=null) filters.add(Criteria.where(Product.Fields.price).gte(criteria.priceMin()));
        if(criteria.priceMax() !=null) filters.add(Criteria.where(Product.Fields.price).lte(criteria.priceMax()));
        Criteria combined = filters.isEmpty() ? new Criteria() : new Criteria().andOperator(filters.toArray(new Criteria[0]));
        return new Query(combined);
    }
}
