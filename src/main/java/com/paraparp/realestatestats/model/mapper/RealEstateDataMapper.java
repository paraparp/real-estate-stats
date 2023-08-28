package com.paraparp.realestatestats.model.mapper;

import com.paraparp.realestatestats.model.entities.RealEstateData;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RealEstateDataMapper {

    public static RealEstateData mapToRealEstateData(Map<String, Object> dataMap) {
        return mapToRealEstateDataInternal(dataMap);
    }

    public static RealEstateData mapToRealEstateDataFromStrings(Map<String, String> dataMap) {
        return mapToRealEstateDataInternal(dataMap);
    }

    public static List<String[]> mapToArrayList(List<Map<String, Object>> dataMaps) {
        List<String[]> arrayList = new ArrayList<>();

        for (Map<String, Object> dataMap : dataMaps) {
            arrayList.add(mapToArray(dataMap));
        }

        return arrayList;
    }

    private static String[] mapToArray(Map<String, Object> dataMap) {
        String[] array = new String[7];

        array[0] = String.valueOf(dataMap.get("Date"));
        array[1] = (String) dataMap.get("Location");
        array[2] = String.valueOf(dataMap.get("Amount"));
        array[3] = String.valueOf(dataMap.get("Total"));
        array[4] = String.valueOf(dataMap.get("Percent1/3"));
        array[5] = String.valueOf(dataMap.get("Percent3/3"));
        array[6] = String.valueOf(dataMap.get("m2"));

        return array;
    }


    private static RealEstateData mapToRealEstateDataInternal(Map<String, ?> dataMap) {
        LocalDate date = parseDate(dataMap);

        RealEstateData realEstateData = new RealEstateData(
                date,
                (String) dataMap.get("Location"),
                parseToInt(dataMap.get("Amount")),
                parseToInt(dataMap.get("Total")),
                parseToInt(dataMap.get("Percent1/3")),
                parseToInt(dataMap.get("Percent3/3")),
                parseToInt(dataMap.get("m2"))
        );

        realEstateData.setId(realEstateData.id());
        return realEstateData;
    }

    private static LocalDate parseDate(Map<String, ?> dataMap) {
        Object dateObj = dataMap.get("Date");
        LocalDate date;

        if (dateObj instanceof LocalDate) {
            date = (LocalDate) dateObj;
        } else if (dateObj instanceof String) {
            date = LocalDate.parse((String) dateObj);
        } else {
            throw new IllegalArgumentException("Unexpected type for Date: " + dateObj.getClass());
        }
        return date;
    }

    public static List<RealEstateData> mapToRealEstateDataList(List<Map<String, Object>> dataMaps) {
        List<RealEstateData> realEstateDataList = new ArrayList<>();

        for (Map<String, Object> dataMap : dataMaps) {
            realEstateDataList.add(mapToRealEstateData(dataMap));
        }

        return realEstateDataList;
    }

    public static List<RealEstateData> mapToRealEstateDataListFromStrings(List<Map<String, String>> dataMaps) {
        List<RealEstateData> realEstateDataList = new ArrayList<>();

        for (Map<String, String> dataMap : dataMaps) {
            realEstateDataList.add(mapToRealEstateDataFromStrings(dataMap));
        }

        return realEstateDataList;
    }

    private static int parseToInt(Object obj) {
        if (obj == null) throw new IllegalArgumentException("Object is null");

        if (obj instanceof String) {
            return Integer.parseInt((String) obj);
        } else if (obj instanceof Integer) {
            return (Integer) obj;
        }

        throw new IllegalArgumentException("Unexpected type: " + obj.getClass());
    }
}
