package pl.shonsu.hibernate.dao;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Component;
import pl.shonsu.hibernate.entity.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductDao {

    private final EntityManager em;

    public ProductDao(EntityManager em) {
        this.em = em;
    }

    public Product getProduct(Long id) {
        EntityGraph entityGraph = em.getEntityGraph("product");
        Map<String, Object> props = new HashMap<>();
        props.put("jakarta.persistence.fetchgraph", entityGraph);

        return em.find(Product.class, id, props);
    }

    public List<Product> getProducts() {
        EntityGraph entityGraph = em.getEntityGraph("product");
        List<Product> products = em.createQuery("SELECT p FROM Product p", Product.class)
                .setHint("jakarta.persistence.fetchgraph", entityGraph)
                .getResultList();
        return products;
    }
}
