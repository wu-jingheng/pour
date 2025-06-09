package com.moxie.multi_tenant.blog.mapper;

import com.moxie.multi_tenant.blog.dto.UserDto;
import com.moxie.multi_tenant.blog.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "tenantId", source = "tenant.id")
    UserDto toDto(User user);

    @Mapping(target = "tenant.id", source = "tenantId")
    User toEntity(UserDto userDto);
}
