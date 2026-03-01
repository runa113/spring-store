package com.efa.store.service.imp;

import com.efa.store.entity.Producto;
import com.efa.store.repository.ProductoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductoServiceImp {

    //@Autowired
    private ProductoRepository productoRepository;

    public ArrayList<Producto> getAllProductos(){
        return (ArrayList<Producto>) productoRepository.findAll();
    }

    public Optional<Producto> findById(Integer id) {

        try{
            Producto producto = productoRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));

            return productoRepository.findById(id);

        } catch (Exception e){
            throw e;
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

    public boolean deleteProductoById(Integer id) {
        try {
            Optional<Producto> u = findById(id);
            productoRepository.delete(u.get());
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
