package com.samangkar.Samangkar.repository;

import com.samangkar.Samangkar.model.UserType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

@RepositoryRestResource(collectionResourceRel = "user-types", path = "user-types")
public interface UserTypeRepository extends PagingAndSortingRepository<UserType, Long>,
        CrudRepository<UserType, Long> {

    List<UserType> findByName(@Param("typeName") String typeName);

    UserType findFirstByName(@Param("typeName") String typeName);

}