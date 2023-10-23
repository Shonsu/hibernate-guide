package pl.shonsu;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.shonsu.entity.Address;
import pl.shonsu.entity.AddressType;
import pl.shonsu.entity.Customer;

import java.time.LocalDateTime;
import java.util.List;

public class App38ElementCollection {
    private static final Logger log = LogManager.getLogger(App38ElementCollection.class);
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Customer customer = new Customer();
        customer.setFirstname("Customer 1");
        customer.setLastname("Customer 1 lastname");
        customer.setCreated(LocalDateTime.now());
        customer.setAddress(List.of(
                new Address(AddressType.BILLING, "Polna 10/10", "00-000", "warszawa"),
                new Address(AddressType.BILLING, "Kwiatowa 5", "00-123", "Józefgłów"),
                new Address(AddressType.BILLING, "Dąbrowskiej 54", "00-321", "Glina")
        ));
        em.persist(customer);
        em.flush();
        em.clear();

        Customer customer1 = em.find(Customer.class, customer.getId());
        log.info(customer1);
        log.info(customer1.getAddress());

        em.getTransaction().commit();
        em.close();
    }
}
