package pl.shonsu;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.shonsu.entity.Customer;

import java.math.BigDecimal;
import java.util.List;

public class App35CriteriaJoin {
    private static final Logger log = LogManager.getLogger(App35CriteriaJoin.class);
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();


        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Customer> criteriaQuery = cb.createQuery(Customer.class);
        Root<Customer> customerRoot = criteriaQuery.from(Customer.class);
        Join<Object, Object> orders = (Join<Object, Object>) customerRoot.fetch("orders", JoinType.INNER);
        orders.fetch("orderRows")
                .fetch("product");
        ParameterExpression<Long> id = cb.parameter(Long.class);
        ParameterExpression<BigDecimal> total = cb.parameter(BigDecimal.class);
        criteriaQuery.select(customerRoot)
                // where c.id=:id and o.total>50
                .where(
                        cb.and(
                                cb.equal(customerRoot.get("id"), id),
                                cb.greaterThan(orders.get("total"), total))
                );
        TypedQuery<Customer> query = em.createQuery(criteriaQuery);
        query.setParameter(id, 1L);
        query.setParameter(total, new BigDecimal("50.00"));

        List<Customer> results = query.getResultList();
        for (Customer result : results) {
            log.info(result);
            result.getOrders().forEach(order -> {
                log.info(order);
                order.getOrderRows().forEach(orderRow ->{
                    log.info(orderRow);
                    log.info(orderRow.getProduct());
                });
            });
        }

        em.getTransaction().commit();
        em.close();
    }
}
