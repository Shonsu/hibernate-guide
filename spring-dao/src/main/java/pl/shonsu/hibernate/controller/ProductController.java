package pl.shonsu.hibernate.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.shonsu.hibernate.controller.dto.AttributeDto;
import pl.shonsu.hibernate.controller.dto.CategoryDto;
import pl.shonsu.hibernate.controller.dto.ProductDto;
import pl.shonsu.hibernate.controller.dto.ReviewDto;
import pl.shonsu.hibernate.entity.Product;
import pl.shonsu.hibernate.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products/{id}")
    ProductDto getOrder(@PathVariable Long id) {
        Product product = productService.getProduct(id);
        ProductDto productDto = getProductDto(product);
        return productDto;
    }

    @GetMapping("/products")
    List<ProductDto> getOrders() {
        return productService.getProducts().stream().map(ProductController::getProductDto).toList();
    }

    private static ProductDto getProductDto(Product product) {
        return new ProductDto(product.getId(), product.getName(),
                product.getDescription(),
                product.getCreated(),
                product.getUpdated(),
                product.getPrice(),
                product.getProductType(),
                product.getReviews().stream()
                        .map(review -> new ReviewDto(
                                review.getId(),
                                review.getContent(),
                                review.getRating()))
                        .collect(Collectors.toSet()),
                new CategoryDto(
                        product.getCategory().getId(),
                        product.getCategory().getName(),
                        product.getCategory().getDescription()),
                product.getAttributes().stream()
                        .map(attribute -> new AttributeDto(
                                attribute.getId(),
                                attribute.getName(),
                                attribute.getValue()))
                        .collect(Collectors.toSet())

        );
    }
}
