package com.paraparp.realestatestats.repository.jdbc;

import com.paraparp.realestatestats.model.entities.RealEstateData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRepository extends CrudRepository<RealEstateData, String> {
}