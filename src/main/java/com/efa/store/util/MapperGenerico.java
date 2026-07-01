package com.efa.store.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import lombok.experimental.UtilityClass;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

@UtilityClass
public class MapperGenerico {
    private static final ModelMapper MAPPER = new ModelMapper();
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static <D, T> List<T> mapDTOEntityList(List<D> entityList, Class<T> dtoClass) {

        List<T> listaDto = new ArrayList<>();
        entityList.forEach(dto -> listaDto.add(MAPPER.map(dto, dtoClass)));
        return listaDto;
    }


    public static <D> List<LinkedHashMap<String, Object>> mapDTO(List<D> entityList) {

        List<LinkedHashMap<String, Object>> listaDto = new ArrayList<>();
        entityList.forEach(dto -> listaDto.add(OBJECT_MAPPER.convertValue(dto, LinkedHashMap.class)));
        return listaDto;
    }

    public static String filtrosToJson(Object filtros) throws JsonProcessingException {
        if (Objects.isNull(filtros)) return "{}";

        OBJECT_MAPPER.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        return OBJECT_MAPPER.writeValueAsString(filtros);

    }

    public static JsonNode dtoToJsonNode(Object dto){
        OBJECT_MAPPER.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        OBJECT_MAPPER.nullNode();
        return OBJECT_MAPPER.valueToTree(dto);
    }

    public static <T, D> D mapEntity(T entity, Class<D> dtoClass) {
        return MAPPER.map(entity, dtoClass);
    }

}
