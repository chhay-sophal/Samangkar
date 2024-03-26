package com.samangkar.Samangkar.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.samangkar.Samangkar.model.Request;

@RepositoryRestResource(collectionResourceRel = "requests", path = "requests")
@Repository
public interface RequestRepository extends PagingAndSortingRepository<Request, Long>,
CrudRepository<Request, Long> {

    // @SuppressWarnings("null")
    // Page<ShopRequest> findAll(Pageable pageable);

    List<Request> findAllByDeletedAtIsNull();
}
