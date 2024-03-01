package com.samangkar.Samangkar.repository;

import com.samangkar.Samangkar.model.Shop;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "shops", path = "shops")
public interface ShopRepository extends PagingAndSortingRepository<Shop, Long>,
        CrudRepository<Shop, Long> {

    List<Shop> findByName(@Param("shopName") String shopName);

    Shop findFirstByName(@Param("shopName") String shopName);


}
