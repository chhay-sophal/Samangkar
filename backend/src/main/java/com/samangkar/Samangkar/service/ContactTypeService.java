package com.samangkar.Samangkar.service;

import java.util.List;
import java.util.Optional;
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
        Iterable<ContactType> contactTypes = contactTypeRepository.findAll();

        return StreamSupport.stream(contactTypes.spliterator(), false)
            .map(contactType -> new ContactTypeDto(contactType.getId(), contactType.getPlatform()))
            .collect(Collectors.toList());
    }

    public List<ContactTypeDto> addContactType(String platform) {
        if (contactTypeRepository.findByPlatform(platform).isEmpty()) {
            ContactType newContactType = new ContactType(platform);

            contactTypeRepository.save(newContactType);
    
            return getAllContactTypes();
        } else {
            throw new ContactTypeExistsException("Contact type with platform " + platform + " already exists.");
        }
    }

    public List<ContactTypeDto> removeContactType(Long id) {
        contactTypeRepository.deleteById(id);

        return getAllContactTypes();
    }

    public List<ContactTypeDto> modifyContactType(Long id, String platform) {
        ContactType modifyContactType = contactTypeRepository.findFirstById(id);
        modifyContactType.setPlatform(platform);

        return getAllContactTypes();
    }

    public class ContactTypeExistsException extends RuntimeException {
        public ContactTypeExistsException(String message) {
            super(message);
        }
    }

}
