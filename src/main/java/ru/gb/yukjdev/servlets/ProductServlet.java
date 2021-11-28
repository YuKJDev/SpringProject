package ru.gb.yukjdev.servlets;

import ru.gb.yukjdev.services.ProductService;
import ru.gb.yukjdev.services.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ProductHttpServlet", urlPatterns = "/prod")
public class ProductServlet extends HttpServlet {

    private final ProductService productService = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("product", productService.getAll());
        getServletContext().getRequestDispatcher("/product.jsp").forward(req, resp);

    }
}
