package com.efa.store.util;

import org.apache.commons.lang3.StringUtils;

public class Util {

    private Util() {
        //Not intended to be instantiated
    }

    public static Object validObject(Object obj) {
        if (obj != null && StringUtils.isNotBlank(obj.toString())) {
            return obj;
        } else {
            return "";
        }
    }

/*
    //Método para convertir a base64
    public static ArchivoDto descargarArchivo(List<Object[]> result, String notFoundMessage) throws IOException {
        if (result == null || result.isEmpty()) {
            throw new ArchivoNoEncontradoException(notFoundMessage);
        }
        Object[] resultFile = result.get(0);
        String fileName = (String) resultFile[0];
        String filePath = (String) resultFile[1];

        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            throw new FileNotFoundException("El archivo físico no existe en la ruta: " + filePath);
        }

        byte[] fileBytes = Files.readAllBytes(path);
        String base64 = Base64.getEncoder().encodeToString(fileBytes);

        return new ArchivoDto(fileName, base64);
    }
    */
}
