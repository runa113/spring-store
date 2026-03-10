package com.efa.store.client;

import com.efa.store.dto.RespuestaDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "ApiFakeClient", url = "${client.api-fake}")
public interface ApiFake {

    @GetMapping(value = "/random", produces =  MediaType.APPLICATION_JSON_VALUE)
    RespuestaDTO obtener();

}
