package ru.gb.yukjdev.apps;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import ru.gb.yukjdev.config.ApplicationConfig;
import ru.gb.yukjdev.entities.Cart;
import ru.gb.yukjdev.entities.Product;
import ru.gb.yukjdev.services.CartServiceImpl;
import ru.gb.yukjdev.services.ProductService;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.List;

@Component
public class DemoApplication {

    private final ProductService productService;
    private CartServiceImpl cartService;

    public DemoApplication(ProductService productService, CartServiceImpl cartService) {
        this.productService = productService;
        this.cartService = cartService;
    }

    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(ApplicationConfig.class);
    }
    public static void printSeparator(){
        System.out.println("------------------------------");
    }

    private static void printList(List<?> list) {
        System.out.println("СПИСОК ПРОДУКТОВ:");
        for (Object el : list) {
            System.out.println(el.toString());
        }
    }

    @PostConstruct
    private void cartInteract() throws IOException {
        Cart cart = cartService.getNewCart();
        System.out.println("\n----- Корзина ------\n");

        System.out.println("Консольное приложение для работы с корзиной. Для справки /?");
        listCommand();
        printSeparator();

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        boolean exit = false;
        while(!exit) {

            System.out.print("Введите команду: ");
            Integer prodId;
            int quantity;

            String str = in.readLine();
            if (!str.isEmpty()) {
                String[] parts = str.split("\\s");
                String command = parts[0];

                if (command.equalsIgnoreCase("exit")) {
                    exit = true; // флаг - выйти из цикла и завершить работу
                    System.out.println("Спасибо, что воспользовались нашим интернет-магазином.");
                } else if (command.equalsIgnoreCase("/?")) {
                    listCommand(); // справка
                    printSeparator();
                } else if (command.equalsIgnoreCase("test")) {
                    cartTest();
                } else if (command.equals("list")) {
                    // распечатать список продуктов
                    printSeparator();
                    printList(productService.getProductList());
                    printSeparator();
                } else if (command.equalsIgnoreCase("new")) {
                    // удалить корзину, создать новую
                    cart = cartService.getNewCart();
                    System.out.println("Создана новая (пустая) корзина, старая - удалена.");
                } else if (command.equalsIgnoreCase("print")) {
                    printSeparator();
                    cartService.printCart(cart); // распечатать содержимое корзины
                    printSeparator();
                } else if (command.equalsIgnoreCase("sum")) {
                    System.out.println(cartService.getSum(cart)); // распечатать стоимость корзины
                    printSeparator();
                  } else if (parts.length == 3) {
                    try {
                        prodId = Integer.valueOf(parts[1]);
                        quantity = Integer.parseInt(parts[2]);
                    } catch (NumberFormatException e) {
                        wrongCommand();
                        continue;
                    }

                    if (command.equalsIgnoreCase("add")) {
                        // добавить продукт
                        Product product = productService.getById(prodId);
                        if (product != null) {
                            cartService.addProduct(cart, productService.getById(prodId), quantity);
                            System.out.println("В корзину добавлен товар: " + productService.getById(prodId) + " - " + quantity + " шт.");
                        } else {
                            System.out.println("Такого товара нет в списке.");
                        }
                    } else if (command.equalsIgnoreCase("del")) {
                        // удалить продукт
                        if (cartService.getProductQuantity(cart, prodId) > 0) {
                            cartService.delProduct(cart, productService.getById(prodId), quantity);
                            System.out.println("Из корзины удален товар: " + productService.getById(prodId) + " - " + quantity + " шт.");
                        } else {
                            System.out.println("Такого продукта нет в корзине.");
                        }
                    }
                } else {
                    wrongCommand();
                }
            }
        }
    }

    private static void wrongCommand() {
        System.out.println("Неправильный формат команды");
    }

    private static void listCommand() {
        System.out.println("Распечатать список продуктов: list");
        System.out.println("Добавить продукт: add [N продукта] [количество]");
        System.out.println("Удалить продукт: del [N продукта] [количество]");
        System.out.println("\tЕсли количество товара в корзине < 1, то он удаляется из списка.");
        System.out.println("Распечатать содержимое корзины: print");
        System.out.println("Распечатать только стоимость корзины: sum");
        System.out.println("Удалить корзину и создать новую: new");
        System.out.println("Тест (все операции с корзиной и продуктами): test");
        System.out.println("Завершить: exit");
    }


    private void cartTest() {
        Cart cart = cartService.getNewCart();
        System.out.println("\n----------- ТЕСТИРОВАНИЕ КОРЗИНЫ -----------\n");

        printList(productService.getProductList());
        printSeparator();

        //вывести продукт с id = 2
        System.out.println("Распечатать: продукт с id=2");
        System.out.println(productService.getById(2));
        printSeparator();

        //удалить продукт с id = 3, добавить - с id = 8, заменить - id = 1 (сделать цену 0.01)
        System.out.println("Продукт с id=3 удалён.");
        productService.delete(productService.getById(3));
        System.out.println("Продукт с id=8 добавлен.");
        productService.saveOrUpdate(new Product(8, "Product Added", BigDecimal.valueOf(888888.00)));
        System.out.println("Продукт с id=1 изменён.");
        productService.saveOrUpdate(new Product(1, "Product Changed", BigDecimal.valueOf(.01)));
        printSeparator();

        //распечатать измененный список продуктов
        printList(productService.getProductList());
        printSeparator();

        System.out.println("КОРЗИНА №1 (все операции с корзиной - добавить, изменить количество, удалить продукт)");
        // добавить продукты в корзину
        cartService.addProduct(cart, productService.getById(1), 1);
        cartService.addProduct(cart, productService.getById(2), 3);
        cartService.addProduct(cart, productService.getById(4), 9);
        cartService.addProduct(cart, productService.getById(5), 9);
        cartService.addProduct(cart, productService.getById(8), 5);
        // увеличить количество продуктов в корзине (добавляем еще продукт с id=1 + 3шт.), итого 4шт.
        cartService.addProduct(cart, productService.getById(1), 3);
        // уменьшить количество одного продукта в корзине (id=2 - 1шт.), итого должно быть 2шт.
        cartService.delProduct(cart, productService.getById(2), 1);
        // удалить продукт из корзины
        cartService.delProduct(cart, productService.getById(4), 999);
        cartService.delProduct(cart, productService.getById(5), null);
        cartService.printCart(cart);
        System.out.println("Проверка стоимости корзины (getSum): " + cartService.getSum(cart));
        printSeparator();

        // создать новую корзину
        System.out.println("КОРЗИНА №2");
        cart = cartService.getNewCart();
        cartService.addProduct(cart, 2, 2);
        cartService.printCart(cart);

        System.out.println("\n----------- ТЕСТИРОВАНИЕ ЗАВЕРШЕНО -----------\n");

    }

}
