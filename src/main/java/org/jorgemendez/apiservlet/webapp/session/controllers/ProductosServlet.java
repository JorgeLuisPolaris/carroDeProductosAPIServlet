package org.jorgemendez.apiservlet.webapp.session.controllers;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jorgemendez.apiservlet.webapp.session.configs.ProductoServicePrincipal;
import org.jorgemendez.apiservlet.webapp.session.model.Producto;
import org.jorgemendez.apiservlet.webapp.session.service.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet({"/productos", "/productos.html"})
public class ProductosServlet extends HttpServlet {
    @Inject
    @ProductoServicePrincipal
    //@Named("defecto")
    private ProductoService productoService;
    @Inject
    private LoginService auth;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Producto> productos = productoService.listar();
        Optional<String> usernameOptional = auth.getUsername(req);
        req.setAttribute("productos",productos);
        req.setAttribute("title",req.getAttribute("title") + ": Listado de productos");
        req.setAttribute("username",usernameOptional);
        getServletContext().getRequestDispatcher("/listar.jsp").forward(req,resp);


    }
}

