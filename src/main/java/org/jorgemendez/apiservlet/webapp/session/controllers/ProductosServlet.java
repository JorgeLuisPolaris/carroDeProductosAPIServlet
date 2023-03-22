package org.jorgemendez.apiservlet.webapp.session.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jorgemendez.apiservlet.webapp.session.model.Producto;
import org.jorgemendez.apiservlet.webapp.session.service.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

@WebServlet({"/productos", "/productos.html"})
public class ProductosServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conn = (Connection) req.getAttribute("conn");
        ProductoService productoService = new ProductoServiceJdbcImpl(conn);
        List<Producto> productos = productoService.listar();

        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = auth.getUsername(req);

        req.setAttribute("productos",productos);
        req.setAttribute("title",req.getAttribute("title") + ": Listado de productos");
        req.setAttribute("username",usernameOptional);
        getServletContext().getRequestDispatcher("/listar.jsp").forward(req,resp);


    }
}

