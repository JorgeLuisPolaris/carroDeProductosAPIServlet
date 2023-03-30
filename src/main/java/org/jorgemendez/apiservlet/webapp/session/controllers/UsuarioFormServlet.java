package org.jorgemendez.apiservlet.webapp.session.controllers;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jorgemendez.apiservlet.webapp.session.model.Usuario;
import org.jorgemendez.apiservlet.webapp.session.service.UsuarioService;
import org.jorgemendez.apiservlet.webapp.session.service.UsuarioServiceImpl;
import java.io.IOException;
import java.sql.Connection;;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/usuarios/form")
public class UsuarioFormServlet extends HttpServlet {
    @Inject
    @Named("defectoU")
    private UsuarioService service;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0L;
        }
        Usuario usuario = new Usuario();
        if (id > 0L) {
            Optional<Usuario> optionalUsuario = service.porId(id);
            if (optionalUsuario.isPresent()) {
                usuario = optionalUsuario.get();
            }
        }
        req.setAttribute("usuario", usuario);
        req.setAttribute("title", req.getAttribute("title") + ": Formulario de usuarios");
        getServletContext().getRequestDispatcher("/formUsuario.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long id;

        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0L; //este id nos sirve como swtich para saber si se hará update o se guardará uno nuevo
        }
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        Map<String, String> errores = new HashMap<>();

        if (username == null || username.isBlank()) {
            errores.put("username", "El username no puede estar vacio");
        }

        if (password == null || password.isBlank()) {
            errores.put(" ", "El password es requerido");
        }

        if (email == null || email.isBlank()) {
            errores.put("email", "El email es requerido");
        }


        Usuario usuario = new Usuario();

        if (id > 0) {
            Optional<Usuario> o = service.porId(id);
            if (o.isPresent()) {
                usuario = o.get();
            }
        }

        usuario.setPassword(password);
        usuario.setUsername(username);
        usuario.setEmail(email);
        if (errores.isEmpty()) {
            service.guardar(usuario);
            resp.sendRedirect(req.getContextPath() + "/usuarios");
        } else {
            req.setAttribute("errores", errores);
            req.setAttribute("usuario", usuario);
            req.setAttribute("title", req.getAttribute("title") + ": Formulario de usuarios");
            getServletContext().getRequestDispatcher("/formUsuario.jsp").forward(req, resp);
        }
    }
}
