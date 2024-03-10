package com.samangkar.Samangkar.repository;

import com.samangkar.Samangkar.model.Shop;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//@RepositoryRestResource(collectionResourceRel = "shops", path = "shops")
@Repository
public interface ShopRepository extends PagingAndSortingRepository<Shop, Long>,
        CrudRepository<Shop, Long> {
    List<Shop> findByName(@Param("shopName") String shopName);

    Shop findFirstByName(@Param("shopName") String shopName);

    // @Query("SELECT new com.samangkar.Samangkar.dto.AllShopDto(s.id, s.name, s.description, s.shopImageUrl, s.isActive, s.isTrending) FROM Shop s WHERE s.isActive = true")
    // List<AllShopDto> findByActivatedTrue(); //get active shop

    // @Query("SELECT new com.samangkar.Samangkar.dto.AllShopDto(s.id, s.name, s.description, s.shopImageUrl, s.isActive, s.isTrending) FROM Shop s WHERE s.isTrending = true")
    // List<AllShopDto> findByTrendingTrue(); //get Trending shop

    List<Shop> findAllByIsTrendingIsTrue();

    boolean existsByOwner_Id(Long ownerId);

    List<Shop> findAllByDeletedAtIsNull();

    List<Shop> findAllByDeletedAtIsNotNull();

    List<Shop> findAllByOwnerIdAndDeletedAtIsNull(Long ownerId);

    //FIND SHOP BY NAME AND DESCRIPTION
    @Query("SELECT s FROM Shop s WHERE s.name LIKE %:keyword% OR s.description LIKE %:keyword%")
    List<Shop> findByKeyword(@Param("keyword") String keyword);

//    @Query("SELECT new com.samangkar.Samangkar.dto.AllShopDto(s.id, s.name, s.description, s.shopImageUrl, s.activated, s.trending) FROM Shop s " +
//            "WHERE (s.name LIKE CONCAT('%', :keyword, '%') OR s.description LIKE CONCAT('%', :keyword, '%')) " +
//            "AND s.activated = false AND s.trending = false")
//    List<AllShopDto> findByNameAndDescription(@Param("keyword") String keyword );

    Shop findFirstById(Long Id);
    
    @Query("SELECT s FROM Shop s WHERE " +
        "LOWER(s.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
        "LOWER(s.description) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
        "LOWER(s.owner.username) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Shop> findShopsByKeyword(String keyword, Pageable pageable);

    @SuppressWarnings("null")
    Page<Shop> findAll(Pageable pageable);

    @SuppressWarnings("null")
    List<Shop> findAll();
    
}
