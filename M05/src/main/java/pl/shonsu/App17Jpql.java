package pl.shonsu;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.shonsu.entity.Product;

public class App17Jpql {
    private static final Logger log = LogManager.getLogger(App17Jpql.class);
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();


        TypedQuery<Product> query = em.createQuery(
                //"SELECT p FROM Product p ORDER BY p.id DESC",
                "SELECT p FROM Product p" +
                        " WHERE p.id=:id",
                Product.class);
        query.setParameter("id", 31);
        Product product = query.getResultStream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No result for query"));
        log.info(product);

        em.getTransaction().commit();
        em.close();
    }
}
