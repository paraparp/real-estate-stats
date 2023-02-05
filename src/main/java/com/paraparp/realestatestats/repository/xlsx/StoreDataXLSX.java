package com.paraparp.realestatestats.repository.xlsx;

import com.paraparp.realestatestats.model.entities.RealEstateData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

import static com.paraparp.realestatestats.utils.Constants.XLSX_FILE_NAME;

@Service
public class StoreDataXLSX {

    Set<String> existingIds = new HashSet<>();

    public void insert(List<RealEstateData> dataList) {

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Real Estate Data");
        createHeader(sheet);
        Set<String> existingIds = getExistingIds(sheet);


        writeData(dataList, workbook, sheet, existingIds);
    }

    private void writeData(List<RealEstateData> dataList, Workbook workbook, Sheet sheet, Set<String> existingIds) {

        String xlsxFileName = XLSX_FILE_NAME;
        if (!xlsxFileName.endsWith(".xlsx")) {
            throw new UnsupportedOperationException("The file is not an XLSX file");
        }

        int lastRow = sheet.getLastRowNum();
        int numRow = lastRow + 1;

        for (RealEstateData data : dataList) {
            if (!existingIds.contains(data.id())) {
                Row row = sheet.createRow(numRow++);
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
                fos = new FileOutputStream(xlsxFileName);
                workbook.write(fos);
                fos.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

    private Set<String> getExistingIds(Sheet sheet) {

        for (Row fila : sheet) {
            String date = fila.getCell(0).getStringCellValue();
            String location = fila.getCell(1).getStringCellValue();
            existingIds.add(date + location);
        }
        return existingIds;
    }

    private void createHeader(Sheet sheet) {
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Fecha");
        header.createCell(1).setCellValue("Ubicación");
        header.createCell(2).setCellValue("Importe");
        header.createCell(3).setCellValue("Tipo");
        header.createCell(4).setCellValue("Percentil13");
        header.createCell(5).setCellValue("Percentil33");
        header.createCell(6).setCellValue("PrecioM2");
    }

}
