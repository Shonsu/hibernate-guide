package pl.shonsu;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.shonsu.entity.Customer;
import pl.shonsu.entity.Note;

import java.time.LocalDateTime;

public class App44OneToMany3TablesMapping {
    private static final Logger log = LogManager.getLogger(App44OneToMany3TablesMapping.class);
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Customer customer = em.find(Customer.class, 1L);
//        customer.getNotes().add(new Note("Content 111", LocalDateTime.now()));
//        customer.getNotes().add(new Note("Content 222", LocalDateTime.now()));
//        customer.getNotes().add(new Note("Content 333", LocalDateTime.now()));
//        customer.getNotes().add(new Note("Content 444", LocalDateTime.now()));

        //em.persist(customer);
        log.info(customer);
        log.info(customer.getNotes());
        em.getTransaction().commit();
        em.close();
    }
}
