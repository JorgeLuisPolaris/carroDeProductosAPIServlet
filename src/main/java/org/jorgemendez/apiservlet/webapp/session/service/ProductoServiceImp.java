package org.jorgemendez.apiservlet.webapp.session.service;

import org.jorgemendez.apiservlet.webapp.session.model.Categoria;
import org.jorgemendez.apiservlet.webapp.session.model.Producto;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProductoServiceImp implements ProductoService {
    @Override
    public void guardar(Producto producto) {

    }

    @Override
    public void eliminar(Long id) {

    }

    @Override
    public List<Categoria> listarCategoria() {
        return null;
    }

    @Override
    public Optional<Categoria> porIdCategoria(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Producto> listar() {
        return Arrays.asList(new Producto(1L, "notebook", "computación", 175000),
                new Producto(2L, "mesa escritorio", "oficina", 100000),
                new Producto(3L, "teclado mecánico", "computación", 40000));
    }

    @Override
    public Optional<Producto> porId(Long id) {
        return listar().stream().filter(i->i.getId().equals(id)).findFirst();
    }
}
