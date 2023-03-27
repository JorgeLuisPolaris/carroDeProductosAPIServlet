package org.jorgemendez.apiservlet.webapp.session.repositories;

import org.jorgemendez.apiservlet.webapp.session.model.Usuario;

import java.sql.SQLException;

public interface UsuarioRepository extends Repository<Usuario>{

    Usuario porUsername(String username) throws SQLException;


}
