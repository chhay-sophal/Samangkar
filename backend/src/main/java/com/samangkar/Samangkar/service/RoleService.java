package com.samangkar.Samangkar.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samangkar.Samangkar.dto.RoleDto;
import com.samangkar.Samangkar.model.Role;
import com.samangkar.Samangkar.repository.RoleRepository;

@Service
public class RoleService {
    
    @Autowired
    private RoleRepository roleRepository;
    
    public List<RoleDto> getAllRoles() {
        Iterable<Role> roles = roleRepository.findByDeletedAtIsNull();
        List<RoleDto> roleDtos = StreamSupport.stream(roles.spliterator(), false)
                .map(role -> new RoleDto(
                    role.getId(), 
                    role.getName(), 
                    role.getCreatedAt(), 
                    role.getUpdatedAt()
                ))
                .collect(Collectors.toList());
        return roleDtos;
    }
}
