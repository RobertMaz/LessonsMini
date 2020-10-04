package com.innopolis.repositories.specifications;

import com.innopolis.entity.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {
    public static Specification<Product> costGEThan(int minCost) {
        return (Specification<Product>) (root, criterialQuery, criterialBuilder)
                -> criterialBuilder.greaterThanOrEqualTo(root.get("cost"), minCost);
    }
}
