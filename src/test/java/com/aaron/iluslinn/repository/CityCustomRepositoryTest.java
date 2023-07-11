package com.aaron.iluslinn.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.aaron.iluslinn.model.City;
import com.aaron.iluslinn.service.CityService;
import com.aaron.iluslinn.util.ResourceUtil;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import lombok.RequiredArgsConstructor;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@RequiredArgsConstructor
class CityCustomRepositoryTest {


    @Qualifier("cityCustomRepositoryImpl")
    @Autowired
    private CityCustomRepository cityCustomRepository;


    @Autowired
    private CityRepository cityRepository;

    private final String EMPTY_SEARCH_TERM = "";


    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    public void loadDataToDB(int numberOfCityToBeSaved) {
        City[] data = ResourceUtil.getDataFromResourceFile("/data/city-mock-data.json", City[].class);
        List<City> cityList = new ArrayList<City>();

        int count = 0;
        for (City city : data) {
            if (numberOfCityToBeSaved == count) {
                break;
            }
            cityList.add(city);
            count++;
        }

        cityRepository.saveAllAndFlush(cityList);
    }

    @DisplayName("CityCustomRepository should return data if DB is not empty ")
    @Test
    public void CityCustomRepository_whenValidPageRequestAndEmptySearchTerm_returnCityList() {
        loadDataToDB(1000);

        // given
        int requestPage = 0;
        int requestedSize = 10;
        PageRequest pageable = PageRequest.of(requestPage, requestedSize);
        Specification<City> spec = Specification.where(CityService.nameContains(EMPTY_SEARCH_TERM));

        // when
        Page<City> cityPage = cityCustomRepository.findAll(spec, pageable);

        // then
        int expectedPageSize = requestedSize;
        int expectedTotalPagesCount = 100;
        int expectedTotalElementCount = 1000;

        assertThat(cityPage).isNotNull();
        assertThat(cityPage.getTotalPages()).isEqualTo(expectedTotalPagesCount);
        assertThat(cityPage.getTotalElements()).isEqualTo(expectedTotalElementCount);
        assertThat(cityPage.getSize()).isEqualTo(expectedPageSize);

    }


    @DisplayName("CityCustomRepository should return data if null search term provided ")
    @Test
    public void CityCustomRepository_whenValidPageRequestAndNullSearchTerm_returnCityList() {
        loadDataToDB(35);

        // given
        int requestPage = 0;
        int requestedSize = 10;
        PageRequest pageable = PageRequest.of(requestPage, requestedSize);
        Specification<City> spec = Specification.where(CityService.nameContains(null));

        // when
        Page<City> cityPage = cityCustomRepository.findAll(spec, pageable);

        // then
        int expectedPageSize = requestedSize;
        int expectedTotalPagesCount = 4;
        int expectedTotalElementCount = 35;

        assertThat(cityPage).isNotNull();
        assertThat(cityPage.getTotalPages()).isEqualTo(expectedTotalPagesCount);
        assertThat(cityPage.getTotalElements()).isEqualTo(expectedTotalElementCount);
        assertThat(cityPage.getSize()).isEqualTo(expectedPageSize);

    }
}