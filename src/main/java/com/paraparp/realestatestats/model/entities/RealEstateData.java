package com.paraparp.realestatestats.model.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Comparator;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RealEstateData  implements Comparator<RealEstateData> {

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate date;
    private String location;
    private int amount;
    private int type0;
    private int percentil13;
    private int percentil33;
    private int priceM2;


    public String id()  {
        return date + location;
    }

    @Override
        public int compare(RealEstateData prod1, RealEstateData prod2) {
            return prod1.getDate().compareTo(prod2.getDate());
        }

}