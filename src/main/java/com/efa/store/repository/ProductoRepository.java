package com.efa.store.repository;

import com.efa.store.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {











    //No usar,
    //Cuando vas a modificar o eliminar la entidad sin necesitar leer sus dato
    //Producto p = productoRepository.getById(id);
    //p.setPrecio(100);
    //productoRepository.save(p);
    //Producto getById(Integer id);

}
