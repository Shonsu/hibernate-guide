package pl.shonsu;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.shonsu.entity.Customer;
import pl.shonsu.entity.CustomerDetails;

import java.time.LocalDate;

public class App39IdsMapping {
    private static final Logger log = LogManager.getLogger(App39IdsMapping.class);
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Customer customer = em.find(Customer.class, 1L);
        CustomerDetails customerDetails = new CustomerDetails(
                null,
                "Warszawa",
                LocalDate.of(1982, 2, 9),
                "Jan",
                "Janina",
                "089098098",
                customer);
        customer.setCustomerDetails(customerDetails);

        em.persist(customer);

        log.info(customer);
        log.info(customer.getCustomerDetails());

        em.getTransaction().commit();
        em.close();
    }
}
