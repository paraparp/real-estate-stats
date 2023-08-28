package com.paraparp.realestatestats.model.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Comparator;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "RealEstateData")
public class RealEstateData implements Comparator<RealEstateData>, Serializable {

    @Id
    private String id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private String location;
    private int amount;
    private int medianPrice;
    private int firstQuartilePrice;
    private int thirdQuartilePrice;
    private int priceM2;

    public RealEstateData(LocalDate date, String location, int amount, int medianPrice, int firstQuartilePrice, int thirdQuartilePrice, int priceM2) {
        this.date = date;
        this.location = location;
        this.amount = amount;
        this.medianPrice = medianPrice;
        this.firstQuartilePrice = firstQuartilePrice;
        this.thirdQuartilePrice = thirdQuartilePrice;
        this.priceM2 = priceM2;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = date + location;
    }

    public String id()  {
        return date + location;
    }
    @Override
        public int compare (RealEstateData prod1, RealEstateData prod2){
            return prod1.getDate().compareTo(prod2.getDate());
        }

    }