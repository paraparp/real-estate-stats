package com.paraparp.realestatestats.repository.jdbc;

import com.paraparp.realestatestats.model.entities.RealEstateData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DataService {

    public final DataRepository dataRepository;

    public void save(List<RealEstateData> data){
        dataRepository.saveAll(data);
    }
}
