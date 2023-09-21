package pl.shonsu;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.jpa.AvailableHints;
import pl.shonsu.entity.batch.BatchReview;

public class App31BatchUpdateScrolling {
    private static final Logger log = LogManager.getLogger(App31BatchUpdateScrolling.class);
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        em.createQuery(
                        "SELECT r FROM BatchReview r",
                        BatchReview.class)
                .setHint(AvailableHints.HINT_FETCH_SIZE, Integer.MIN_VALUE)
                .getResultStream()
                .forEach(batchReview -> {
                    batchReview.setContent("contsdent");
                    batchReview.setRating(2);
                    log.info(batchReview);
                });

        em.getTransaction().commit();
        em.close();
    }
}
