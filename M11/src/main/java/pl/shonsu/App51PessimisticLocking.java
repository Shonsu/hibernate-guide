package pl.shonsu;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.LockModeType;
import jakarta.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.shonsu.entity.Order;

public class App51PessimisticLocking {
    private static final Logger log = LogManager.getLogger(App51PessimisticLocking.class);
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();


        //Order order = em.find(Order.class, 3L, LockModeType.PESSIMISTIC_READ);
        Order order = em.createQuery(
                        "SELECT o FROM Order o " +
                                " WHERE o.id=:id",
                        Order.class)
                .setParameter("id", 3L)
                .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                .getSingleResult();

        log.info(order);

        em.getTransaction().commit();
        em.close();
    }
}
