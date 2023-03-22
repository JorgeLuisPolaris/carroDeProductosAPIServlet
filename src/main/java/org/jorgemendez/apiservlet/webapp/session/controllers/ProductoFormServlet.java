package org.jorgemendez.apiservlet.webapp.session.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jorgemendez.apiservlet.webapp.session.model.Categoria;
import org.jorgemendez.apiservlet.webapp.session.model.Producto;
import org.jorgemendez.apiservlet.webapp.session.service.ProductoService;
import org.jorgemendez.apiservlet.webapp.session.service.ProductoServiceJdbcImpl;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/productos/form")
public class ProductoFormServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        ProductoService service = new ProductoServiceJdbcImpl(conn);
        long id;
        try{
            id = Long.parseLong(req.getParameter("id"));
        }catch (NumberFormatException e){
            id = 0L;
        }
        Producto producto = new Producto();
        producto.setCategoria(new Categoria());
        if (id>0L){
            Optional<Producto> optionalProducto = service.porId(id);
            if (optionalProducto.isPresent()){
                producto = optionalProducto.orElseThrow();
            }
        }
        req.setAttribute("categorias",service.listarCategoria());

        req.setAttribute("producto",producto);
        req.setAttribute("title",req.getAttribute("title") + ": Formulario de productos");

        getServletContext().getRequestDispatcher("/form.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       Connection conn = (Connection) req.getAttribute("conn");
       ProductoService service = new ProductoServiceJdbcImpl(conn);
       String nombre = req.getParameter("nombre");

       Integer precio;
       try {
           precio = Integer.valueOf(req.getParameter("precio"));
       }catch (NumberFormatException e){
            precio = 0;
       }
       String sku = req.getParameter("sku");
       String fechaStr = req.getParameter("fecha_registro");
       Long categoriaId;
       try {
           categoriaId = Long.parseLong(req.getParameter("categoria"));
       }catch (NumberFormatException e){
           categoriaId = 0L;
       }

        Map<String,String> errores = new HashMap<>();
       if (nombre==null || nombre.isBlank()){
           errores.put("nombre","El nombre no puede estar vacio");
       }

       if (sku==null|| sku.isBlank()){
           errores.put("sku","El sku es requerido");
       }else if (sku.length()>10){
           errores.put("sku","El sku es como máximo 10 caracteres");

       }

       if (fechaStr==null || fechaStr.isBlank()){
           errores.put("fecha_registro","La fecha es requerida");
       }
       if (precio.equals(0)){
           errores.put("precio","El precio es requerido");
       }
       if (categoriaId.equals(0L)){
           errores.put("categoria","La categoría es requerida");
       }
        LocalDate fecha;
       try{
           fecha= LocalDate.parse(fechaStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

       }catch (DateTimeParseException e ){
           fecha = null;
       }
       long id;

       try{
           id = Long.parseLong(req.getParameter("id"));
       }catch (NumberFormatException e){
           id = 0L; //este id nos sirve como swtich para saber si se hará update o se guardará uno nuevo
       }


       Producto producto = new Producto();
       producto.setId(id);

        producto.setPrecio(precio);
        producto.setSku(sku);
        producto.setNombre(nombre);
        producto.setFecha(fecha);
        Categoria c = new Categoria();
        c.setId(categoriaId);
        producto.setCategoria(c);
       if (errores.isEmpty()) {
           service.guardar(producto);
           resp.sendRedirect(req.getContextPath() + "/productos");
       }else {
           req.setAttribute("errores",errores);
           req.setAttribute("categorias",service.listarCategoria());
           req.setAttribute("producto",producto);
           req.setAttribute("title",req.getAttribute("title") + ": Formulario de productos");

           getServletContext().getRequestDispatcher("/form.jsp").forward(req,resp);
       }
    }
}
