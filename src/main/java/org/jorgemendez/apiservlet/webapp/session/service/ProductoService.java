package org.jorgemendez.apiservlet.webapp.session.service;

import org.jorgemendez.apiservlet.webapp.session.model.Categoria;
import org.jorgemendez.apiservlet.webapp.session.model.Producto;

import javax.swing.text.html.Option;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<Producto> listar() ;
   Optional<Producto> porId(Long id) ;

   void guardar(Producto producto);
   void eliminar(Long id);
   List<Categoria> listarCategoria();
   Optional<Categoria> porIdCategoria(Long id);

}
