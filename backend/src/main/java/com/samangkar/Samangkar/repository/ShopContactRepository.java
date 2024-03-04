package com.samangkar.Samangkar.repository;

import com.samangkar.Samangkar.model.Shop;
import com.samangkar.Samangkar.model.ShopContact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "shop-contacts", path = "shops-contacts")
public interface ShopContactRepository extends PagingAndSortingRepository<ShopContact, Long>,
        CrudRepository<ShopContact, Long> {

    List<ShopContact> findByUrl(@Param("url") String url);

    List<ShopContact> findByShop(@Param("shop") Shop shop);
    
    List<ShopContact> findByShopAndDeletedAtIsNull(@Param("shop") Shop shop);

    ShopContact findFirstById(Long id);

}