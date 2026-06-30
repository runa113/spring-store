package com.efa.store.service.impl;

import com.efa.store.dto.ProductoDTO;
import com.efa.store.entity.Producto;
import com.efa.store.exception.ResourceNotFoundException;
import com.efa.store.mapper.ProductoMapper;
import com.efa.store.repository.ProductoAltaRepository;
import com.efa.store.repository.ProductoRepository;
import com.efa.store.service.ProductoAltaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoAltaServiceImpl implements ProductoAltaService {

    private final ProductoRepository productoRepository;
    private final ProductoAltaRepository productoAltaRepository;

    private final ProductoMapper productoMapper;

    @Override
    public ProductoDTO obtenerProducto(Integer id) throws ResourceNotFoundException {

        Producto productoEntity = productoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No se encontro el producto"));

        ProductoDTO dto = productoMapper.toDTO(productoEntity);
        return dto;
    }

    public ProductoDTO findDtoById(Integer id) throws ResourceNotFoundException {
        ProductoDTO productoDTO = productoAltaRepository.findDtoById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontro el producto"));
        return productoDTO;

    }

/*    @Override
    public List<ProductoDTO> findAllProductos() {
        return List.of();
    }*/


    @Override
    public List<ProductoDTO> findAllProductosMapper() {
        return productoRepository.findAll()
                .stream()
                //.map(productoMapper::toDTO)
                .map(this::toDto)
                .toList();
    }

    @Override
    public List<ProductoDTO> obtenerTodosMapperDto() {
        //List<ProductoDTO> productoDTOS = new ArrayList<>();
        return productoAltaRepository.obtenerTodosMapperDto();
    }


/*
    public List<ProductoDTO> findAllProductosMapper() {
        List<Producto> productos = productoRepository.findAll();
        return productos
                .stream()
                .map(producto ->
                        new ProductoDTO(
                                producto.getId(),
                                producto.getDescripcion(),
                                producto.getPrecio()))
                .toList();
    }
*/

    private ProductoDTO toDto(Producto producto) {

        if (producto == null) {
            return null;
        }

        return ProductoDTO.builder()
                .id(producto.getId())
                .descripcion(producto.getDescripcion())
                .precio(producto.getPrecio())
                .build();

        //Creando el objeto ProductoDTO

/*        return new ProductoDTO(
                producto.getId(),
                producto.getDescripcion(),
                producto.getPrecio()
        );*/

    }

    @Override
    public List<ProductoDTO> obtenerTodosProjection() {
        return productoAltaRepository.obtenerTodosProjection()
                .stream()
                .map(productoMapper::toResponse)
                .toList();
    }

}
