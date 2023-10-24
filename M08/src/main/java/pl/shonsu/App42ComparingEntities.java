package pl.shonsu;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.shonsu.entity.Customer;
import pl.shonsu.entity.Order;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class App42ComparingEntities {
    private static final Logger log = LogManager.getLogger(App42ComparingEntities.class);
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Customer customer = em.find(Customer.class, 1L);
        log.info(customer);
        log.info(customer.getOrders());

//        em.clear();
//        Order order = em.find(Order.class, 4L);
//        log.info(order.equals(getOrder(customer, 4L)));

//        Order order = new Order();
//        order.setTotal(new BigDecimal("19.99"));
//        order.setCreated(LocalDateTime.now());
//        order.setCustomer(customer);
//        em.persist(order);

        em.getTransaction().commit();
        em.close();
    }

    private static Order getOrder(Customer customer, long id) {
        return customer.getOrders()
                .stream()
                .filter(order1 -> order1.getId().equals(id))
                .findFirst()
                .orElseThrow();
    }
}
