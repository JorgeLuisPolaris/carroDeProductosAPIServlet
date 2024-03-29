package org.jorgemendez.apiservlet.webapp.session.controllers;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.jorgemendez.apiservlet.webapp.session.model.Usuario;
import org.jorgemendez.apiservlet.webapp.session.service.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Optional;

@WebServlet({"/login","/login.html"})
public class LoginServlet extends HttpServlet {
    @Inject
    @Named("defectoU")
    private UsuarioService service;

    @Inject
    private LoginService login;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String pass = req.getParameter("password");
        Optional<Usuario> usuarioOptional = service.login(username,pass);
        if (usuarioOptional.isPresent()) {
            HttpSession session = req.getSession();
            session.setAttribute("username", username);

            resp.sendRedirect(req.getContextPath() + "/login.html");
        } else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Lo sentimos los datos ingresados no son correctos");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<String> usernameOptional = login.getUsername(req);
        if (usernameOptional.isPresent()){
            resp.setContentType("text/html; charset=UTF-8");
            try (PrintWriter out = resp.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("    <head>");
                out.println("        <meta charset=\"UTF8\">");
                out.println("        <title>Hola "+ usernameOptional.get()+" has iniciado sesión anteriormente</title>");
                out.println("    </head>");
                out.println("    <body>");
                out.println("        <h1>Hola "+ usernameOptional.get() +"  has iniciado sesión anteriormente</h1>");
                out.println("<p> <a href='"+req.getContextPath()+"/index.jsp'>volver </a></p>");
                out.println("<p> <a href='"+req.getContextPath()+"/logout'>Cerrrar sesión </a></p>");

                out.println("    </body>");
                out.println("</html>");
            }
        }else {
            req.setAttribute("title",req.getAttribute("title") + ": Login");

            getServletContext().getRequestDispatcher("/login.jsp").forward(req,resp);

        }
    
    }
}
