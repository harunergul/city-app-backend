package com.aaron.iluslinn.controller;

import com.aaron.iluslinn.auth.AuthenticationRequest;
import com.aaron.iluslinn.auth.AuthenticationResponse;
import com.aaron.iluslinn.config.RoleAllowEditOnly;
import com.aaron.iluslinn.dto.CityDTO;
import com.aaron.iluslinn.dto.CityListRsp;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.constraints.Min;

public interface AuthenticationApi {

    @PostMapping(value = "/login")
    ResponseEntity<AuthenticationResponse> authenticateUser(@RequestBody AuthenticationRequest authenticationRequest)
        throws Exception;


}
