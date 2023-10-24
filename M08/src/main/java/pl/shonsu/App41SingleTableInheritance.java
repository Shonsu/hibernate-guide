package pl.shonsu;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.shonsu.entity.Order;
import pl.shonsu.entity.ProductType;
import pl.shonsu.entity.RealProduct;
import pl.shonsu.entity.VirtualProduct;

import java.time.LocalDateTime;

public class App41SingleTableInheritance {
    private static final Logger log = LogManager.getLogger(App41SingleTableInheritance.class);
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        VirtualProduct virtualProduct = new VirtualProduct(
                null,
                "Virtual",
                "Description virtual",
                LocalDateTime.now(),
                ProductType.VIRTUAL,
                true,
                "/store",
                "test.txt"
        );
        em.persist(virtualProduct);

        RealProduct realProduct = new RealProduct(
                null,
                "Real",
                "Description real product",
                LocalDateTime.now(),
                ProductType.REAL,
                1.5f,
                100,
                50,
                50
        );
        em.persist(realProduct);

        em.flush();
        em.clear();

        VirtualProduct virtualProduct1 = em.find(VirtualProduct.class, virtualProduct.getId());
        log.info(virtualProduct1);
        RealProduct realProduct1 = em.find(RealProduct.class, realProduct.getId());
        log.info(realProduct1);


        em.getTransaction().commit();
        em.close();
    }
}
