package com.efa.store.service;

import com.efa.store.dto.ProductoDTO;
import com.efa.store.dto.request.ProductoRequest;
import com.efa.store.exception.ResourceNotFoundException;

import java.util.List;

public interface ProductoAltaService {

    ProductoDTO obtenerProducto(Integer id) throws ResourceNotFoundException;

    ProductoDTO findDtoById(Integer id) throws ResourceNotFoundException;

    List<ProductoDTO> findAllProductosMapper();

    List<ProductoDTO> obtenerTodosMapperDto();

    List<ProductoDTO> obtenerTodosProjection();

    ProductoDTO guardar(ProductoRequest request);

    void guardarVoid(ProductoRequest request);

    ProductoDTO actualizar(Integer id, ProductoRequest request);

    void eliminar(Integer id);

    String obtenerExcelProductos();
}
