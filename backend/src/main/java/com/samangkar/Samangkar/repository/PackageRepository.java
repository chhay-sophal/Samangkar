package com.samangkar.Samangkar.repository;

import com.samangkar.Samangkar.model.PackageModel;
import com.samangkar.Samangkar.model.Shop;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
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

    List<PackageModel> findByShop_Id(Long id);

    // @Query("SELECT p FROM PackageModel p WHERE p.shop = :shop AND p.deletedAt IS NULL")
    List<PackageModel> findNonDeletedPackagesByShop_Id(Long shopId);

    List<PackageModel> findAllByDeletedAtIsNull();

    // @SuppressWarnings("null")
    Page<PackageModel> findAllByDeletedAtIsNull(Pageable pageable);

    @Query("SELECT pkg FROM PackageModel pkg WHERE " +
        "LOWER(pkg.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
        "LOWER(pkg.description) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
        "pkg.deletedAt IS NULL OR " +
        "LOWER(pkg.shop.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<PackageModel> findPackagesByKeyword(String keyword, Pageable pageable);

    @Query("SELECT p FROM PackageModel p WHERE SIZE(p.services) > 0 AND p.deletedAt IS NULL")
    Page<PackageModel> findPackagesWithServicesAndDeletedAtIsNull(Pageable pageable);

    PackageModel findFirstById(Long id);
}
