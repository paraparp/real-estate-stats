package com.paraparp.realestatestats.repository.csv;

import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class CSVDataWriter {

    private final Set<String> existingIds = new HashSet<>();

    public void execute(List<String[]> data, String csvFileName) {

        File csvOutputFile = new File(csvFileName);
        if (!csvFileName.endsWith(".csv")) {
            throw new UnsupportedOperationException("The file is not an XLSX file");
        }

        getExistingIds(csvOutputFile);

        try (PrintWriter pw = new PrintWriter(new FileOutputStream(csvOutputFile, true))) {
            data.stream()
                    .filter(x -> !existingIds.contains(x[0] + x[1]))
                    .map(this::convertToCSV)
                    .forEach(pw::println);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void getExistingIds(File csvOutputFile) {
        try (Scanner scanner = new Scanner(csvOutputFile)) {
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(",");
                existingIds.add(line[0] + line[1]);
            }
        } catch (FileNotFoundException e) {
            // Ignore, this just means the file doesn't exist yet
        }
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
