package com.efa.store.controller;

import com.efa.store.dto.ProductoDTO;
import com.efa.store.dto.request.ProductoRequest;
import com.efa.store.dto.utils.RespuestaServicioDto;
import com.efa.store.mapper.MapperGenericResponse;
import com.efa.store.service.ProductoAltaService;
import com.efa.store.service.ProductoService;
import com.efa.store.service.utileria.UtileriaExcelService;
import com.efa.store.util.Constantes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

@RestController
//@RequestMapping("production/productos")
public class ProductoControllerProduction {

    private final ProductoAltaService productoAltaService;
    private final UtileriaExcelService utileriaExcel;

    public ProductoControllerProduction(ProductoService productoService, ProductoAltaService productoAltaService, UtileriaExcelService utileriaExcel) {

        this.productoAltaService = productoAltaService;
        this.utileriaExcel = utileriaExcel;
    }


    @GetMapping("/production/{idProducto}")
    public ResponseEntity<RespuestaServicioDto> obtenerProducto(@PathVariable Integer idProducto) {

        return ResponseEntity.ok(RespuestaServicioDto.builder()
                .statusCode(200)
                .message("Petición exitosa")
                .data(productoAltaService.obtenerProducto(idProducto))
                .timestamp(new Date())
                .build());
    }

    @GetMapping("/production/findDtoById/{idProducto}")
    public ResponseEntity<RespuestaServicioDto> findDtoById(@PathVariable Integer idProducto) {

        return ResponseEntity.ok(RespuestaServicioDto.builder()
                .statusCode(200)
                .message("Petición exitosa")
                .data(productoAltaService.findDtoById(idProducto))
                .timestamp(new Date())
                .build());
    }


