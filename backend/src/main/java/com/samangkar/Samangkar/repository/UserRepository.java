package com.samangkar.Samangkar.repository;

import com.samangkar.Samangkar.model.Role;
import com.samangkar.Samangkar.model.UserEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long>,
        CrudRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(@Param("username") String username);

    Optional<UserEntity> findByEmail(@Param("email") String email);

    UserEntity findFirstByUsername(@Param("username") String username);

    UserEntity findFirstById(Long id);

    Boolean existsByUsername(String username);

    Optional<UserEntity> findByIdAndUserRole_Id(Long id, Long roleId);
    Boolean existsByEmail(String email);

    List<UserEntity> findByUserRole(Role userRole);

    @Query("SELECT u FROM UserEntity u WHERE " +
        "LOWER(u.username) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
        "LOWER(u.email) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
        // "u.deletedAt IS NULL OR " +
        "LOWER(u.userRole.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<UserEntity> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);

    @SuppressWarnings("null")
    Page<UserEntity> findAll(Pageable pageable);

    @SuppressWarnings("null")
    List<UserEntity> findAll();

}


