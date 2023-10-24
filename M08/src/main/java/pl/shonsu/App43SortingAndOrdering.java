package pl.shonsu;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.shonsu.entity.Customer;


public class App43SortingAndOrdering {
    private static final Logger log = LogManager.getLogger(App43SortingAndOrdering.class);
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

//        Product product = em.find(Product.class, 1L);
//        log.info(product);
//        Review review = new Review();
//        review.setContent("Content 1");
//        review.setRating(5);
//        review.setProduct(product);
//        log.info(review);
//        Review review2 = new Review();
//        review2.setContent("Content 2");
//        review2.setRating(3);
//        review2.setProduct(product);
//        Review review3 = new Review();
//        review3.setContent("Content 3");
//        review3.setRating(7);
//        review3.setProduct(product);
//
//        Customer customer = em.find(Customer.class, 1L);
//        customer.getReviews().addAll(List.of(review, review2, review3));

        Customer customer = em.createQuery(
                        "SELECT c FROM Customer c " +
                                "INNER JOIN FETCH c.reviews r " +
                                "WHERE c.id=:id " +
                                "ORDER BY r.id ASC",
                        Customer.class
                )
                .setParameter("id", 1L)
                .getSingleResult();

        customer.getReviews().forEach(log::info);

        em.getTransaction().commit();
        em.close();
    }

}
