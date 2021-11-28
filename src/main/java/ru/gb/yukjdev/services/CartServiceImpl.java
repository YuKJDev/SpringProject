package ru.gb.yukjdev.services;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;
import ru.gb.yukjdev.entities.Cart;
import ru.gb.yukjdev.entities.Product;
import ru.gb.yukjdev.entities.ProductRepositoryImpl;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService {

    private final ProductRepositoryImpl productRepository;

    public CartServiceImpl(ProductRepositoryImpl productRepository) {
        this.productRepository = productRepository;
    }

    @Lookup
    @Override
    public Cart getNewCart() {
        return null;
    }

    @Override
    public void addProduct(Cart cart, Product product, Integer quantity) {
        cart.add(product, quantity);

    }

    @Override
    public void addProduct(Cart cart, Integer prodId, Integer quantity) {
        Product product = productRepository.getById(prodId);
        this.addProduct(cart, product, quantity);
    }

    @Override
    public void delProduct(Cart cart, Product product, Integer quantity) {
        cart.del(product, quantity);
    }

    @Override
    public BigDecimal getSum(Cart cart) {
        return cart.getSum();

    }

    @Override
    public void printCart(Cart cart) {
        BigDecimal sum = BigDecimal.valueOf(0);
          for (Map.Entry<Product, Integer> entryMap : cart.getCartMap().entrySet()) {
            Product product = entryMap.getKey();
            BigDecimal prodSum = product.getCost().multiply(BigDecimal.valueOf(entryMap.getValue()));
            System.out.printf("Product id = %-2s | name = %-15s | price = %-8s | quantity = %-3s | sum = %-12s \n",
                    product.getId(), product.getTitle(), product.getCost(), entryMap.getValue(), prodSum);
            sum = sum.add(prodSum);
        }
        System.out.println("Общая стоимость продуктов в корзине: " + sum);

    }

    @Override
    public int getProductQuantity(Cart cart, Product product) {
        if (cart.getCartMap().containsKey(product)) {
            return cart.getCartMap().get(product);
        }
        return 0;
    }

    @Override
    public int getProductQuantity(Cart cart, Integer prodId) {
        Product product = productRepository.getById(prodId);
        return this.getProductQuantity(cart, product);
    }

}
