package pl.shonsu;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import pl.shonsu.entity.Category;
import pl.shonsu.entity.Product;

public class App11AddOneToOne {

    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Product product = em.find(Product.class, 3L);
        Category category = new Category();
        category.setName("Nowa kategoria");
        category.setDescription("Opis");
        em.persist(category);
        product.setCategory(category);

        em.getTransaction().commit();
        em.close();
    }
}
