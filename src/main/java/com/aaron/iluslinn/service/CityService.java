package com.aaron.iluslinn.service;

import com.aaron.iluslinn.exception.ApiRequestException;
import com.aaron.iluslinn.exception.enums.ApiExceptionCity;
import com.aaron.iluslinn.model.City;
import com.aaron.iluslinn.repository.CityRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service //Business logic
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;

    public Page<City> getCities(Pageable pageable, String name) {
        Specification<City> spec = Specification.where(CityService.nameContains(name));

        return cityRepository.findAll(spec, pageable);
    }

    public static Specification<City> nameContains(String searchTerm) {
        return (root, query, builder) -> {

            if (searchTerm == null) {

                return null;// builder.like(builder.lower(root.get("name")), "% %")
            }

            return builder.like(builder.lower(root.get("name")), "%" + searchTerm + "%");
        };
    }

    public City updateCity(City city) {
        if (city.getId() == null) {
            throw new ApiRequestException(ApiExceptionCity.INVALID_FIELDS);
        }
        return cityRepository.save(city);
    }
}
