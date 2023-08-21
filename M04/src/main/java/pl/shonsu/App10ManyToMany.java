package pl.shonsu;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.shonsu.entity.Attribute;
import pl.shonsu.entity.Product;

public class App10ManyToMany {
    private static final Logger log = LogManager.getLogger(App10ManyToMany.class);
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Product product = em.find(Product.class, 3L);
        log.info(product);
        log.info(product.getAttributes());

        log.info("-== by Attribute =-");

        Attribute attribute = em.find(Attribute.class, 3L);
        log.info(attribute);
        log.info(attribute.getProducts());


        em.getTransaction().commit();
        em.close();
    }
}
