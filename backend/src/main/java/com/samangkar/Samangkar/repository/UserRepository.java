package com.samangkar.Samangkar.repository;

import com.samangkar.Samangkar.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends PagingAndSortingRepository<User, Long>,
        CrudRepository<User, Long> {

    List<User> findByUsername(@Param("username") String username);

    List<User> findByEmail(@Param("email") String email);

    User findFirstByUsername(@Param("username") String username);

}
