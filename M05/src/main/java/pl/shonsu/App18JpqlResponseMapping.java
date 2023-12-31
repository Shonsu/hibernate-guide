package pl.shonsu;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.shonsu.entity.ProductInCategoryCounterDto;

import java.util.List;

public class App18JpqlResponseMapping {
    private static final Logger log = LogManager.getLogger(App18JpqlResponseMapping.class);
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();


        Query query = em.createQuery(
                "SELECT new pl.shonsu.entity.ProductInCategoryCounterDto(p.category.id, COUNT(p)) " +
                        "FROM Product p GROUP BY p.category");
        List<ProductInCategoryCounterDto> list = query.getResultList();
        list.forEach(log::info);
        em.getTransaction().commit();
        em.close();
    }
}