    @Operation(summary = "Recupera datos de llamada periódica asociados a partir del id de Caso y el id de la llamada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Petición exitosa",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = RespuestaServicioDto.class))})
    })
    @GetMapping("/findAllProductosMapper")
    ResponseEntity<RespuestaServicioDto> listarProductos() {

        List<ProductoDTO> productos = productoAltaService.findAllProductosMapper();
        return ResponseEntity.ok(
                RespuestaServicioDto.builder()
                        .statusCode(200)
                        .message("Productos obtenidos correctamente")
                        .timestamp(new java.util.Date())
                        .data(productos)
                        .build()
        );
    }


    //2
    @GetMapping("/obtenerTodosMapperDto")
    //public ResponseEntity<List<ProductoDTO>> obtenerTodos()
    public ResponseEntity<RespuestaServicioDto> obtenerTodos() {
        List<ProductoDTO> productos = productoAltaService.obtenerTodosMapperDto();
        //return ResponseEntity.ok(productoAltaService.obtenerTodosMapperDto());
        return ResponseEntity.ok(
                RespuestaServicioDto.builder()
                        .statusCode(200)
                        .message("Productos obtenidos correctamente")
                        .timestamp(new java.util.Date())
                        .data(productos)
                        .build()
        );
    }


    //3
    @GetMapping("/listar-projection")
    ResponseEntity<RespuestaServicioDto> obtenerTodosProjection() {

        List<ProductoDTO> productos = productoAltaService.obtenerTodosProjection() ;
        return ResponseEntity.ok(
                RespuestaServicioDto.builder()
                        .statusCode(200)
                        .message("Productos obtenidos correctamente")
                        .timestamp(new java.util.Date())
                        .data(productos)
                        .build()
        );
    }

    @PutMapping("/estandar/{id}")
    public ResponseEntity<ProductoDTO> actualizar(
            @PathVariable Integer id,
            @RequestBody ProductoRequest request) {

        ProductoDTO response = productoAltaService.actualizar(id, request);

        return ResponseEntity.ok(response);
    }

    @PostMapping(
            value = "/guardar",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ProductoDTO> guardar(
            @Valid @RequestBody ProductoRequest request) {

        ProductoDTO response = productoAltaService.guardar(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping(
            value = "/guardarVoid",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<RespuestaServicioDto> guardarVoid(
            @Valid @RequestBody ProductoRequest request) {

        productoAltaService.guardarVoid(request);

        return ResponseEntity.ok(
                RespuestaServicioDto.builder()
                        .statusCode(200)
                        .message("Producto guardado correctamente")
                        .timestamp(new Date())
                        .data(null)
                        .build()
        );
    }

    @PutMapping(value = "/{id}",  produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaServicioDto> actualizarProducto(
            @PathVariable Integer id,
            @RequestBody ProductoRequest request) {

        ProductoDTO response = productoAltaService.actualizar(id, request);

        return ResponseEntity.ok(
                RespuestaServicioDto.builder()
                        .statusCode(200)
                        .message("Producto actualizado correctamente")
                        .timestamp(new java.util.Date())
                        .data(response)
                        .build()
        );
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {

        productoAltaService.eliminar(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/reporteEstatico")
    @Operation(summary = "Solicitud que obtiene el reporte de perfiles")
    @ApiResponse(description = "Operación que obtiene el reporte de la bandeja de perfiles", responseCode = Constantes.HTTP_200_SUCCESS, content = {@Content(mediaType = Constantes.MEDIA_TYPE_JSON)})
    @ApiResponse(responseCode = Constantes.HTTP_404_NOT_FOUND, description = Constantes.DESC_HTTP_404, content = @Content)
    @ApiResponse(responseCode = Constantes.HTTP_500_INTERNAL_ERROR, description = Constantes.DESC_HTTP_500, content = @Content)

    public ResponseEntity<RespuestaServicioDto> consultarEstatusSolicitud(){

        // === 1️⃣ Crear lista principal de datos ===
        List<LinkedHashMap<String, Object>> listData = new ArrayList<>();

        // === 2️⃣ Crear primer registro ===
        LinkedHashMap<String, Object> fila1 = new LinkedHashMap<>();
        fila1.put("Id", 1);
        fila1.put("Descripción", "Limón");
        fila1.put("Precio", 30);

        // === 3️⃣ Segundo registro ===
        LinkedHashMap<String, Object> fila2 = new LinkedHashMap<>();
        fila2.put("Id", 2);
        fila2.put("Descripción", "Mandarina");
        fila2.put("Precio", 20);

        // === 3er registro ===
        LinkedHashMap<String, Object> fila3 = new LinkedHashMap<>();
        fila3.put("Id", 3);
        fila3.put("Descripción", "Naranja");
        fila3.put("Precio", 15);

        // === 4️⃣ Agregar filas a la lista ===
        listData.add(fila1);
        listData.add(fila2);
        listData.add(fila3);


        List<List<String>> lista = new ArrayList<>();

        lista.add(Constantes.CABECERA_PRINCIPAL_CATALOGO_REPORTE_PRODUCTOS);
        lista.add(Constantes.CABECERA_REPORTE_PRODUCTOS);

        String excel =  utileriaExcel.obtenerBandejaPerfiles(lista, listData, "Cítricos");

        return ResponseEntity.ok(MapperGenericResponse.ok("Reporte generado",
                excel));
    }


    @PostMapping(value = "/reporte", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Solicitud que obtiene el reporte de productos")
    @ApiResponse(description = "Operación que obtiene el reporte de productos", responseCode = Constantes.HTTP_200_SUCCESS, content = {@Content(mediaType = Constantes.MEDIA_TYPE_JSON)})
    @ApiResponse(responseCode = Constantes.HTTP_404_NOT_FOUND, description = Constantes.DESC_HTTP_404, content = @Content)
    @ApiResponse(responseCode = Constantes.HTTP_500_INTERNAL_ERROR, description = Constantes.DESC_HTTP_500, content = @Content)

    public ResponseEntity<RespuestaServicioDto> consultarEstatusSolicitud(
            //@RequestParam(required = false) String "Ejemplo",
            @RequestBody ProductoRequest filtros
    ){
        String excel = productoAltaService.obtenerExcelProductos();
        return ResponseEntity.ok(MapperGenericResponse.ok("Reporte generado",
                excel));
    }

}
