package pl.shonsu;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.shonsu.entity.Attribute;

public class App15DeleteManyToMany {
    private static final Logger log = LogManager.getLogger(App15DeleteManyToMany.class);
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        //Product product = em.getReference(Product.class, 5L);
        //em.remove(product);
        //product.getAttributes().clear();

        Attribute attribute = em.find(Attribute.class, 1L);
        attribute.getProducts().stream().toList().forEach(attribute::removeProduct);
        em.remove(attribute);

        em.getTransaction().commit();
        em.close();
    }
}
