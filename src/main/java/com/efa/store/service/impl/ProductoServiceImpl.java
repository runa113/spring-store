package com.efa.store.service.impl;

import com.efa.store.entity.Producto;
import com.efa.store.repository.ProductoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductoServiceImpl {

    //@Autowired
    private ProductoRepository productoRepository;

/*    public ArrayList<Producto> getAllProductos(){
        return (ArrayList<Producto>) productoRepository.findAll();
    }*/

    public List<Producto> getAllProductos(){
        return productoRepository.findAll();
    }

    public Optional<Producto> findByIdWithOptional(Integer id) {

        try{
            Producto producto = productoRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));

            return productoRepository.findById(id);

        } catch (Exception e){
            throw e;
        }

    }

    public boolean deleteProductoByIdWithOptional(Integer id) {
        try {
            Optional<Producto> u = findByIdWithOptional(id);
            productoRepository.delete(u.get());
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public Producto saveProducto(Producto u) {
        return productoRepository.save(u);
    }

    /*

        public Optional<Producto> getById(Integer id) {
        return Optional.ofNullable(productoRepository.getById(id));
    }
    */

    public Producto findById(Integer id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado con id: " + id));
    }

    public boolean deleteProductoById(Integer id) {
        Producto u = findById(id);
        productoRepository.delete(u);
        return true;
    }
}
