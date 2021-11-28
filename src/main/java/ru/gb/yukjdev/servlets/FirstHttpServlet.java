package ru.gb.yukjdev.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "FirstHttpServlet", urlPatterns = "/first_http_servlet")
public class FirstHttpServlet extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(FirstHttpServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setHeader("Content-Type", "text/html; charset=utf-8");
        logger.info("New GET request");
        logger.info("User agent: {}", req.getHeader("User-agent"));
        resp.getWriter().printf("<h1>New GET request</h1> with parameters: param1 = %s, param2 = %s, param3 = %s",
                req.getParameter("param1"), req.getParameter("param2"), req.getParameter("param3"));
        Cookie cookie = new Cookie("user", "someUserName");
        resp.addCookie(cookie);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.info("New POST request");
        resp.getWriter().printf("<h1>New POST request with body %s</h1>",
               readAllLines(req.getReader()));

    }

    public String readAllLines(BufferedReader reader) throws IOException {
        StringBuilder content = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            content.append(line);
            content.append(System.lineSeparator());
        }

        return content.toString();
    }


}
