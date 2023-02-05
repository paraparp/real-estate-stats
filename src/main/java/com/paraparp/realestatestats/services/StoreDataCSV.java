package com.paraparp.realestatestats.services;

import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.paraparp.realestatestats.utils.Utils.convertToInt;

@Service
public class StoreDataCSV {

    private static final String CSV_FILE_NAME = "data.csv";
    private final Set<String> writtenLines = new HashSet<>();

    public void storeInCSV(List<String[]> data) {
        File csvOutputFile = new File(CSV_FILE_NAME);
        try (Scanner scanner = new Scanner(csvOutputFile)) {
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(",");
                writtenLines.add(line[0] + "," + line[1]);
            }
        } catch (FileNotFoundException e) {
            // Ignore, this just means the file doesn't exist yet
        }

        try (PrintWriter pw = new PrintWriter(new FileOutputStream(csvOutputFile, true))) {
            data.stream()
                    .filter(x -> writtenLines.add(x[0] + "," + x[1]))
                    .map(this::convertToCSV)
                    .forEach(pw::println);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<RealEstateData> getFromCSV() {
        List<RealEstateData> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE_NAME))) {
            String line;
            int lineCount = 0;
            while ((line = br.readLine()) != null) {
                lineCount++;
                if (lineCount == 1) {
                    continue;
                }
                String[] values = line.split(",");
                data.add(new RealEstateData(
                        LocalDate.parse(values[0]),
                        values[1],
                        convertToInt(values[2]),
                        convertToInt(values[3]),
                        convertToInt(values[4]),
                        convertToInt(values[5]),
                        convertToInt(values[6])
                ));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        data.remove(0);
        return data;
    }

    private String convertToCSV(String[] data) {
        return Stream.of(data)
                .map(this::escapeSpecialCharacters)
                .collect(Collectors.joining(","));
    }

    private String escapeSpecialCharacters(String data) {
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }
}
