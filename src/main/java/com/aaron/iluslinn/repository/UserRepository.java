package com.aaron.iluslinn.repository;

import com.aaron.iluslinn.model.User;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer>, JpaSpecificationExecutor<User> {

    List<User> findByUsername(String lastname);

    Optional<User> findUserById(Long id);
}
