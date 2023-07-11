package com.aaron.iluslinn.service;

import com.aaron.iluslinn.model.City;
import com.aaron.iluslinn.repository.CityRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

class CityServiceTest {

    private CityService underTest;

    private AutoCloseable autoClosable;

    @Mock
    private CityRepository cityRepository;


    @BeforeEach
    void setUp() {
        autoClosable = MockitoAnnotations.openMocks(this);
        underTest = new CityService(cityRepository);
    }

    @AfterEach
    void tearDown() {
    }

    public Page<City> getCities(Pageable pageable, String name){
        Specification<City> spec = Specification.where(CityService.nameContains(name));

        return cityRepository.findAll(spec, pageable);
    }
}