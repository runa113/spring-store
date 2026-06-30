package com.efa.store.service;

import com.efa.store.dto.ProductoDTO;
import com.efa.store.exception.ResourceNotFoundException;

import java.util.List;

public interface ProductoAltaService {

    ProductoDTO obtenerProducto(Integer id) throws ResourceNotFoundException;

    ProductoDTO findDtoById(Integer id) throws ResourceNotFoundException;

    List<ProductoDTO> findAllProductosMapper();

    List<ProductoDTO> obtenerTodosMapperDto();

    List<ProductoDTO> obtenerTodosProjection();
}
