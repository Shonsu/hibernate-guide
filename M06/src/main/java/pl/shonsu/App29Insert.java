package pl.shonsu;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import pl.shonsu.entity.batch.BatchReview;

public class App29Insert {
    private static final Logger log = LogManager.getLogger(App29Insert.class);
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
       // em.unwrap(Session.class).setJdbcBatchSize(5);
        Long lastId = em.createQuery("SELECT MAX(r.id) FROM BatchReview r", Long.class).getSingleResult();
        System.out.println(lastId);
        for (long i = 1; i <= 25; i++) {
            if (i % 5 == 0) {
                em.flush();
                em.clear();
            }
            em.persist(new BatchReview(lastId + i, "Treść", 5, 1L));
        }
        em.getTransaction().commit();
        em.close();
    }
}