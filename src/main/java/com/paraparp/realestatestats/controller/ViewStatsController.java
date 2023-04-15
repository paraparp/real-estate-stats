package com.paraparp.realestatestats.controller;


import com.paraparp.realestatestats.repository.csv.GetDataCSV;
import com.paraparp.realestatestats.repository.jdbc.DataService;
import com.paraparp.realestatestats.services.IdealistaService;
import com.paraparp.realestatestats.model.entities.RealEstateData;
import com.paraparp.realestatestats.repository.csv.StoreDataCSV;
import com.paraparp.realestatestats.repository.xlsx.StoreDataXLSX;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ViewStatsController {

    private final IdealistaService idealistaService;
    private final StoreDataCSV storeDataCSV;
    private final GetDataCSV getDataCSV;
    private final StoreDataXLSX storeDataExcell;
    private final DataService dataService;

    @GetMapping("/datos")
    public String getStatsFromIdealista(Model model) {

        List<Map<String, Object>> statistics = idealistaService.getStatistics();
        storeDataCSV.insert(idealistaService.getStatisticsAsArray());
        List<RealEstateData> statisticsAsObject = idealistaService.getStatisticsAsObject();
        storeDataExcell.insert(statisticsAsObject);
        model.addAttribute("datos", statistics);
        dataService.save(statisticsAsObject);
        return "datos";
    }

    @GetMapping("/datos2")
    public String getStatsFromIdealista2(Model model) {
        List<RealEstateData> fromCSV = getDataCSV.get();
        fromCSV.sort(new RealEstateData());
        model.addAttribute("datos", fromCSV);
        return "datos2";
    }
}
