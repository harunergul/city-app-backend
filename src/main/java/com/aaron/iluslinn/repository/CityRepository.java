package com.aaron.iluslinn.repository;

import com.aaron.iluslinn.model.City;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long>, CityCustomRepository {


}
