package ru.gb.yukjdev.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.yukjdev.entities.Product;
import ru.gb.yukjdev.services.ProductService;
import ru.gb.yukjdev.services.ProductServiceImpl;


import java.math.BigDecimal;
import java.util.List;

@Controller
public class ProductController {


    private final ProductService productService = new ProductServiceImpl();

    @RequestMapping(value="/product",  method=RequestMethod.GET)
    public String getProductPage(Model model) {
        List<Product> products = productService.getAll();
        model.addAttribute("productList", products);
        return "product";
    }


    @RequestMapping(value = "/addNewProduct", method=RequestMethod.GET)
    public String addNewProductPage() {
        return "addNewProduct";
    }


    @RequestMapping(value="/addNewProduct", method=RequestMethod.POST)
    public String addNewProduct(@RequestParam(value="id") Integer lastID, @RequestParam(value="title") String title, @RequestParam(value="cost") BigDecimal cost) throws Exception {
        lastID = getLastId();
        Product product = new Product(lastID, title, cost);
        product.setId(++lastID);
        product.setTitle(title);
        product.setCost(cost);
        productService.saveOrUpdate(product);
        return "redirect:./product";
    }


    @RequestMapping(value="./product/delete/{id}")
    public String deleteItem(@PathVariable Integer id) {
        Product product = productService.getById(id);
        productService.delete(product);
        return "redirect:./product";
    }

    Integer getLastId() {
         return productService.getAll().size() - 1;
    }


}
