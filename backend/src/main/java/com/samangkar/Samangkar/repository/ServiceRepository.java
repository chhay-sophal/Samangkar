package com.samangkar.Samangkar.repository;

import com.samangkar.Samangkar.model.ServiceModel;
import com.samangkar.Samangkar.model.Shop;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "services", path = "services")
public interface ServiceRepository extends PagingAndSortingRepository<ServiceModel, Long>,
        CrudRepository<ServiceModel, Long> {

    List<ServiceModel> findByName(@Param("name") String name);

    List<ServiceModel> findByShop(@Param("shop") Shop shop);

    List<ServiceModel> findByNameAndShop(@Param("name") String name, @Param("shop") Shop shop);

    ServiceModel findFirstByNameAndShop(@Param("name") String name, @Param("shop") Shop shop);

    ServiceModel findFirstById(Long id);
    
    @Query("SELECT service FROM ServiceModel service WHERE " +
        "LOWER(service.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
        "LOWER(service.description) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
        "LOWER(service.shop.name) LIKE LOWER(CONCAT('%', :keyword, '%')) AND " + 
        "service.deletedAt IS NULL")
    Page<ServiceModel> searchPackagesByKeyword(@Param("keyword") String keyword, Pageable pageable);

    List<ServiceModel> findByShopAndDeletedAtIsNull(@Param("shop") Shop shop);

    List<ServiceModel> findByPackages_Id(Long id);
}