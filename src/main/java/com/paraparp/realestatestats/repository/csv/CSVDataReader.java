package com.paraparp.realestatestats.repository.csv;

import com.paraparp.realestatestats.errors.FileProcessingException;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CSVDataReader {

    public List<Map<String, String>> execute(String fileName) {
        List<Map<String, String>> data = new ArrayList<>();
        List<String> headers = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int lineCount = 0;

            while ((line = br.readLine()) != null) {
                lineCount++;

                String[] values = line.split(",");

                if (lineCount == 1) {
                    // This is the header row, store the headers
                    for (String header : values) {
                        headers.add(header.trim());
                    }
                } else {
                    // This is a data row, map the values to the headers
                    Map<String, String> rowMap = new HashMap<>();
                    for (int i = 0; i < headers.size() && i < values.length; i++) {
                        rowMap.put(headers.get(i), values[i].trim());
                    }
                    data.add(rowMap);
                }
            }

        } catch (FileNotFoundException e) {
            throw new FileProcessingException("File not found: " + fileName, "The specified file could not be located.");
        } catch (IOException e) {
            throw new FileProcessingException("Error reading from file: " + fileName, "There was an unexpected error while reading from the file.");
        }

        return data;
    }
}
