package com.efa.store.mapper;

import com.efa.store.dto.ProductoDTO;
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

    public ProductoDTO toResponse(ProductoProjection projection) {

        return ProductoDTO.builder()
                .id(projection.getId())
                .descripcion(projection.getDescripcion())
                .precio(projection.getPrecio())
                .build();
    }

}
