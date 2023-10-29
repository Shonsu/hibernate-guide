package pl.shonsu.hibernate.service;

import org.springframework.stereotype.Service;
import pl.shonsu.hibernate.dao.ProductDao;
import pl.shonsu.hibernate.entity.Product;

import java.util.List;

@Service
public class ProductService {

    private final ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public Product getProduct(Long id) {
        return productDao.getProduct(id);
    }

    public List<Product> getProducts() {
        return productDao.getProducts();
    }
}
