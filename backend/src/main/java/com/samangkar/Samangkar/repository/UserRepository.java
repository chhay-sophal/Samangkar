package com.samangkar.Samangkar.repository;

import com.samangkar.Samangkar.model.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long>,
        CrudRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(@Param("username") String username);

    Optional<UserEntity> findByEmail(@Param("email") String email);

    UserEntity findFirstByUsername(@Param("username") String username);

    UserEntity findFirstById(Long id);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

}
