package com.aaron.iluslinn.service;

import com.aaron.iluslinn.model.PartialUser;
import com.aaron.iluslinn.model.User;
import com.aaron.iluslinn.repository.PartialUserRepository;
import com.aaron.iluslinn.repository.UserRepository;
import com.aaron.iluslinn.util.UserNotFoundException;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepo;
    private final PartialUserRepository partialUserRepo;

    public User getUserById(Long id) {
        return userRepo.findUserById(id)
            .orElseThrow(() -> new UserNotFoundException("User by id" + id + " was not found"));
    }

    public PartialUser getUserByUsername(String username) {
        return partialUserRepo.findUserByUsername(username)
            .orElseThrow(() -> new UserNotFoundException("User by username: " + username + " was not found"));
    }

    public PartialUser getPartialUser(Long id) {
        return partialUserRepo.findById(id).get();

    }


}
