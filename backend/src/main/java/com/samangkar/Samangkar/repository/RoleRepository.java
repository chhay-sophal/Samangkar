package com.samangkar.Samangkar.repository;

import com.samangkar.Samangkar.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

@RepositoryRestResource(collectionResourceRel = "user-types", path = "user-types")
public interface RoleRepository extends PagingAndSortingRepository<Role, Long>,
        CrudRepository<Role, Long> {

    Optional<Role> findByName(@Param("role") String role);

    Role findFirstByName(@Param("role") String role);

    List<Role> findByDeletedAtIsNull();
}