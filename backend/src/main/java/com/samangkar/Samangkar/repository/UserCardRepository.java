package com.samangkar.Samangkar.repository;

import com.samangkar.Samangkar.model.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "cards", path = "cards")
public interface UserCardRepository extends PagingAndSortingRepository<UserCard, Long>,
        CrudRepository<UserCard, Long> {

    List<UserCard> findByUser(@Param("user") UserEntity user);

    // List<UserCard> findByShop(@Param("shop") Shop shop);

    List<UserCard> findByService(@Param("service") ServiceModel service);

    // List<UserCard> findByUserAndShopAndService(@Param("user") UserEntity user, @Param("shop") Shop shop, @Param("service") ServiceModel service);
    // List<UserCard> findByUserAndService(@Param("user") UserEntity user, @Param("service") ServiceModel service);

    List<UserCard> findByUser_UsernameIgnoreCase(String username);

    List<UserCard> findByUser_Id(Long id);

    List<UserCard> findByDeletedAtIsNullAndUser_Id(Long id);

    Optional<UserCard> findByUserAndService(UserEntity user, ServiceModel service);
    Optional<UserCard> findByUserAndPkg(UserEntity user, PackageModel pkg);

}
