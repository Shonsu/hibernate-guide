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
import java.util.Collection;
import java.util.List;

public class App36CriteriaFiltering {

    private static final Logger log = LogManager.getLogger(App36CriteriaFiltering.class);
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
        ParameterExpression<Long> id1 = cb.parameter(Long.class);
        ParameterExpression<Long> id2 = cb.parameter(Long.class);
        ParameterExpression<String> lastname = cb.parameter(String.class);
        ParameterExpression<BigDecimal> total = cb.parameter(BigDecimal.class);
        ParameterExpression<Collection> ids = cb.parameter(Collection.class);
        criteriaQuery.select(customerRoot)
                // where c.id=:id and o.total>50
                .where(
                        cb.and(
//                                cb.or(
//                                        cb.equal(customerRoot.get("id"), id1),
//                                        cb.equal(customerRoot.get("id"), id2)
//                                ),
//                                cb.or(
//                                        //cb.like(customerRoot.get("lastname"), "%ow%")
//                                        cb.like(customerRoot.get("lastname"), cb.concat(lastname, "%"))
//                                ),
                                customerRoot.get("id").in(ids),
                                cb.between(orders.get("total"), total, cb.literal(new BigDecimal("70.00"))),
                                cb.isNotNull(customerRoot.get("firstname"))
                        )
                );
        TypedQuery<Customer> query = em.createQuery(criteriaQuery);
//        query.setParameter(id1, 1L);
//        query.setParameter(id2, 2L);
        query.setParameter(total, new BigDecimal("30.00"));
        //query.setParameter(lastname, "K");
        query.setParameter(ids, List.of(1L, 2L, 4L));

        List<Customer> results = query.getResultList();
        for (Customer result : results) {
            log.info(result);
            log.info(result.getOrders());
//            result.getOrders().forEach(order -> {
//                log.info(order);
//                order.getOrderRows().forEach(orderRow ->{
//                    log.info(orderRow);
//                    log.info(orderRow.getProduct());
//                });
//            });
        }

        em.getTransaction().commit();
        em.close();
    }
}
