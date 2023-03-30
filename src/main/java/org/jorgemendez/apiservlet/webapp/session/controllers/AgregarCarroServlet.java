package org.jorgemendez.apiservlet.webapp.session.controllers;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jorgemendez.apiservlet.webapp.session.configs.ProductoServicePrincipal;
import org.jorgemendez.apiservlet.webapp.session.model.Carro;
import org.jorgemendez.apiservlet.webapp.session.model.ItemCarro;
import org.jorgemendez.apiservlet.webapp.session.model.Producto;
import org.jorgemendez.apiservlet.webapp.session.service.ProductoService;

import java.io.IOException;
    import java.util.Optional;

@WebServlet("/carro/agregar")
public class AgregarCarroServlet extends HttpServlet {
    @Inject
    @ProductoServicePrincipal
    //@Named("defecto")
    private ProductoService productoService;
    @Inject //lo que se manda es un proxy y por ello no hay problemas si se usan diferentes clientes
    private Carro carro;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));

        Optional<Producto> producto = productoService.porId(id);
        if (producto.isPresent()){
            ItemCarro item = new ItemCarro(1,producto.get());
          //  HttpSession session = req.getSession();
          //  Carro carro = (Carro) session.getAttribute("carro"); esto ya no es necesario porque el carro va a ser inyectado
            carro.addItem(item);
            resp.sendRedirect(req.getContextPath()+"/carro/ver");
        }
    }
}
