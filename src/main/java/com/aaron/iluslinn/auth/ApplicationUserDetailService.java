package com.aaron.iluslinn.auth;

import com.aaron.iluslinn.model.User;
import com.aaron.iluslinn.repository.UserRepository;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApplicationUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> userList = userRepository.findByUsername(username);
        if (userList != null && userList.size() > 0) {
            return userList.get(0);
        } else {
            throw new UsernameNotFoundException("Username not found!");
        }

    }

}
