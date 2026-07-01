package com.efa.store.service.utileria;

import java.util.LinkedHashMap;
import java.util.List;

public interface UtileriaExcelService {
    String obtenerBandejaPerfiles(List < List<String> > encabezados,
                                  List <LinkedHashMap<String, Object>>data,
                                  String nombreLibro);
}
