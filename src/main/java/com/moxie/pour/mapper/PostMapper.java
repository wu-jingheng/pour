package com.moxie.pour.mapper;

import com.moxie.pour.dto.PostCreateDto;
import com.moxie.pour.dto.PostDto;
import com.moxie.pour.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring", uses = {TenantMapper.class, UserMapper.class})
public interface PostMapper {

    @Mapping(target = "tenantId", source = "tenant.id")
    @Mapping(target = "authorId", source = "author.id")
    // @Mapping(target = "createdAt", source = "createdAt", qualifiedByName = "formatDate")
    PostDto toDto(Post post);

    @Mapping(target = "tenant", ignore = true) // Set manually in service
    @Mapping(target = "author", ignore = true) // Set manually in service
    Post toEntity(PostCreateDto postCreateDto);

    @Named("formatDate")
    default String formatDate(LocalDateTime dateTime) {
        return dateTime != null ? dateTime.format(DateTimeFormatter.ISO_DATE_TIME) : null;
    }
}
