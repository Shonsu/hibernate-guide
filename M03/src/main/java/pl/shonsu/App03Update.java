package pl.shonsu;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.shonsu.entity.Product;

public class App03Update {
    private static final Logger log = LogManager.getLogger(App03Update.class);
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Product product = em.find(Product.class, 2L);
        log.info(product);
        product.setName("Nowy rower");
//        Product product = new Product();
//        product.setId(1L);
//        product.setName("SUPER NOWY ROWER");
        Product merged = em.merge(product);
        log.info(merged);
        em.getTransaction().commit();
        em.close();
    }
}
