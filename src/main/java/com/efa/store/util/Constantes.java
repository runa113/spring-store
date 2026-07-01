package com.efa.store.util;

import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class Constantes {

    // Códigos HTTP estándar
    public static final String HTTP_200_SUCCESS = "200";
    public static final String HTTP_201_CREATED = "201";
    public static final String HTTP_204_NO_CONTENT = "204";
    public static final String HTTP_210_CUSTOM_SUCCESS = "210";
    public static final String HTTP_400_BAD_REQUEST = "400";
    public static final String HTTP_401_UNAUTHORIZED = "401";
    public static final String HTTP_403_FORBIDDEN = "403";
    public static final String HTTP_404_NOT_FOUND = "404";
    public static final String HTTP_409_CONFLICT = "409";
    public static final String HTTP_410_GONE = "410";
    public static final String HTTP_411_LENGTH_REQUIRED = "411";
    public static final String HTTP_412_PRECONDITION_FAILED = "412";
    public static final String HTTP_422_UNPROCESSABLE_ENTITY = "422";
    public static final String HTTP_500_INTERNAL_ERROR = "500";

    // Descripciones HTTP estándar
    public static final String DESC_HTTP_401 = "Autenticación requerida: token ausente, inválido o expirado.";
    public static final String DESC_HTTP_404 = "El recurso solicitado no se ha encontrado. Verifique los parámetros e intente nuevamente.";
    public static final String DESC_HTTP_500 = "Error interno del servidor al procesar la solicitud.";
    public static final String DESC_HTTP_400 = "Solicitud mal formada o parámetros inválidos.";
    public static final String DESC_HTTP_403 = "No tiene permisos para realizar esta acción.";
    public static final String DESC_HTTP_409 = "Los datos enviados violan una restricción de integridad en la base de datos.";
    public static final String DESC_HTTP_422 = "Error al guardar/actualizar la información.";

    // Media Types
    public static final String MEDIA_TYPE_JSON = "application/json";

    public static final List<String> CABECERA_PRINCIPAL_CATALOGO_REPORTE_PRODUCTOS  = List.of(
            "REPORTE DE PRODUCTOS");

    public static final List<String> CABECERA_REPORTE_PRODUCTOS = List.of(
            "Id",
            "Descripción",
            "Precio");

}
