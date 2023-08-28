package com.paraparp.realestatestats.controller;


import com.paraparp.realestatestats.repository.csv.CSVDataReader;
import com.paraparp.realestatestats.repository.jdbc.DataService;
import com.paraparp.realestatestats.services.IdealistaService;
import com.paraparp.realestatestats.model.entities.RealEstateData;
import com.paraparp.realestatestats.repository.csv.CSVDataWriter;
import com.paraparp.realestatestats.repository.xlsx.XLSXDataWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

import static com.paraparp.realestatestats.model.mapper.RealEstateDataMapper.*;
import static com.paraparp.realestatestats.utils.Constants.CSV_FILE_NAME;
import static com.paraparp.realestatestats.utils.Constants.XLSX_FILE_NAME;

@Controller()
@Slf4j
@RequiredArgsConstructor
public class ViewStatsController {

    private final IdealistaService idealistaService;
    private final CSVDataWriter csvDataWriter;
    private final CSVDataReader csvDataReader;
    private final XLSXDataWriter xlsxDataWriter;
    private final DataService dataService;

    @GetMapping("/statistics/today")
    public String getTodayStatistics(Model model) {

        List<Map<String, Object>> statistics = idealistaService.getStatistics();

        List<String[]> dataArray = mapToArrayList(statistics);
        csvDataWriter.execute(dataArray, CSV_FILE_NAME);

        List<RealEstateData> realEstateDataList = mapToRealEstateDataList(statistics);
        xlsxDataWriter.execute(realEstateDataList, XLSX_FILE_NAME);

        dataService.save(realEstateDataList);

        model.addAttribute("data", realEstateDataList);
        return "statistics";
    }

    @GetMapping("/statistics/all")
    public String getAllStatistics(Model model) {
        List<Map<String, String>> fromCSV = csvDataReader.execute(CSV_FILE_NAME);
        List<RealEstateData> realEstateData = mapToRealEstateDataListFromStrings(fromCSV);
        model.addAttribute("data", realEstateData);

        return "statistics";
    }
}
