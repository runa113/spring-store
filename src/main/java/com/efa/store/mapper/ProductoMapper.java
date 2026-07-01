package com.efa.store.mapper;

import com.efa.store.dto.ProductoDTO;
import com.efa.store.dto.request.ProductoRequest;
import com.efa.store.entity.Producto;
import com.efa.store.entity.ProductoProjection;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper {


    public ProductoDTO toDTO(Producto producto) {

        if (producto == null) {
            return null;
        }

        return ProductoDTO.builder()
                .id(producto.getId())
                .descripcion(producto.getDescripcion())
                .precio(producto.getPrecio())
                .build();
    }

    public Producto toEntity(ProductoRequest request) {

        if (request == null) {
            return null;
        }

        return Producto.builder()
                .descripcion(request.getDescripcion())
                .precio(request.getPrecio())
                .build();

    }

    public ProductoDTO toResponse(ProductoProjection projection) {

        return ProductoDTO.builder()
                .id(projection.getId())
                .descripcion(projection.getDescripcion())
                .precio(projection.getPrecio())
                .build();
    }

}
