package pl.shonsu;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.shonsu.entity.Customer;

import java.util.List;

public class App37CriteriaAggregations {
    private static final Logger log = LogManager.getLogger(App37CriteriaAggregations.class);
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        /*
        "select c.id, c.lastname as customer, ca.name as category, SUM(orw.price) as total" +
                "form Customer c " +
                "inner join c.orders o " +
                "inner join o.orderRows orw " +
                "inner join orw.product p " +
                "inner join p. category ca " +
                "group by ca, c " +
                "having SUM(orw.price)>50 " +
                "order by total desc"
        */

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = cb.createQuery(Object[].class);
        Root<Customer> customer = criteriaQuery.from(Customer.class);
        Join<Object, Object> orders = customer.join("orders", JoinType.INNER);
        Join<Object, Object> orderRows = orders.join("orderRows");
        Join<Object, Object> product = orderRows.join("product");
        Join<Object, Object> category = product.join("category");

        criteriaQuery.multiselect(
                        customer.get("id"),
                        customer.get("lastname"),
                        category.get("name"),
                        cb.sum(orderRows.get("price"))
                )
                .groupBy(category.get("id"), customer.get("id"))
                .orderBy(cb.desc(cb.sum(orderRows.get("price"))))
                .having(cb.greaterThan(cb.sum(orderRows.get("price")), 50));


        TypedQuery<Object[]> query = em.createQuery(criteriaQuery);

        List<Object[]> resultList = query.getResultList();
        for (Object[] row : resultList) {
            log.info(row[0] + " " + row[1] + " " + row[2] + " " + row[3]);
        }
        em.getTransaction().commit();
        em.close();
    }
}
