package pl.shonsu;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.shonsu.entity.Category;
import pl.shonsu.entity.Product;

import java.util.List;
import java.util.Set;

public class App21JpqlMultiJoin {
    private static final Logger log = LogManager.getLogger(App21JpqlMultiJoin.class);
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        TypedQuery<Category> query = em.createQuery("SELECT c FROM Category c " +
                "LEFT JOIN FETCH c.products p " +
                "LEFT JOIN FETCH p.reviews " +
                "WHERE c.id=:id", Category.class);

        query.setParameter("id", 4L);

        List<Category> resultList = query.getResultList();
        for (Category category : resultList) {
            log.info(category);
            Set<Product> products = category.getProducts();
            log.info(products);
            for (Product product : products) {
                log.info(product.getReviews());
            }
        }
        em.getTransaction().commit();
        em.close();
    }
}
