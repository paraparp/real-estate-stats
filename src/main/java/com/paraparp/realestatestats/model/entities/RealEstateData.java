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
    private int type0;
    private int percentil13;
    private int percentil33;
    private int priceM2;

    public RealEstateData(LocalDate date, String location, int amount, int type0, int percentil13, int percentil33, int priceM2) {
        this.date = date;
        this.location = location;
        this.amount = amount;
        this.type0 = type0;
        this.percentil13 = percentil13;
        this.percentil33 = percentil33;
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