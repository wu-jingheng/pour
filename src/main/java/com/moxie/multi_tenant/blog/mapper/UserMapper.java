package com.moxie.multi_tenant.blog.mapper;

import com.moxie.multi_tenant.blog.dto.UserCreateDto;
import com.moxie.multi_tenant.blog.dto.UserDto;
import com.moxie.multi_tenant.blog.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = TenantMapper.class)
public interface UserMapper {
    @Mapping(target = "tenantId", source = "tenant.id")
    UserDto toDto(User user);

    @Mapping(target = "tenant", ignore = true) // Set manually in service
    User toEntity(UserCreateDto userCreateDto);
}
