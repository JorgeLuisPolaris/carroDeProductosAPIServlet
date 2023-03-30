package org.jorgemendez.apiservlet.webapp.session.listener;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.jorgemendez.apiservlet.webapp.session.model.Carro;

@WebListener
public class AplicacionListener implements ServletContextListener, ServletRequestListener, HttpSessionListener {
    private ServletContext servletContext;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().log("Inicializando la app");
        servletContext = sce.getServletContext();
        servletContext.setAttribute("mensaje","Valores globales de la aplicación");//este valor tiene alcance en toda la app
    }
    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        servletContext.log("inicializando el request");
        sre.getServletRequest().setAttribute("mensaje","Valor único del request");
        sre.getServletRequest().setAttribute("title","Catálogo servlet");
        //los atributos creados aquí solo tienen alcance en su petición

    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        servletContext.log("Inicializando la sessión http");
       // Carro carro = new Carro();
       // HttpSession session = se.getSession();
      //  session.setAttribute("carro",carro);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        servletContext.log("Destruyendo la app");

    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        servletContext.log("Destruyendo el request");

    }



    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        servletContext.log("Destruyendo la sessión http");

    }
}
