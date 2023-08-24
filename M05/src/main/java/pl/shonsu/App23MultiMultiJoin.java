package pl.shonsu;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class App23MultiMultiJoin {
    private static final Logger log = LogManager.getLogger(App22CartesianProduct.class);
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
/*
    SELECT cu.id,cu.lastname customer, ca.name category, SUM(orw.price) total FROM CUSTOMER cu
    INNER JOIN `order` o on cu.id=o.customer_id
    INNER JOIN order_row orw on o.id=orw.order_id
    INNER JOIN product p on orw.product_id=p.id
    INNER JOIN category ca on p.category_id=ca.id
    GROUP BY ca.id, cu.id
    HAVING total>50
    ORDER BY total DESC
    ;
 */
        Query query = em.createQuery(
                "SELECT DISTINCT " +
                        "c.id, " +
                        "c.lastname as customer, " +
                        "ca.name as category, " +
                        "SUM(orw.price) as total " +
                        "FROM Customer c " +
                        "INNER JOIN c.orders o " +
                        "INNER JOIN o.orderRows orw " +
                        "INNER JOIN orw.product p " +
                        "INNER JOIN p.category ca " +
                        "group by ca, c " +
                        "HAVING SUM(orw.price) > 50 " +
                        "ORDER BY total DESC");
        List<Object[]> resultList = query.getResultList();
        for (Object[] row : resultList) {
            log.info(row[0] + ", \t" + row[1] + ", \t" + row[2] + ", \t" + row[3]);
        }
        em.getTransaction().commit();
        em.close();
    }
}
