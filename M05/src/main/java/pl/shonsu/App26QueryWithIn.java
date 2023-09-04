package pl.shonsu;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.shonsu.entity.Order;

import java.util.List;

public class App26QueryWithIn {
    private static final Logger log = LogManager.getLogger(App26QueryWithIn.class);
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        List<Order> orders = em.createQuery(
                        "SELECT o FROM Order o " +
                                "WHERE o.id IN (:ids)",
                        Order.class
                )
                .setParameter("ids", List.of(1L, 3L, 5L))
                .getResultList();


        for (Order order : orders) {
            log.info(order);
//            log.info(order.getOrderRows());
        }


        em.getTransaction().commit();
        em.close();

    }
}
