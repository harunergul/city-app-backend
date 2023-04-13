package com.aaron.iluslinn.repository;

import com.aaron.iluslinn.model.City;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface CityCustomRepository {

    Page<City> findAll(Specification<City> specification, Pageable pageable);


}
