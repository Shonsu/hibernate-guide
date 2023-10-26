package pl.shonsu;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.jpa.AvailableHints;
import pl.shonsu.entity.Customer;

import java.util.List;

public class App49QueryCache {
    private static final Logger log = LogManager.getLogger(App49QueryCache.class);
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        List<Customer> customer = em.createQuery("SELECT c FROM Customer c",
                        Customer.class)
                .setHint(AvailableHints.HINT_CACHEABLE, true)
                .getResultList();
        log.info(customer);
        em.getTransaction().commit();
        em.close();

        em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        customer = em.createQuery("SELECT c FROM Customer c",
                        Customer.class)
                .setHint(AvailableHints.HINT_CACHEABLE, true)
                .getResultList();
        log.info(customer);

        em.getTransaction().commit();
        em.close();
    }
}
