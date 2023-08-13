package org.example.dto.impl;


import org.example.dto.RoleDto;

public class RoleDtoToConverter {
    public RoleDto convertRoleNameToRoleDto(String roleName){
        RoleDto roleDto=new RoleDto();
        roleDto.setRoleName(roleName);
        return roleDto;
    }
}
