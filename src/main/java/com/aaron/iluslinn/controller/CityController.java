package com.aaron.iluslinn.controller;

import com.aaron.iluslinn.dto.CityDTO;
import com.aaron.iluslinn.dto.CityDTOMapper;
import com.aaron.iluslinn.dto.CityListRsp;
import com.aaron.iluslinn.dto.PageVo;
import com.aaron.iluslinn.model.City;
import com.aaron.iluslinn.service.CityService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cities")
@RequiredArgsConstructor
public class CityController implements CityApi {

    private final CityService cityService;
    private final CityDTOMapper cityDTOMapper;


    public ResponseEntity<CityListRsp> getAllCities(Integer page, Integer pageSize, String name) {
        page = page - 1;
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<City> pagedResult = this.cityService.getCities(pageable, name);
        List result = pagedResult.getContent().stream().map(cityDTOMapper).collect(Collectors.toList());
        int totalPages = pagedResult.getTotalPages();
        long totalElements = pagedResult.getTotalElements();
        PageVo pageVo = new PageVo(totalElements, totalPages, page + 1);
        CityListRsp response = CityListRsp.builder().data(result).pageInfo(pageVo).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CityDTO> updateCity(CityDTO cityDTO) {
        City city = City.builder().id(cityDTO.id()).name(cityDTO.name()).photoUrl(cityDTO.photoUrl()).build();
        city = cityService.updateCity(city);
        return new ResponseEntity<>(cityDTOMapper.apply(city), HttpStatus.OK);

    }
}
