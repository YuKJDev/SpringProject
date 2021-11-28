package ru.gb.yukjdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.yukjdev.entities.Product;
import ru.gb.yukjdev.entities.ProductRepository;
import ru.gb.yukjdev.entities.ProductRepositoryImpl;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository = new ProductRepositoryImpl();

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;

    }
    @Override
    public List<Product> getProductList() {
        return productRepository.getAll();
    }

    @Override
    public void saveOrUpdate(Product product) {
        if(product!=null) {
            List<Product> products = productRepository.getAll();
            if(!products.isEmpty()) {
                Product lastProduct = products.get(products.size() - 1);
                product.setId(lastProduct.getId()+1);
                productRepository.saveOrUpdate(product);

            }
        }

    }

    @Override
    public void delete(Product product) {
        if(product!=null) {
            productRepository.delById(product.getId());
        }
    }

    @Override
    public List<Product> getAll() {
        return productRepository.getAll();
    }

    @Override
    public Product getById(Integer id) {
        if(id!=null) {
            return productRepository.getById(id);
        }
        return null;
    }
}
