package com.paraparp.realestatestats.repository.xlsx;

import com.paraparp.realestatestats.model.entities.RealEstateData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.paraparp.realestatestats.utils.Constants.XLSX_FILE_NAME;

@Service
public class XLSXDataWriter {

    public void execute(List<RealEstateData> dataList, String csvFileName) {
        Workbook workbook = new XSSFWorkbook();
        Map<String, Sheet> locationSheetMap = new HashMap<>();

        for (RealEstateData data : dataList) {
            Sheet sheet = locationSheetMap.computeIfAbsent(data.getLocation(), location -> {
                Sheet newSheet = workbook.createSheet(location);
                createHeader(newSheet);
                return newSheet;
            });

            writeDataRow(data, sheet);
        }

        saveWorkbookToFile(workbook, csvFileName);
    }

    private void writeDataRow(RealEstateData data, Sheet sheet) {
        int lastRow = sheet.getLastRowNum();
        int newRowNum = lastRow + 1;

        Row row = sheet.createRow(newRowNum);
        row.createCell(0).setCellValue(data.getDate().toString());
        row.createCell(1).setCellValue(data.getAmount());
        row.createCell(2).setCellValue(data.getMedianPrice());
        row.createCell(3).setCellValue(data.getFirstQuartilePrice());
        row.createCell(4).setCellValue(data.getThirdQuartilePrice());
        row.createCell(5).setCellValue(data.getPriceM2());

        for (int column = 0; column < 6; column++) {
            sheet.autoSizeColumn(column);
        }
    }

    private void saveWorkbookToFile(Workbook workbook, String xlsxFileName) {
        if (!xlsxFileName.endsWith(".xlsx")) {
            throw new UnsupportedOperationException("The file is not an XLSX file");
        }

        try (FileOutputStream fos = new FileOutputStream(xlsxFileName)) {
            workbook.write(fos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createHeader(Sheet sheet) {
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Date");
        header.createCell(1).setCellValue("Amount");
        header.createCell(2).setCellValue("Median Price");
        header.createCell(3).setCellValue("1st Quartile Price");
        header.createCell(4).setCellValue("3rd Quartile Price");
        header.createCell(5).setCellValue("Price per M2");
    }
}
