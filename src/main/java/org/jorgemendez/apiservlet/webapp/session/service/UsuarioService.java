package org.jorgemendez.apiservlet.webapp.session.service;

import org.jorgemendez.apiservlet.webapp.session.model.Producto;
import org.jorgemendez.apiservlet.webapp.session.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    Optional<Usuario> login(String username, String password);
    void guardar(Usuario usuario);
    void eliminar(Long id);
    List<Usuario> listar() ;
    Optional<Usuario> porId(Long id) ;

}
