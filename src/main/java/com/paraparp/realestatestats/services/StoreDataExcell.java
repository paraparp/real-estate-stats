package com.paraparp.realestatestats.services;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
public class StoreDataExcell {

    private static final String CSV_FILE_NAME = "data.xlsx" ;

    public void storeInXls(List<RealEstateData> dataList) {

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Datos Inmuebles");

        // Crear fila de header
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Fecha");
        header.createCell(1).setCellValue("Ubicación");
        header.createCell(2).setCellValue("Importe");
        header.createCell(3).setCellValue("Tipo");
        header.createCell(4).setCellValue("Percentil13");
        header.createCell(5).setCellValue("Percentil33");
        header.createCell(6).setCellValue("PrecioM2");

        Set<String> combinacionesExistentes = new HashSet<>();
        for (Row fila : sheet) {
            String fecha = fila.getCell(0).getStringCellValue();
            String ubicacion = fila.getCell(1).getStringCellValue();
            combinacionesExistentes.add(fecha + ubicacion);
        }

        int ultimaFila = sheet.getLastRowNum();
        int filaNum = ultimaFila + 1;

        for (RealEstateData data : dataList) {


            if (!combinacionesExistentes.contains(data.id())) {
                Row row = sheet.createRow(filaNum++);
                row.createCell(0).setCellValue(data.getDate());
                row.createCell(1).setCellValue(data.getLocation());
                row.createCell(2).setCellValue(data.getAmount());
                row.createCell(3).setCellValue(data.getType0());
                row.createCell(4).setCellValue(data.getPercentil13());
                row.createCell(5).setCellValue(data.getPercentil33());
                row.createCell(6).setCellValue(data.getPriceM2());
            }

            FileOutputStream fos;
            try {
                fos = new FileOutputStream(CSV_FILE_NAME);
                workbook.write(fos);
                fos.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

}
