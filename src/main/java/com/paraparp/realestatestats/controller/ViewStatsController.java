package com.paraparp.realestatestats.controller;


import com.paraparp.realestatestats.services.IdealistaService;
import com.paraparp.realestatestats.services.RealEstateData;
import com.paraparp.realestatestats.services.StoreDataCSV;
import com.paraparp.realestatestats.services.StoreDataExcell;
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
    private final StoreDataExcell storeDataExcell;
    @GetMapping("/datos")
    public String getStatsFromIdealista(Model model) {

        List<Map<String, Object>> statistics = idealistaService.getStatistics();
        storeDataCSV.storeInCSV (idealistaService.getStatisticsAsArray());
        storeDataExcell.storeInXls(idealistaService.getStatisticsAsObject());
        model.addAttribute("datos", statistics);
        return "datos";

    }

    @GetMapping("/datos2")
    public String getStatsFromIdealista2(Model model) {
        List<RealEstateData> fromCSV = storeDataCSV.getFromCSV();
        fromCSV.sort(new RealEstateData());
        model.addAttribute("datos", fromCSV);
        return "datos2";

    }
}
