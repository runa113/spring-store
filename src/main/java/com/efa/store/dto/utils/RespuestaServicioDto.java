package com.efa.store.dto.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * Objeto de respuesta específico para consultas a endpoints que devuelven información.
 */
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RespuestaServicioDto extends BaseRespuestaDto {
    /**
     * Objeto con los datos de la respuesta.
     */
    @Schema(description = "Datos de la respuesta, puede ser un objeto o un arreglo de objetos")
    private Object data;
}
