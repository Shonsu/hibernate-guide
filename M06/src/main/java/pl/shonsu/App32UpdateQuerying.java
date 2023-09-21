package pl.shonsu;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App32UpdateQuerying {
    private static final Logger log = LogManager.getLogger(App32UpdateQuerying.class);
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        int updated = em.createQuery(
                        "UPDATE Review r SET rating=:rating " +
                                "WHERE r.product.id=:id"
                )
                .setParameter("rating", 3)
                .setParameter("id", 1L)
                .executeUpdate();
        log.info("Updated: " + updated);

        em.getTransaction().commit();
        em.close();
    }
}
