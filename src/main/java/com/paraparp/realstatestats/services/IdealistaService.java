package com.paraparp.realstatestats.services;

import com.paraparp.realstatestats.model.entity.Data;
import com.paraparp.realstatestats.model.entity.Item;
import com.paraparp.realstatestats.model.entity.MainObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IdealistaService {
    final String path = "https://www.idealista.com/ajax/listingcontroller/listingmapajaxgrouped.ajax?";
    final String urlRiazorRosales = path +
            "locationUri=a-coruna/riazor-los-rosales&typology=1&operation=1&zoom=5&northEast=57.84598237584738,+23.051198959350607&southWest=24.409244860154534,+-39.922433853149414";
    final String urlCiudadVieja = path +
            "locationUri=a-coruna/ciudad-vieja-centro&typology=1&operation=1&zoom=5&northEast=58.502772676210604,+13.732917785644556&southWest=23.257310155975194,+-30.52001190185546";
    final String urlTotalCoruna = path +
            "locationUri=a-coruna-a-coruna&typology=1&operation=1&zoom=5&northEast=57.84598237584738,+23.051198959350607&southWest=24.409244860154534,+-39.922433853149414";


    private final IdealistaExtractorService extractorService;

    public List<Map<String, Object>> getStatistics() {

        MainObject responseRosales = extractorService.getIdealistaStats(urlRiazorRosales);
        MainObject responseCiudadVieja = extractorService.getIdealistaStats(urlCiudadVieja);
        MainObject responseTotalCoruna = extractorService.getIdealistaStats(urlTotalCoruna);

        Map<String, Object> statsRosales = getStatsFromJson(responseRosales);
        Map<String, Object> statsCiudadVieja = getStatsFromJson(responseCiudadVieja);
        Map<String, Object> statsTotalCoruna = getStatsFromJson(responseTotalCoruna);

        return List.of(statsRosales, statsCiudadVieja, statsTotalCoruna);
    }


    private Map<String, Object> getStatsFromJson(MainObject body) {

        Data data = body.getData();
        List<Item> items = data.map.items;

        List<Integer> list1 = items.stream().map(item -> (int) item.ads.get(0).price).sorted().collect(Collectors.toList());
        List<Integer> list0 = items.stream().map(item -> item.ads).flatMap(Collection::stream).map(ad -> (int) ad.price).sorted().collect(Collectors.toList());
        Map<String, String> st1 = getStatistics( list1);
        Map<String, String> st0 = getStatistics(list0);
        Map<String, String> st1_3 = getStatistics(list0.subList(0, list0.size() / 3));
        Map<String, String> st2_3 = getStatistics(list0.subList(list0.size() / 3, 2 * list0.size() / 3));
        Map<String, String> st3_3 = getStatistics(list0.subList(2 * list0.size() / 3, list0.size()));


        Map<String, Object> response = new LinkedHashMap<>();
        response.put("Zona", data.valueH1);
        response.put("Type 1", st1);
        response.put("Type 0", st0);
        response.put("Percentil 1/3", st1_3);
        response.put("Percentil 2/3", st2_3);
        response.put("Percentil 3/3", st3_3);
        response.put("Precio m2", data.listingPriceByArea);
        response.put("Date", LocalDate.now());
        return response;
    }


    private Map<String, String> getStatistics(List<Integer> list) {
        NumberFormat nf = NumberFormat.getInstance(new Locale("es", "ES"));
        IntSummaryStatistics st = list.stream().mapToInt(Integer::intValue).summaryStatistics();

        String mediana = nf.format((int) list.get(list.size() / 2));
        String media = nf.format((int) st.getAverage());

        Map<String, String> response = new HashMap<>();
        System.out.println("Mediana = " + mediana + "€");
        System.out.println("Media = " + media + "€");

        response.put("Mediana", mediana + "€");
        response.put("Media", media + "€");
        response.put("Cantidad", String.valueOf(list.size()));

        return response;
    }

}
