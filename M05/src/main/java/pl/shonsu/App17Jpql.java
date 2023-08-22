package pl.shonsu;

import jakarta.persistence.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.shonsu.entity.Product;

import java.util.List;

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
        query.setParameter("id", 1);
        Product product = query.getResultStream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No result for query"));
        log.info(product);

        Query query1 = em.createQuery(
                "SELECT AVG(p.price) FROM Product p" +
                        " WHERE p.id=:id");
        query1.setParameter("id", 1090L);
        Double singleResult = (Double) query1.getSingleResult();
        log.info(singleResult);

        Query query2 = em.createQuery(
                "SELECT COUNT(p), AVG(p.price) FROM Product p");
        Object[] result = (Object[]) query2.getSingleResult();
        log.info(result[0] + ", " + result[1]);

        Query query3 = em.createQuery(
                "SELECT p.category.id, COUNT(p) FROM Product p GROUP BY p.category");
        List<Object[]> result3 = query3.getResultList();
        result3.forEach(objects -> log.info(objects[0] + ", " + objects[1]));

        em.getTransaction().commit();
        em.close();
    }
}
