package pl.shonsu;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import pl.shonsu.entity.batch.BatchReview;

import java.util.List;

public class App30BatchUpdateAndPaging {
    private static final Logger log = LogManager.getLogger(App30BatchUpdateAndPaging.class);
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();


        Long count = em.createQuery("SELECT COUNT(r) FROM BatchReview  r",
                Long.class).getSingleResult();

        int batchSize = 10;
        em.unwrap(Session.class).setJdbcBatchSize(batchSize);
        for (int i = 0; i < count; i = i + batchSize) {

            List<BatchReview> reviews = em.createQuery(
                            "SELECT r FROM BatchReview r",
                            BatchReview.class)
                    .setFirstResult(i)
                    .setMaxResults(batchSize)
                    .getResultList();
            log.info(reviews);
            for (BatchReview review : reviews) {
                review.setContent("Nowaaa Treść 22 !!!");
                review.setRating(2);
            }
            em.flush();
            em.clear();
        }

        em.getTransaction().commit();
        em.close();
    }
}
