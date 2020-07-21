package com.geekbrains.july.market.repositories.specifications;

import com.geekbrains.july.market.entities.Category;
import com.geekbrains.july.market.entities.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecifications {
    public static Specification<Product> priceGreaterOrEqualsThan(int minPrice) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
    }

    public static Specification<Product> priceLesserOrEqualsThan(int maxPrice) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
    }

    public static Specification<Product> titleContainsFollowingExpression(String title) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.get("title"), "%" + title + "%");
    }

    public static Specification<Product> categoryIs(Category category){
        return (root , criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.isMember(category, root.get("categories"));
        };
    }
}
