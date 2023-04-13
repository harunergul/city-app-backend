package com.aaron.iluslinn.controller;

import com.aaron.iluslinn.config.RoleAllowEditOnly;
import com.aaron.iluslinn.dto.CityDTO;
import com.aaron.iluslinn.dto.CityListRsp;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.constraints.Min;

public interface CityApi {

    @GetMapping
    ResponseEntity<CityListRsp> getAllCities(@RequestParam(required = false, defaultValue = "1" )@Min(1) Integer page,
        @RequestParam(required = false, defaultValue = "5") @Min(1) Integer pageSize,
        @RequestParam(required = false, defaultValue = "") String name);

    @RoleAllowEditOnly
    @PutMapping(value = "/edit")
    ResponseEntity<CityDTO> updateCity(@RequestBody CityDTO cityDTO);

}
