package com.aaron.iluslinn.repository;

import com.aaron.iluslinn.model.PartialUser;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PartialUserRepository extends CrudRepository<PartialUser, Long>, JpaSpecificationExecutor<PartialUser>{

	@Query(value = "SELECT * FROM user where username = ?1 AND is_Exist =TRUE ", nativeQuery = true)
	Optional<PartialUser> findUserByUsername(String username);

}
