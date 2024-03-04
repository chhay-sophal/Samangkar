package com.samangkar.Samangkar.repository;

import com.samangkar.Samangkar.dto.AllShopDto;
import com.samangkar.Samangkar.model.Shop;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//@RepositoryRestResource(collectionResourceRel = "shops", path = "shops")
@Repository
public interface ShopRepository extends PagingAndSortingRepository<Shop, Long>,
        CrudRepository<Shop, Long> {
    List<Shop> findByName(@Param("shopName") String shopName);

    Shop findFirstByName(@Param("shopName") String shopName);

    @Query("SELECT new com.samangkar.Samangkar.dto.AllShopDto(s.id, s.name, s.description, s.shopImageUrl, s.activated, s.trending) FROM Shop s WHERE s.activated = true")
    List<AllShopDto> findByActivatedTrue(); //get active shop

    @Query("SELECT new com.samangkar.Samangkar.dto.AllShopDto(s.id, s.name, s.description, s.shopImageUrl, s.activated, s.trending) FROM Shop s WHERE s.trending = true")
    List<AllShopDto> findByTrendingTrue(); //get Trending shop

    boolean existsByOwner_Id(Long ownerId);

    Optional<Shop> findByOwnerId(Long ownerId);
}
