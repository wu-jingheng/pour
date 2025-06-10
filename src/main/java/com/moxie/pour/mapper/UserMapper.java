package com.moxie.pour.mapper;

import com.moxie.pour.dto.UserCreateDto;
import com.moxie.pour.dto.UserDto;
import com.moxie.pour.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = TenantMapper.class)
public interface UserMapper {
    @Mapping(target = "tenantId", source = "tenant.id")
    UserDto toDto(User user);

    @Mapping(target = "tenant", ignore = true) // Set manually in service
    User toEntity(UserCreateDto userCreateDto);
}
