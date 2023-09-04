package pl.shonsu;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.shonsu.entity.Product;


public class App24FetchTypeInDirectAndQueryFetching {
    private static final Logger log = LogManager.getLogger(App24FetchTypeInDirectAndQueryFetching.class);
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Product product = em.createQuery(
                        "SELECT p FROM Product p " +
                                "LEFT JOIN FETCH p.category c " +
                                "WHERE p.id=:id and c.id=:categoryId",
                        Product.class)
                .setParameter("id", 1L)
                .setParameter("categoryId", 1L)
                .getSingleResult();
        //Product product = em.find(Product.class, 1L);
        log.info(product);
        log.info(product.getCategory());
        log.info(product.getReviews());


        em.getTransaction().commit();
        em.close();

    }
}
