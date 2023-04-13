package com.aaron.iluslinn.dto;

import com.aaron.iluslinn.model.City;

import java.util.function.Function;

import org.springframework.stereotype.Component;

@Component
public class CityDTOMapper implements Function<City, CityDTO> {
    @Override
    public CityDTO apply(City city) {
        return new CityDTO(city.getId(), city.getName(), city.getPhotoUrl());
    }
}
