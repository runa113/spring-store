package com.efa.store.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String descripcion;
    private Double precio;

/*    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }





    SELECT * FROM public.producto


CREATE SEQUENCE producto_id_seq
    START 1
    INCREMENT 1;

   ALTER TABLE public.producto
    ALTER COLUMN id SET DEFAULT nextval('producto_id_seq');

   SELECT setval('producto_id_seq', (SELECT MAX(id) FROM public.producto));


  INSERT INTO public.producto(descripcion, precio)
VALUES ('chayotes', 9.89);

    */


}
