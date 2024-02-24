package com.samangkar.Samangkar.repository;

import com.samangkar.Samangkar.model.PackageModel;
import com.samangkar.Samangkar.model.Shop;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "packages", path = "packages")
public interface PackageRepository extends PagingAndSortingRepository<PackageModel, Long>,
        CrudRepository<PackageModel, Long> {

    List<PackageModel> findByName(@Param("name") String name);

    List<PackageModel> findByShop(@Param("shop") Shop shop);

    List<PackageModel> findByNameAndShop(@Param("name") String name, @Param("shop") Shop shop);

}
