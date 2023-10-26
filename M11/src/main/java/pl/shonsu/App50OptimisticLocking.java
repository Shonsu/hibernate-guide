package pl.shonsu;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.shonsu.entity.Order;

import java.math.BigDecimal;

public class App50OptimisticLocking {
    private static final Logger log = LogManager.getLogger(App50OptimisticLocking.class);
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        EntityManager em2 = entityManagerFactory.createEntityManager();
        em2.getTransaction().begin();

        Order order = em.find(Order.class, 3L);
        Order order2 = em2.find(Order.class, 3L);

        order.setTotal(new BigDecimal("212.11"));
        log.info(order);
        em.getTransaction().commit();
        em.close();

        order2.setTotal(new BigDecimal("414.22"));
        log.info(order2);
        em2.getTransaction().commit();
        em2.close();
    }
}
