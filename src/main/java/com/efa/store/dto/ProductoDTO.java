package com.efa.store.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

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
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductoDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "ID del producto")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;

    @Size(max = 40)
    @Schema(description = "Descripción del producto")
    private String descripcion;

    @Schema(description = "Precio del producto")
    private Double precio;

}
