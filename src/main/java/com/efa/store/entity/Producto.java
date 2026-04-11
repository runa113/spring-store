package com.efa.store.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "producto", schema = "public")
public class Producto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /*
     * “Usa esta secuencia explícitamente para generar el ID”
     *JPA → SELECT nextval('sclbm.llamada_critica_id_seq')
     * JPA → obtiene el id
     * JPA → INSERT con id incluido
     * */

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "producto_jpa_seq")
    @SequenceGenerator(
            name = "producto_jpa_seq",
            sequenceName = "public.producto_id_seq",
            allocationSize = 1
    )
    private Integer id;

    @Size(max = 40)
    @Column(name = "descripcion", length = 40)
    private String descripcion;

    @Column(name = "precio")
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

*/

    /*
    * Se utiliza para delegar el incremental a POSGRE
    * La base de datos se encarga de generar el ID automáticamente”
    * JPA → INSERT sin id
    * PostgreSQL → genera id con nextval()
    * JPA → obtiene el id generado
    */


/*
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


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

