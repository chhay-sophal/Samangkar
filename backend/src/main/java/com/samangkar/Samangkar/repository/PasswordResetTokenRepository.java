package com.samangkar.Samangkar.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.samangkar.Samangkar.model.PasswordResetToken;

@RepositoryRestResource(collectionResourceRel = "password-reset", path = "password-reset")
@Repository
public interface PasswordResetTokenRepository extends PagingAndSortingRepository<PasswordResetToken, Long>,
CrudRepository<PasswordResetToken, Long> {

    PasswordResetToken findByToken(String token);
    
}