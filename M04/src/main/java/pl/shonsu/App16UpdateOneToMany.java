package pl.shonsu;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.shonsu.entity.Product;
import pl.shonsu.entity.ReviewDto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class App16UpdateOneToMany {
    private static final Logger log = LogManager.getLogger(App16UpdateOneToMany.class);
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Set<ReviewDto> reviewDtos = getUpdatedReviews();

        Product product = em.find(Product.class, 3L);
        product.getReviews()
                .forEach(review -> reviewDtos
                        .forEach(reviewDto -> {
                            if (reviewDto.id().equals(review.getId())) {
                                review.setContent(reviewDto.content());
                                review.setRating(reviewDto.rating());
                            }
                        }));


        em.getTransaction().commit();
        em.close();
    }

    private static Set<ReviewDto> getUpdatedReviews() {
        Set<ReviewDto> list = new HashSet<>();
        list.add(new ReviewDto(13L, "treśc opinii 3 !!!", 10));
        list.add(new ReviewDto(14L, "treśc opinii 4 !!!", 10));
        list.add(new ReviewDto(15L, "treśc opinii 5 !!!", 10));
        return list;
    }
}
