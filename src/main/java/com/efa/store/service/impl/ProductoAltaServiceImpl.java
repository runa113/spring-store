package com.efa.store.service.impl;

import com.efa.store.dto.ProductoDTO;
import com.efa.store.dto.request.ProductoRequest;
import com.efa.store.entity.Producto;
import com.efa.store.exception.ResourceNotFoundException;
import com.efa.store.mapper.ProductoMapper;
import com.efa.store.repository.ProductoAltaRepository;
import com.efa.store.repository.ProductoRepository;
import com.efa.store.service.ProductoAltaService;
import com.efa.store.service.utileria.UtileriaExcelService;
import com.efa.store.util.Constantes;
import com.efa.store.util.MapperGenerico;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoAltaServiceImpl implements ProductoAltaService {

    private final UtileriaExcelService utileriaExcel;
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

    @Override
    public ProductoDTO guardar(ProductoRequest request) {

        Producto producto = productoMapper.toEntity(request);

        Producto guardado = productoRepository.save(producto);

        return productoMapper.toDTO(guardado);
    }


    @Override
    @Transactional
    public void guardarVoid(ProductoRequest request) {

        Producto producto = Producto.builder()
                .descripcion(request.getDescripcion())
                .precio(request.getPrecio())
                .build();

        productoRepository.save(producto);
    }


    @Override
    public ProductoDTO actualizar(Integer id, ProductoRequest request) {

        Producto producto = productoRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("No se encontró el producto"));

        producto.setDescripcion(request.getDescripcion());
        producto.setPrecio(request.getPrecio());

        Producto actualizado = productoRepository.save(producto);

        return productoMapper.toDTO(actualizado);
    }

    @Override
    @Transactional
    public void eliminar(Integer id) {

        Producto producto = productoAltaRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("No se encontró el producto"));

        productoAltaRepository.delete(producto);
    }

    @Override
    public String obtenerExcelProductos() {

        String nombreLibro = "Lista productos";
        List<ProductoDTO> listProductoDto = MapperGenerico.mapDTOEntityList(
                productoAltaRepository.obtenerTodosProjectionExel(), ProductoDTO.class);

        List<LinkedHashMap<String, Object>> listData = MapperGenerico.mapDTO(listProductoDto);



        List<List<String>> lista = new ArrayList<>();
        lista.add(Constantes.CABECERA_PRINCIPAL_CATALOGO_REPORTE_PRODUCTOS);
        lista.add(Constantes.CABECERA_REPORTE_PRODUCTOS);

        return utileriaExcel.obtenerBandejaPerfiles(lista, listData, nombreLibro);

    }

}
