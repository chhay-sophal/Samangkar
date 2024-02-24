package com.samangkar.Samangkar.repository;

import com.samangkar.Samangkar.model.ServiceModel;
import com.samangkar.Samangkar.model.Shop;
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

}