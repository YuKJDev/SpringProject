package ru.gb.yukjdev.entities;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ProductRepositoryImpl implements ProductRepository {

    private final Map<Integer, Product> products = new ConcurrentHashMap<>();

    public ProductRepositoryImpl() {
        for (int i = 1; i < 6; i++) {
            BigDecimal randomCost  = newRandomBigDecimal(new Random(), 100);
            products.put(i, new Product(i, "Product# " + i, randomCost));

        }

        products.put(1, new Product(1, "Audi r8", BigDecimal.valueOf(11200000.00)));
        products.put(2, new Product(2, "BMW X6", BigDecimal.valueOf(7100000.00)));
        products.put(3, new Product(3, "Cadillac Escalade", BigDecimal.valueOf(8200000.00)));
        products.put(4, new Product(4, "Dodge Challenger", BigDecimal.valueOf(2500000.00)));
        products.put(5, new Product(5, "E-Car GD04B", BigDecimal.valueOf(1200000.55)));

    }

    @Override
    public void saveOrUpdate(Product product) {
        if (product.getId() == null) {
            Integer id = products.size() + 1;
            product.setId(id);
        }
    }

    @Override
    public void delById(Integer id) {
        products.remove(id);
    }

    @Override
    public List<Product> getAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public Product getById(Integer id) {
        return products.get(id);
    }

    private static BigDecimal newRandomBigDecimal(Random r, int precision) {
        BigInteger n = BigInteger.TEN.pow(precision);
        return new BigDecimal(newRandomBigInteger(n, r), precision);
    }

    private static BigInteger newRandomBigInteger(BigInteger n, Random rnd) {
        BigInteger r;
        do {
            r = new BigInteger(n.bitLength(), rnd);
        } while (r.compareTo(n) >= 0);

        return r;
    }
}
