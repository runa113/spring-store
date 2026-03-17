package com.efa.store.dto.utils;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Date;

/**
 * Objeto para las respuestas base de los endpoints, puede utilizarse para respuestas de error o respuestas que
 * no devuelven datos.
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class BaseRespuestaDto {

    /**
     * Código de estado de la respuesta HTTP.
     */
    @Schema(description = "Código de la respuesta")
    private Integer statusCode;

    /**
     * Mensaje escrito de la respuesta, si es el caso.
     */
    @Schema(description = "Mensaje de respuesta de la operación")
    private String message;

    /**
     * Fecha de la respuesta.
     */
    @Schema(description = "Fecha de respuesta")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    @Builder.Default
    private Date timestamp = new Date();
}
