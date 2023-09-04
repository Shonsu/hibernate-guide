package pl.shonsu;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.shonsu.entity.Order;
import pl.shonsu.entity.Product;

import java.util.List;

public class App28NplusOne {
    private static final Logger log = LogManager.getLogger(App28NplusOne.class);
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

//        List<Order> orders = em.createQuery("SELECT o FROM Order  o", Order.class).getResultList();
//        List<Order> orders = em.createQuery(
//                "SELECT DISTINCT o FROM Order  o " +
//                        "INNER JOIN FETCH o.orderRows orw",
//                Order.class
//        ).getResultList();
//
//        log.info("Orders count: " + orders.size());
//        for (Order order : orders) {
//
//            log.info(order.getId());
//            log.info(order.getOrderRows());
//        }
//        EntityGraph entityGraph = em.getEntityGraph("order-and-rows");
//        List<Order> orders = em.createQuery(
//                "SELECT DISTINCT o FROM Order  o",
//                Order.class
//        )
//                .setHint("jakarta.persistence.fetchgraph", entityGraph)
//                .getResultList();
//
//        log.info("Orders count: " + orders.size());
//        for (Order order : orders) {
//
//            log.info(order.getId());
//            log.info(order.getOrderRows());
//        }

        EntityGraph entityGraph1 = em.getEntityGraph("product-review-attribute");

//        List<Product> resultList = em.createQuery(
//                "SELECT p FROM Product  p " ,
////                        "LEFT JOIN FETCH p.reviews " +
////                        "LEFT JOIN FETCH p.attributes",
//                Product.class
//        )
//                .setHint("jakarta.persistence.fetchgraph", entityGraph1)
//                .getResultList();
//        for (Product product : resultList) {
//            log.info(product);
//            log.info(product.getReviews());
//            log.info(product.getAttributes());
//        }

        List<Order> orders = em.createQuery(
                "SELECT o FROM Order  o ",
                Order.class
        ).getResultList();

        log.info("Orders count: " + orders.size());
        for (Order order : orders) {

            log.info(order.getId());
            log.info(order.getOrderRows());
        }
        em.getTransaction().commit();
        em.close();
    }
}
