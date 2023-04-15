package com.paraparp.realestatestats.model.entities;

import jakarta.persistence.IdClass;
import org.springframework.data.annotation.Id;

@IdClass(RealEstateData.class)
public class RealEstateDataID {

        @Id
        private int column1;
        @Id
        private int column2;


}
