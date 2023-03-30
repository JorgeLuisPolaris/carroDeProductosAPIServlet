package org.jorgemendez.apiservlet.webapp.session.repositories;

import jakarta.inject.Inject;
import org.jorgemendez.apiservlet.webapp.session.configs.MysqlConnPrincipal;
import org.jorgemendez.apiservlet.webapp.session.configs.Repositorio;
import org.jorgemendez.apiservlet.webapp.session.model.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Repositorio
public class CategoriaRepositoryImpl implements Repository<Categoria> {
    Connection conn;

    @Inject
    public CategoriaRepositoryImpl(@MysqlConnPrincipal Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Categoria> listar() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        try(Statement stmt = conn.createStatement();
            ResultSet rs= stmt.executeQuery("SELECT * FROM categorias")){
            while (rs.next()){
                Categoria categoria = getCategoria(rs);
                categorias.add(categoria);
            }
        }
        return categorias;
    }



    @Override
    public Categoria porId(Long id) throws SQLException {
        Categoria categoria = null;
    try(PreparedStatement stmt = conn.prepareStatement("SELECT * FROM categorias as c where c.id=?")){
        stmt.setLong(1,id);
        try(ResultSet rs = stmt.executeQuery()){

            if (rs.next()){
                categoria = getCategoria(rs);
            }
        }
    }
        return categoria;
    }

    @Override
    public void guardar(Categoria categoria) throws SQLException {

    }

    @Override
    public void eliminar(Long id) throws SQLException {

    }

    private static Categoria getCategoria(ResultSet rs) throws SQLException {
        Categoria categoria = new Categoria();
        categoria.setNombre(rs.getString("nombre"));
        categoria.setId(rs.getLong("id"));
        return categoria;
    }
}
