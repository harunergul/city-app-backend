package com.aaron.iluslinn.controller;

import com.aaron.iluslinn.auth.AuthenticationRequest;
import com.aaron.iluslinn.auth.AuthenticationResponse;
import com.aaron.iluslinn.auth.JwtUtil;
import com.aaron.iluslinn.model.PartialUser;
import com.aaron.iluslinn.model.User;
import com.aaron.iluslinn.repository.PartialUserRepository;
import com.aaron.iluslinn.service.UserService;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController implements AuthenticationApi {

    private final AuthenticationManager authenticationManager;

    private final UserService userService;
    private final PartialUserRepository partialUserRepo;

    private final JwtUtil jwtUtil;


    @Override
    public ResponseEntity<AuthenticationResponse> authenticateUser(AuthenticationRequest authenticationRequest) {

        try {
            Authentication result = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.username(),
                    authenticationRequest.password()));
            System.out.println(result);
        } catch (BadCredentialsException e) {
            throw new UsernameNotFoundException("Incorrect username or password", e);
        }

        Optional<PartialUser> partialUser = this.partialUserRepo.findUserByUsername(authenticationRequest.username());
        if (partialUser.isPresent()) {
            PartialUser pUser = partialUser.get();
            User user = userService.getUserById(pUser.getId());
            pUser.setRoles(user.getRoles());
            final String jwt = jwtUtil.generateToken(pUser);

            return ResponseEntity.ok(new AuthenticationResponse(jwt));
        }
        throw new UsernameNotFoundException("User not found!");

    }
}
