package com.efa.store.mapper;

import com.efa.store.dto.utils.RespuestaServicioDto;
import lombok.experimental.UtilityClass;

import java.util.Date;

@UtilityClass
public class MapperGenericResponse {

    public static RespuestaServicioDto ok(String message, Object data) {
        return RespuestaServicioDto.builder()
                .message(message)
                .statusCode(200)
                .timestamp(new Date())
                .data(data)
                .build();
    }

}
