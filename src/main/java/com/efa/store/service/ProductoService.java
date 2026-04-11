package com.efa.store.service;

import com.efa.store.entity.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    //ArrayList<Producto> getAllProductos();

    //Optional<Producto> findById(Integer id);

    //Optional<Producto> getId(Integer id);

    List<Producto> getAllProductos();

    Optional<Producto> findByIdWithOptional(Integer id);

    boolean deleteProductoByIdWithOptional(Integer id);

    Producto saveProducto(Producto u);

    Producto findById(Integer id);

    boolean deleteProductoById(Integer id);

}
