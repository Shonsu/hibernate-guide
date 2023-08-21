package pl.shonsu;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import pl.shonsu.entity.Product;
import pl.shonsu.entity.Review;

public class App13AddOneToMany {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Product product = em.find(Product.class, 2L);
//        Review review = new Review();
//        review.setContent("nowa opinia");
//        review.setRating(5);
//        review.setProduct(product);
//        product.addReview(review);
//        em.persist(review);
        Review review = em.getReference(Review.class, 12L);
        product.addReview(review);

        em.getTransaction().commit();
        em.close();
    }
}
