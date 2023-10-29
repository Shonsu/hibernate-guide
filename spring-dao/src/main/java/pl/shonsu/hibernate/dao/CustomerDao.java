package pl.shonsu.hibernate.dao;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Component;
import pl.shonsu.hibernate.entity.Customer;

import java.util.List;

@Component
public class CustomerDao {
    //@PersistenceContext
    private final EntityManager em;

    public CustomerDao(EntityManager em) {
        this.em = em;
    }

    public Customer getCustomer(Long id) {
        return em.createQuery(
                        "SELECT c FROM Customer c " +
                                "LEFT JOIN FETCH c.address " +
                                "LEFT JOIN FETCH c.notes " +
                                "WHERE c.id=:id",
                        Customer.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public List<Customer> getCustomers() {
        List<Customer> resultList = em.createQuery("SELECT c FROM Customer c " +
                        "left join fetch c.address ",
                Customer.class).getResultList();

        return resultList = em.createQuery("SELECT c FROM Customer c " +
                                "left join fetch c.notes ",
                        Customer.class)
                .getResultList();
    }
}
