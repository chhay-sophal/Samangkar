package com.samangkar.Samangkar.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samangkar.Samangkar.dto.ContactTypeDto;
import com.samangkar.Samangkar.model.ContactType;
import com.samangkar.Samangkar.repository.ContactTypeRepository;

@Service
public class ContactTypeService {

    @Autowired
    private ContactTypeRepository contactTypeRepository;

    public List<ContactTypeDto> getAllContactTypes() {
        Iterable<ContactType> contactTypes = contactTypeRepository.findByDeletedAtIsNull();

        return StreamSupport.stream(contactTypes.spliterator(), false)
            .map(contactType -> new ContactTypeDto(
                contactType.getId(), 
                contactType.getPlatform(),
                contactType.getCreatedAt(), 
                contactType.getUpdatedAt()
            ))
            .collect(Collectors.toList());
    }

}
