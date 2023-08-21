package pl.shonsu;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.shonsu.entity.Product;

import java.util.List;

public class App02Read {
    private static final Logger log = LogManager.getLogger(App02Read.class);
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Product product = em.find(Product.class, 1L);
        List<Product> products = em.createQuery("from Product").getResultList();
        log.info(products);
        log.info(product);
        em.getTransaction().commit();
        em.close();
    }
}
