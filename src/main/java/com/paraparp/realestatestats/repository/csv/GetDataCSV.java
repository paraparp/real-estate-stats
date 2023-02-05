package com.paraparp.realestatestats.repository.csv;

import com.paraparp.realestatestats.model.entities.RealEstateData;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.paraparp.realestatestats.utils.Constants.CSV_FILE_NAME;
import static com.paraparp.realestatestats.utils.Utils.convertToInt;

@Repository
public class GetDataCSV{

        public List<RealEstateData> get() {
            List<RealEstateData> data = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE_NAME))) {
                String line;
                int lineCount = 0;
                while ((line = br.readLine()) != null) {
                    lineCount++;
                    if (lineCount == 1) { //RemoveHeader
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

            return data;
        }
}
