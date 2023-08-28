package com.paraparp.realestatestats.services;

import com.paraparp.realestatestats.model.idealista.DataDTO;
import com.paraparp.realestatestats.model.idealista.ItemDTO;
import com.paraparp.realestatestats.model.idealista.MainObjectIdealistaDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static com.paraparp.realestatestats.utils.Utils.convertToInt;

@Service
@RequiredArgsConstructor
@Slf4j
public class IdealistaService {
    private final IdealistaExtractorService extractorService;

    public List<Map<String, Object>> getStatistics() {
        log.info("Generating statistics...");

        return Arrays.stream(IdealistaLocation.values())
                .map(IdealistaLocation::buildUrl)
                .map(extractorService::getIdealistaStats)
                .map(this::getSimplifyStats)
                .collect(Collectors.toList());
    }


    private Map<String, Object> getSimplifyStats(MainObjectIdealistaDTO body) {

        DataDTO data = body.getData();
        List<ItemDTO> items = data.map.items;

        List<Integer> list0 = items.stream().map(item -> item.ads).flatMap(Collection::stream).map(ad -> (int) ad.price).sorted().collect(Collectors.toList());
        int st0 = getMedian(list0);
        int st1_3 = getMedian(list0.subList(0, list0.size() / 3));
        int st3_3 = getMedian(list0.subList(2 * list0.size() / 3, list0.size()));


        Map<String, Object> response = new LinkedHashMap<>();
        response.put("Date", LocalDate.now());
        response.put("Location", getLocation(data));
        response.put("Amount", getAmount(data));
        response.put("Total", st0);
        response.put("Percent1/3", st1_3);
        response.put("Percent3/3", st3_3);
        response.put("m2", getListingPriceByArea(data));
        return response;
    }

    private static Integer getListingPriceByArea(DataDTO data) {
        return convertToInt(data.listingPriceByArea.split(" ")[2]);
    }

    private String getLocation(DataDTO data) {

        String location = data.valueH1.replace(",", "-").split(" en ")[1].trim();

        LinkedList<String> collect = Arrays.stream(location.split("-")).map(String::trim).collect(Collectors.toCollection(LinkedList::new));
        String first = collect.removeLast();
        collect.addFirst(first);
        return String.join(" - ", collect);
    }

    private Integer getAmount(DataDTO data) {
        return convertToInt(data.valueH1.split(" ")[0].trim());
    }


    private int getMedian(List<Integer> list) {
        List<Integer> sortedList = new ArrayList<>(list);
        Collections.sort(sortedList);

        int n = sortedList.size();
        if (n % 2 == 0) {
            return (sortedList.get(n / 2 - 1) + sortedList.get(n / 2)) / 2;
        } else {
            return sortedList.get(n / 2);
        }
    }
}
