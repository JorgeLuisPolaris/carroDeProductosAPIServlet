package org.jorgemendez.apiservlet.webapp.session.controllers;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jorgemendez.apiservlet.webapp.session.model.Producto;
import org.jorgemendez.apiservlet.webapp.session.model.Usuario;
import org.jorgemendez.apiservlet.webapp.session.service.ProductoService;
import org.jorgemendez.apiservlet.webapp.session.service.ProductoServiceJdbcImpl;
import org.jorgemendez.apiservlet.webapp.session.service.UsuarioService;
import org.jorgemendez.apiservlet.webapp.session.service.UsuarioServiceImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/usuarios/eliminar")
public class UsuarioEliminarServlet extends HttpServlet {
    @Inject
    @Named("defectoU")
    private UsuarioService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        Long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        }catch (NumberFormatException e){
            id = 0L;
        }
        if (id>0){
            Optional<Usuario> o = service.porId(id);
            if (o.isPresent()){
                service.eliminar(id);
                resp.sendRedirect(req.getContextPath()+"/usuarios");

            }else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND,"No existe el usuario");
            }
        }else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND,"Es necesario el id del usuario a eliminar");

        }

    }

}
