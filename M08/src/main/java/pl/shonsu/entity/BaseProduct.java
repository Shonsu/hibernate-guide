package pl.shonsu.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "base_product")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "product_type", discriminatorType = DiscriminatorType.STRING)
public abstract class BaseProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private LocalDateTime created;
    @Enumerated(EnumType.STRING)
    @Column(name = "product_type", insertable = false, updatable = false)
    private ProductType productType;

    public BaseProduct() {
    }

    public BaseProduct(Long id, String name, String description, LocalDateTime created, ProductType productType) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created = created;
        this.productType = productType;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public ProductType getProductType() {
        return productType;
    }
}
