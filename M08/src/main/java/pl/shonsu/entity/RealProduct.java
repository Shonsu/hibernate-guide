package pl.shonsu.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("REAL")
public class RealProduct extends BaseProduct {
    private float weight;
    private int width;
    private int length;
    private int height;

    public RealProduct() {
    }

    public RealProduct(Long id, String name, String description, LocalDateTime created, ProductType productType, float weight, int width, int length, int height) {
        super(id, name, description, created, productType);
        this.weight = weight;
        this.width = width;
        this.length = length;
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "RealProduct{" +
                "weight=" + weight +
                ", width=" + width +
                ", length=" + length +
                ", height=" + height +
                '}';
    }
}
