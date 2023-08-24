package pl.shonsu;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.shonsu.entity.Product;

import java.util.List;

public class App22CartesianProduct {
    private static final Logger log = LogManager.getLogger(App22CartesianProduct.class);
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        List<Product> result = em.createQuery(
                "SELECT DISTINCT p FROM Product p " +
                        "LEFT JOIN FETCH p.attributes ",
                Product.class
        ).getResultList();

        result = em.createQuery(
                        "SELECT DISTINCT p FROM Product p " +
                                "LEFT JOIN FETCH p.reviews ",
                        Product.class
                )
                .getResultList();

        log.info("Result size: " + result.size());
        for (Product product : result) {
            log.info(product);
            log.info(product.getAttributes());
            log.info(product.getReviews());
        }

        em.getTransaction().commit();
        em.close();
    }
}
