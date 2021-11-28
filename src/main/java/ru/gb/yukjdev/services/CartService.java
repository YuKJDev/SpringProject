package ru.gb.yukjdev.services;

import ru.gb.yukjdev.entities.Cart;
import ru.gb.yukjdev.entities.Product;

import java.math.BigDecimal;

public interface CartService {

    Cart getNewCart();

    void addProduct(Cart cart, Product product, Integer quantity);
    void addProduct(Cart cart, Integer prodId, Integer quantity);
    void delProduct(Cart cart, Product product, Integer quantity);

    BigDecimal getSum(Cart cart);

    void printCart(Cart cart);
    int getProductQuantity(Cart cart, Product product);
    int getProductQuantity(Cart cart, Integer prodId);
}
