package com.efa.store.service.utileria.impl;

import com.efa.store.service.utileria.UtileriaExcelService;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static com.efa.store.util.Util.validObject;

@Service
public class UtileriaExcelServiceImpl implements UtileriaExcelService {

    public String obtenerBandejaPerfiles(List<List<String>> encabezados,
                                         List<LinkedHashMap<String, Object>> data,
                                         String nombreLibro) {

        try (Workbook workbook = new XSSFWorkbook()) {
            // Crear una hoja en el libro
            Sheet sheet = workbook.createSheet(nombreLibro);

            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, encabezados.get(1).size() - 1));

            // ===== Estilo SOLO para la PRIMERA FILA (título fusionado) =====
            CellStyle titleStyle = workbook.createCellStyle();
            Font titleFont = workbook.createFont();
            titleFont.setBold(true);
            ((org.apache.poi.xssf.usermodel.XSSFFont) titleFont)
                    .setColor(new org.apache.poi.xssf.usermodel.XSSFColor(new java.awt.Color(255, 255, 255), null));
            titleStyle.setFont(titleFont);
            ((org.apache.poi.xssf.usermodel.XSSFCellStyle) titleStyle)
                    .setFillForegroundColor(new org.apache.poi.xssf.usermodel.XSSFColor(new java.awt.Color(59, 56, 56), null));
            titleStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            titleStyle.setBorderBottom(BorderStyle.THIN);
            titleStyle.setBorderTop(BorderStyle.THIN);
            titleStyle.setBorderRight(BorderStyle.THIN);
            titleStyle.setBorderLeft(BorderStyle.THIN);
            titleStyle.setAlignment(HorizontalAlignment.CENTER);
            titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);

            // ===== Estilo para el RESTO DE ENCABEZADOS (filas 1..n) =====
            CellStyle headerStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerStyle.setFont(headerFont);
            headerStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
            headerStyle.setFillPattern(FillPatternType.NO_FILL);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBorderTop(BorderStyle.THIN);
            headerStyle.setBorderRight(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setAlignment(HorizontalAlignment.CENTER);

            // ===== Crear filas de encabezado aplicando estilo condicional =====
            AtomicInteger numRowHeader = new AtomicInteger(0);
            encabezados.forEach(e -> {
                Row headerRow = sheet.createRow(numRowHeader.get());
                AtomicInteger numCell = new AtomicInteger(0);
                e.forEach(x -> {
                    Cell cell = headerRow.createCell(numCell.get());
                    cell.setCellValue(x);
                    // Solo la PRIMERA FILA usa el estilo oscuro (titleStyle)
                    cell.setCellStyle(numRowHeader.get() == 0 ? titleStyle : headerStyle);
                    sheet.setColumnWidth(numCell.get(), 5000);
                    numCell.getAndIncrement();
                });
                numRowHeader.getAndIncrement();
            });

            AtomicInteger numRow = new AtomicInteger(encabezados.size());

            // Estilo para los datos
            CellStyle dataStyle = workbook.createCellStyle();
            dataStyle.setBorderBottom(BorderStyle.THIN);
            dataStyle.setBorderTop(BorderStyle.THIN);
            dataStyle.setBorderRight(BorderStyle.THIN);
            dataStyle.setBorderLeft(BorderStyle.THIN);

            data.forEach(e -> {

                // Crear la fila de datos
                Row dataRow = sheet.createRow(numRow.get());
                AtomicInteger numCell = new AtomicInteger(0);

                e.forEach((llave, valor) -> {
                    this.createCell(dataRow, numCell.get(), validObject(valor), dataStyle);
                    numCell.getAndIncrement();
                });
                numRow.getAndIncrement();
            });

            //ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            //workbook.write(outputStream);
            //String base64String = Base64.getEncoder().encodeToString(outputStream.toByteArray());
            //return  base64String;

            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                workbook.write(outputStream);


                convertirBase64AExcel( Base64.getEncoder().encodeToString(outputStream.toByteArray()));
                this.escribir(outputStream.toByteArray());//----> Pruebas en local

                return Base64.getEncoder().encodeToString(outputStream.toByteArray());
            }

        } catch (Exception e) {
            return null;
        }
    }

    private void createCell(Row row, int colNum, Object value, CellStyle style) {
        Cell cell = row.createCell(colNum);
        if (value != null) {
            cell.setCellValue(value.toString());
        } else {
            cell.setCellValue("");
        }
        cell.setCellStyle(style);
    }

    private void escribir(byte[] datosParaEscribir) {
        Path rutaArchivo = Paths.get("D:\\ejemplo.xls");

        try {
            Files.write(rutaArchivo, datosParaEscribir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Convierte un string en Base64 a un archivo Excel físico (.xlsx).
     *
     * @param base64Excel Cadena en Base64 que representa el Excel
     */
    public static void convertirBase64AExcel(String base64Excel) {
        try {
            // Decodificar Base64 a bytes
            byte[] excelBytes = Base64.getDecoder().decode(base64Excel);
            String filePath = "D:\\reporteDos.xlsx";

            // Guardar en un archivo físico
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                fos.write(excelBytes);
                fos.flush();
            }

            System.out.println("Archivo Excel generado correctamente en: " + filePath);

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al generar el archivo Excel desde Base64", e);
        }
    }

}
