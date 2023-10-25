package pl.shonsu;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.shonsu.entity.Customer;
import pl.shonsu.entity.Product;

public class App45ErrorInTransaction {
    private static final Logger log = LogManager.getLogger(App45ErrorInTransaction.class);
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        try {
            Customer customer = em.createQuery("SELECT c FROM Customer c " +
                                    "WHERE c.id=:id",
                            Customer.class).
                    setParameter("id", 99L)
                    .getSingleResult();
            log.info(customer);

            Product product = em.createQuery("SELECT p FROM Product p WHERE p.id = :id", Product.class)
                    .setParameter("id", 1L)
                    .getSingleResult();
            log.info(product);
            em.getTransaction().commit();

        } catch (Exception e) {

            log.info(em.getTransaction().isActive());
            log.info(em.getTransaction().getRollbackOnly());

            if (em.getTransaction().isActive() || em.getTransaction().getRollbackOnly()) {
                em.getTransaction().rollback();
                log.error("Transaction rollback: ", e);
            }
            log.error(e, e);
        }


        // em.getTransaction().commit();
        em.close();
    }
}
