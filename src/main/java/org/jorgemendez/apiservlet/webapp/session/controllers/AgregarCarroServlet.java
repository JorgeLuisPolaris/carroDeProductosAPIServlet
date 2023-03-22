package org.jorgemendez.apiservlet.webapp.session.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.jorgemendez.apiservlet.webapp.session.model.Carro;
import org.jorgemendez.apiservlet.webapp.session.model.ItemCarro;
import org.jorgemendez.apiservlet.webapp.session.model.Producto;
import org.jorgemendez.apiservlet.webapp.session.service.ProductoService;
import org.jorgemendez.apiservlet.webapp.session.service.ProductoServiceImp;
import org.jorgemendez.apiservlet.webapp.session.service.ProductoServiceJdbcImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/carro/agregar")
public class AgregarCarroServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        Connection conn = (Connection) req.getAttribute("conn");
        ProductoService productoService = new ProductoServiceJdbcImpl(conn);
        Optional<Producto> producto = productoService.porId(id);
        if (producto.isPresent()){
            ItemCarro item = new ItemCarro(1,producto.get());
            HttpSession session = req.getSession();
            Carro carro = (Carro) session.getAttribute("carro");
            carro.addItem(item);
            resp.sendRedirect(req.getContextPath()+"/carro/ver");
        }
    }
}
