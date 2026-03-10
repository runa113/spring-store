package com.efa.store.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder

public class ApiRespuestaDTO {
    private String message;
    private String status;
    private Date fecha;
}
