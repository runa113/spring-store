package com.efa.store.repository;

import com.efa.store.dto.ProductoDTO;
import com.efa.store.entity.Producto;
import com.efa.store.entity.ProductoProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoAltaRepository extends JpaRepository<Producto, Integer> {
    Optional<Producto> findById(Integer id);

    @Query("""
    SELECT new com.efa.store.dto.ProductoDTO(
            p.id,
            p.descripcion,
            p.precio
        )
        FROM Producto p
        WHERE p.id = :id
    """)
    Optional<ProductoDTO> findDtoById(Integer id);

    @Query("""
        SELECT new com.efa.store.dto.ProductoDTO(
            p.id,
            p.descripcion,
            p.precio
                )
        FROM Producto p
    """)
    List<ProductoDTO> obtenerTodosMapperDto();

    @Query("""
        SELECT
            p.id AS id,
            p.descripcion AS descripcion,
            p.precio AS precio
        FROM Producto p
    """)
    List<ProductoProjection> obtenerTodosProjection();

}
