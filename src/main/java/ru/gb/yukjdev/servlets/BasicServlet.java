package ru.gb.yukjdev.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet(name = "BasicServlet", urlPatterns = "/basic_servlet")
public class BasicServlet implements Servlet {
    private static Logger logger = LoggerFactory.getLogger(BasicServlet.class);
    private transient ServletConfig servletConfig;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

        this.servletConfig = servletConfig;

    }

    @Override
    public ServletConfig getServletConfig() {
        return servletConfig;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {

        logger.info("New request");
        res.getWriter().println("<h1>Servlet content </h1>");


    }

    @Override
    public String getServletInfo() {
        return "BasicServlet";
    }

    @Override
    public void destroy() {

        logger.info("Servlet {} destroyed", getServletInfo());

    }
}
