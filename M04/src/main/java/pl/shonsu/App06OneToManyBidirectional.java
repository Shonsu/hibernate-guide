package pl.shonsu;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.shonsu.entity.Review;

import java.util.List;

public class App06OneToManyBidirectional {
    private static final Logger log = LogManager.getLogger(App06OneToManyBidirectional.class);
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        List<Review> reviews = em.createQuery("Select r from Review r").getResultList();
        for (Review review : reviews) {
            log.info(review);
            log.info(review.getProduct());
        }
        em.getTransaction().commit();
        em.close();
    }
}
