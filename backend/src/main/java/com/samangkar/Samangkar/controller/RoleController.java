package com.samangkar.Samangkar.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samangkar.Samangkar.dto.RoleDto;
import com.samangkar.Samangkar.model.Role;
import com.samangkar.Samangkar.repository.RoleRepository;
import com.samangkar.Samangkar.service.RoleService;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequestMapping("api/roles")
public class RoleController {
    
    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("get-all")
    public ResponseEntity<?> getAllRoles() {
        try {
            List<RoleDto> roles = roleService.getAllRoles();
            return ResponseEntity.ok(roles);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }

    @PostMapping("add/{roleName}")
    public ResponseEntity<?> addRole(@PathVariable String roleName) {
        try {
            if (roleRepository.findByName(roleName).isEmpty()) {
                Role role = new Role(roleName);
                roleRepository.save(role);
                List<RoleDto> roles = roleService.getAllRoles();
                return ResponseEntity.ok(roles);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Role with name " + roleName + " already exists.");
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }
    
    @SuppressWarnings("null")
    @PostMapping("modify/{roleId}/{newRoleName}")
    public ResponseEntity<?> changeRoleName(@PathVariable Long roleId, @PathVariable String newRoleName) {
        try {
            if (roleRepository.findById(roleId).isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Role with name " + roleId + " does not exists.");
            } else {
                Role role = roleRepository.findById(roleId).get();
                role.setName(newRoleName);
                roleRepository.save(role);
                List<RoleDto> roles = roleService.getAllRoles();
                return ResponseEntity.ok(roles);
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }
    
    @SuppressWarnings("null")
    @PostMapping("delete/{roleId}")
    public ResponseEntity<?> deleteRole(@PathVariable Long roleId) {
        try {
            if (roleRepository.findById(roleId).isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Role with name " + roleId + " does not exists.");
            } else {
                Role role = roleRepository.findById(roleId).get();
                role.setDeletedAt(new Date());
                roleRepository.save(role);
                List<RoleDto> roles = roleService.getAllRoles();
                return ResponseEntity.ok(roles);
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }
    
    
}
