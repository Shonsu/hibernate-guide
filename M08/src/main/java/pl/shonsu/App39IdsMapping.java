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

public class App39IdsMapping {
    private static final Logger log = LogManager.getLogger(App39IdsMapping.class);
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();


        em.getTransaction().commit();
        em.close();
    }
}
