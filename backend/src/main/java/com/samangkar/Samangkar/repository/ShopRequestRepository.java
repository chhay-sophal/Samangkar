package com.samangkar.Samangkar.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.samangkar.Samangkar.model.ShopRequest;

@RepositoryRestResource(collectionResourceRel = "requests", path = "requests")
@Repository
public interface ShopRequestRepository extends PagingAndSortingRepository<ShopRequest, Long>,
CrudRepository<ShopRequest, Long> {

    // @SuppressWarnings("null")
    // Page<ShopRequest> findAll(Pageable pageable);

    @SuppressWarnings("null")
    List<ShopRequest> findAll();
}
