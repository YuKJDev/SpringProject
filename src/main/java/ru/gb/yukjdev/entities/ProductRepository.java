package ru.gb.yukjdev.entities;
import java.util.List;

public interface ProductRepository {

    void saveOrUpdate(Product product);
    void  delById (Integer id);
    List<Product> getAll();
    Product getById(Integer id);

}
