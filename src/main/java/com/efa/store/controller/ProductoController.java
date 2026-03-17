package com.efa.store.controller;

import com.efa.store.client.ApiFake;
import com.efa.store.dto.ApiRespuestaDTO;
import com.efa.store.dto.RespuestaDTO;
import com.efa.store.dto.utils.RespuestaServicioDto;
import com.efa.store.entity.Producto;
import com.efa.store.mapper.MapperGenericResponse;
import com.efa.store.service.impl.ProductoServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Tag(name = "productos", description = "Servicios asociados a los productos")
public class ProductoController {

    //@Autowired
    private final ProductoServiceImpl productoServiceImp;

    private final ApiFake apiFake;

/*    public ProductoController(ProductoServiceImpl productoServiceImp, ApiFake apiFake) {
        this.productoServiceImp = productoServiceImp;
        this.apiFake = apiFake;
    }*/


    @GetMapping("/nombre")
    public String nombreSitio() {
        return "Tutorial Angular";
    }

    @Operation(summary = "Solicitud que obtiene todos los productos")
    @ApiResponse(responseCode = "200", description = "Operación exitosa")
    //@ApiResponse(description = "Operación exitosa", responseCode = "200", content = {@Content(mediaType = Constantes.MEDIA_TYPE_JSON)}),
    @ApiResponse(responseCode = "400", description = "Solicitud mal formada o parámetros inválidos.", content = @Content)
    @ApiResponse(responseCode = "404", description = "El recurso solicitado no se ha encontrado. Verifique los parámetros e intente nuevamente.", content = @Content)
    @ApiResponse(responseCode = "500", description = "Error interno del servidor al procesar la solicitud.", content = @Content)

    @GetMapping("/all")
    public List<Producto> getAllProductos(){
        return productoServiceImp.getAllProductos();
    }

    @GetMapping("/findConOptional/{id}")
    public Optional<Producto> findByIdConOptional(@PathVariable("id") Integer id){
        return productoServiceImp.findByIdWithOptional(id);
    }

    @PostMapping("/save")
    public Producto saveProducto(@RequestBody Producto u) {
        return productoServiceImp.saveProducto(u);

    }

    @DeleteMapping("/deleteWithOptional/{id}")
    public String deleteProductoByIdWithOptional(@PathVariable("id") Integer id) {
        if(productoServiceImp.deleteProductoByIdWithOptional(id)) {
            return "Se ha eliminado el producto";
        }
        else {
            return "NO se ha eliminado el producto";
        }
    }

    @GetMapping("/find/{id}")
    public Producto getProductoById(@PathVariable("id") Integer id){
        return productoServiceImp.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProductoById(@PathVariable("id") Integer id) {
        if(productoServiceImp.deleteProductoById(id)) {
            return "Se ha eliminado el producto";
        }
        else {
            return "NO se ha eliminado el producto";
        }
    }

    @GetMapping(value = "/random", produces =  MediaType.APPLICATION_JSON_VALUE)
    ApiRespuestaDTO obtener(){
        RespuestaDTO aux = apiFake.obtener();

        return ApiRespuestaDTO.builder()
                .message(aux.getMessage())
                .status(aux.getStatus())
                .fecha(new Date())
                .build();

    }

    @Operation(summary = "Solicitud que obtiene todos los productos")
    @ApiResponse(responseCode = "200", description = "Operación exitosa")
    @ApiResponse(responseCode = "400", description = "Solicitud mal formada o parámetros inválidos.", content = @Content)
    @ApiResponse(responseCode = "404", description = "El recurso solicitado no se ha encontrado. Verifique los parámetros e intente nuevamente.", content = @Content)
    @ApiResponse(responseCode = "500", description = "Error interno del servidor al procesar la solicitud.", content = @Content)

    @GetMapping("/all2")
    public ResponseEntity<RespuestaServicioDto> consultarAllProductos(){

        List<Producto> list = productoServiceImp.getAllProductos();

        return ResponseEntity.ok(MapperGenericResponse.ok("Productos obtenidos", list));
    }

    @GetMapping("/all3")
    public ResponseEntity<String> consultarAllProductosTres() {
        return ResponseEntity.ok("ok");
    }

}
