package com.samangkar.Samangkar.repository;

import com.samangkar.Samangkar.model.ContactType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Date;


@RepositoryRestResource(collectionResourceRel = "contact-types", path = "contact-types")
public interface ContactTypeRepository extends PagingAndSortingRepository<ContactType, Long>,
        CrudRepository<ContactType, Long> {

    List<ContactType> findByPlatform(@Param("platform") String platform);

    ContactType findFirstByPlatform(@Param("platform") String platform);

    ContactType findFirstById(Long id);

    List<ContactType> findByDeletedAtIsNull();
}
