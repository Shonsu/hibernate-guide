package pl.shonsu;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.shonsu.entity.batch.BatchReview;

import java.util.List;

public class App33BatchDelete {
    private static final Logger log = LogManager.getLogger(App33BatchDelete.class);
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        List<BatchReview> reviews = em.createQuery(
                        "SELECT r FROM BatchReview r " +
                                "WHERE r.productId=:id",
                        BatchReview.class
                )
                .setParameter("id", 1L)
                .getResultList();
        for (BatchReview review : reviews) {
            log.info("Updated: " + review);
            em.remove(review);
        }

        em.getTransaction().commit();
        em.close();
    }
}
