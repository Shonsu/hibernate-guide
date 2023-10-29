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
        EntityGraph entityGraph = em.getEntityGraph("product-reviews");
        EntityGraph entityGraph2 = em.getEntityGraph("product-attributes");
        EntityGraph entityGraph3 = em.getEntityGraph("product-category");
//        EntityGraph entityGraphAll = em.getEntityGraph("product-all");

        List<Product> products = em.createQuery("SELECT p FROM Product p", Product.class)
                .setHint("jakarta.persistence.fetchgraph", entityGraph)
                .getResultList();
        products = em.createQuery("SELECT p FROM Product p", Product.class)
                .setHint("jakarta.persistence.fetchgraph", entityGraph2)
                .getResultList();
        products = em.createQuery("SELECT p FROM Product p", Product.class)
                .setHint("jakarta.persistence.fetchgraph", entityGraph3)
                .getResultList();
//        List<Product> allProducts = em.createQuery("SELECT p FROM Product p", Product.class)
//                .setHint("jakarta.persistence.fetchgraph", entityGraphAll)
//                .getResultList();
        return products;
    }
}
