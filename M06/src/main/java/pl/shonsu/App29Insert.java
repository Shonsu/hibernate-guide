package pl.shonsu;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.shonsu.entity.batch.BatchReview;

public class App29Insert {
    private static final Logger log = LogManager.getLogger(App29Insert.class);
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Long lastId = em.createQuery("SELECT MAX(r.id) FROM BatchReview r", Long.class).getSingleResult();

        for (long i = 1; i <= 25; i++) {
            em.persist(new BatchReview(lastId + i, "Treść", 5, 1L));
        }

        em.getTransaction().commit();
        em.close();
    }
}