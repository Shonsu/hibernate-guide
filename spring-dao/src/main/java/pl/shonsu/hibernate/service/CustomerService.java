package pl.shonsu.hibernate.service;

import org.springframework.stereotype.Service;
import pl.shonsu.hibernate.dao.CustomerDao;
import pl.shonsu.hibernate.entity.Customer;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerDao customerDao;

    public CustomerService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public Customer getCustomer(Long id) {
        return customerDao.getCustomer(id);
    }

    public List<Customer> getCustomers() {
        return customerDao.getCustomers();
    }
}
