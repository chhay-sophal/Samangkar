package com.samangkar.Samangkar.repository;

import com.samangkar.Samangkar.model.Shop;
import com.samangkar.Samangkar.model.UserEntity;
import com.samangkar.Samangkar.model.UserReview;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "reviews", path = "reviews")
public interface UserReviewRepository extends PagingAndSortingRepository<UserReview, Long>,
        CrudRepository<UserReview, Long> {

    List<UserReview> findByUser(@Param("user") UserEntity user);

    List<UserReview> findByShop(@Param("shop") Shop shop);

    List<UserReview> findByUserAndShop(@Param("user") UserEntity user, @Param("shop") Shop shop);
}
