package pl.shonsu;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App34BatchDeleteQuerying {
    private static final Logger log = LogManager.getLogger(App34BatchDeleteQuerying.class);
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        int updated = em.createQuery(
                        "DELETE FROM Review  r " +
                                "WHERE r.product.id=:id"
                )
                .setParameter("id", 2L)
                .executeUpdate();
        log.info("Deleted: " + updated);

        em.getTransaction().commit();
        em.close();
    }
}
