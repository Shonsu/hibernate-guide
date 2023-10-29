package pl.shonsu.hibernate.controller.dto;

import pl.shonsu.hibernate.entity.Attribute;
import pl.shonsu.hibernate.entity.ProductType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public record ProductDto(
        Long id,
        String name,
        String description,
        LocalDateTime created,
        LocalDateTime updated,
        BigDecimal price,
        ProductType productType,
        Set<ReviewDto> reviews,
        CategoryDto category,
        Set<AttributeDto>attributes
) {
}
