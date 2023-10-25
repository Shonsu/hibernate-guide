package pl.shonsu;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.jpa.AvailableHints;
import pl.shonsu.entity.Customer;

public class App46ReadOnly {
    private static final Logger log = LogManager.getLogger(App46ReadOnly.class);
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Customer customer = em.createQuery("SELECT c FROM Customer c " +
                                "WHERE c.id=:id",
                        Customer.class)
                .setParameter("id", 1L)
                .setHint(AvailableHints.HINT_READ_ONLY, true)
                .getSingleResult();

        customer.setLastname("Lastname");
        em.merge(customer);
        log.info(customer);

        em.getTransaction().commit();
        em.close();
    }
}
