package pl.shonsu;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.shonsu.entity.Order;

import java.util.List;

public class App25FetchMode {
    private static final Logger log = LogManager.getLogger(App25FetchMode.class);
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        //Order order = em.find(Order.class, 1L);

        List<Order> orders = em.createQuery(
                        "SELECT o FROM Order o " +
                                "ORDER BY o.created DESC",
                        Order.class)
                .setMaxResults(5)
                .getResultList();

        for (Order order : orders) {
            log.info(order);
            log.info(order.getOrderRows());
        }


        em.getTransaction().commit();
        em.close();

    }
}
