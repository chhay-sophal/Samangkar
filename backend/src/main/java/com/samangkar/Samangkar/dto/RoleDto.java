package com.samangkar.Samangkar.dto;

import java.util.Date;

import lombok.Data;

@Data
public class RoleDto {
    
    private Long roleId;
    private String roleName;
    private Date createdAt;
    private Date updatedAt;

    public RoleDto(Long roleId, String roleName, Date createdAt, Date updatedAt) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}
