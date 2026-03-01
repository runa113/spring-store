package com.efa.store.controller;


import com.efa.store.entity.Producto;
import com.efa.store.service.imp.ProductoServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
public class ProductoController {

    @Autowired
    private ProductoServiceImp productoServiceImp;

    @GetMapping("/nombre")
    public String nombreSitio() {
        return "Tutorial Angular";
    }

    @GetMapping("/all")
    public ArrayList<Producto> getAllProductos(){
        return productoServiceImp.getAllProductos();
    }

    @GetMapping("/find/{id}")
    public Optional<Producto> getUserById(@PathVariable("id") Integer id){
        return productoServiceImp.findById(id);
    }

    @PostMapping("/save")
    public Producto saveProducto(@RequestBody Producto u) {
        return productoServiceImp.saveProducto(u);

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

}
