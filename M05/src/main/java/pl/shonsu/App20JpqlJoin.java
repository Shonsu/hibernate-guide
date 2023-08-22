package pl.shonsu;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.shonsu.entity.Product;

import java.util.List;

public class App20JpqlJoin {
    private static final Logger log = LogManager.getLogger(App20JpqlJoin.class);
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p" +
                " INNER JOIN FETCH p.category c" +
                " WHERE c.id=:id", Product.class);
        query.setParameter("id", 4);
        List<Product> resultList = query.getResultList();
        for (Product p : resultList) {
            log.info(p);
            log.info(p.getCategory());
        }

        TypedQuery<Product> query1 = em.createQuery("SELECT p FROM Product p" +
                        " LEFT JOIN FETCH p.category c",
                Product.class);
        List<Product> resultList1 = query1.getResultList();
        for (Product p : resultList1) {
            log.info(p);
            log.info(p.getCategory());
        }


        em.getTransaction().commit();
        em.close();
    }
}
