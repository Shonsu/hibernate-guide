package pl.shonsu;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.shonsu.entity.Product;

import java.text.MessageFormat;

public class App14DeleteOneToOne {
    private static final Logger log = LogManager.getLogger(App14DeleteOneToOne.class);
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Product product = em.getReference(Product.class, 3L);
        if (product.getCategory().getProducts().size() == 1) {
            log.info(MessageFormat.format("Deleting {0}.", product.getCategory().getName()));
            em.remove(product.getCategory());
        }
        product.setCategory(null);

        em.getTransaction().commit();
        em.close();
    }
}
