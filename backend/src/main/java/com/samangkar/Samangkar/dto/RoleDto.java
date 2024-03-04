package com.samangkar.Samangkar.dto;

import lombok.Data;

@Data
public class RoleDto {
    
    private Long roleId;
    private String roleName;
    private String createdAt;
    private String modifiedAt;

    public RoleDto(Long roleId, String roleName, String createdAt, String modifiedAt) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

}
