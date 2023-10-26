package pl.nullpointerexception.hibernate.service;

import org.springframework.stereotype.Service;
import pl.nullpointerexception.hibernate.dao.CustomerDao;
import pl.nullpointerexception.hibernate.entity.Customer;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CustomerService {

    private final CustomerDao customerDao;

    public CustomerService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Transactional
    public Customer getCustomer(Long id) {
        return customerDao.getCustomer(id);
    }

    public List<Customer> getCustomers() {
        return customerDao.getCustomers();
    }
}
