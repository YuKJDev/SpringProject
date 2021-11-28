package ru.gb.yukjdev.services;

import ru.gb.yukjdev.entities.Product;
import java.util.List;

public interface ProductService  {

    List<Product> getProductList();
    void saveOrUpdate(Product product);
    void delete(Product product);
    List<Product> getAll();
    Product getById(Integer id);

}
