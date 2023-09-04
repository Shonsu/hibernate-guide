package pl.shonsu;

import jakarta.persistence.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.shonsu.entity.Order;
import pl.shonsu.entity.OrderRow;

import java.util.List;

public class App27EntityGraph {

    private static final Logger log = LogManager.getLogger(App26QueryWithIn.class);
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();


//        EntityGraph entityGraph = em.getEntityGraph("order-rows");
//
//        Map<String, Object> map = new HashMap<>();
//        map.put("javax.persistence.fetchgraph", entityGraph);
//        //map.put("javax.persistence.loadgraph", entityGraph);
//
//        Order order = em.find(Order.class, 1L, map);
//        log.info(order);
//        for (OrderRow orderRow : order.getOrderRows()) {
//            log.info(orderRow);
//            log.info(orderRow.getProduct());
//        }


        EntityGraph entityGraph = em.createEntityGraph(Order.class);
        entityGraph.addAttributeNodes("orderRows");
        //entityGraph.addAttributeNodes("customer");
        Subgraph<OrderRow> orderRows1 = entityGraph.addSubgraph("orderRows");
        orderRows1.addAttributeNodes("product");

//        Map<String, Object> map = new HashMap<>();
//        map.put("jakarta.persistence.fetchgraph", entityGraph);
        //map.put("jakarta.persistence.loadgraph", entityGraph);

//        Order order = em.find(Order.class, 1L, map);
        List<Order> orders = em.createQuery(
                        "SELECT o FROM Order o " +
                                "LEFT JOIN FETCH o.customer",
                        Order.class
                )
                .setHint("jakarta.persistence.loadgraph", entityGraph)
                .getResultList();

        for (Order order : orders) {
            log.info(order);
            log.info(order.getCustomer());
            for (OrderRow orderRow : order.getOrderRows()) {
                log.info(orderRow);
                log.info(orderRow.getProduct());
            }
        }

        em.getTransaction().commit();
        em.close();
    }
}
