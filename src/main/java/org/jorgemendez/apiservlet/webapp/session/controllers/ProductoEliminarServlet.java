package org.jorgemendez.apiservlet.webapp.session.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jorgemendez.apiservlet.webapp.session.model.Producto;
import org.jorgemendez.apiservlet.webapp.session.service.ProductoService;
import org.jorgemendez.apiservlet.webapp.session.service.ProductoServiceJdbcImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/productos/eliminar")
public class ProductoEliminarServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        ProductoService service = new ProductoServiceJdbcImpl(conn);

        Long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        }catch (NumberFormatException e){
            id = 0L;
        }
        if (id>0){
            Optional<Producto> o = service.porId(id);
            if (o.isPresent()){
                service.eliminar(id);
                resp.sendRedirect(req.getContextPath()+"/productos");

            }else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND,"No existe el producto");
            }
        }else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND,"Es necesario el id del producto a eliminar");

        }

    }

}
