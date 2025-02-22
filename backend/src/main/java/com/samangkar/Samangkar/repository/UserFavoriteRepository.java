package com.samangkar.Samangkar.repository;

import com.samangkar.Samangkar.model.Shop;
import com.samangkar.Samangkar.model.UserEntity;
import com.samangkar.Samangkar.model.UserFavorite;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "favorites", path = "favorites")
public interface UserFavoriteRepository extends PagingAndSortingRepository<UserFavorite, Long>,
        CrudRepository<UserFavorite, Long> {

    List<UserFavorite> findByUser(@Param("user") UserEntity user);

    List<UserFavorite> findByUserAndDeletedAtIsNull(UserEntity user);

    List<UserFavorite> findByShop(@Param("shop") Shop shop);

    List<UserFavorite> findByUserAndShop(@Param("user") UserEntity user, @Param("shop") Shop shop);

    List<UserFavorite> findByUser_Username(@Param("username") String username);

}
