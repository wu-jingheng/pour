package com.moxie.multi_tenant.blog.mapper;

import com.moxie.multi_tenant.blog.dto.PostDto;
import com.moxie.multi_tenant.blog.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface PostMapper {
    @Mapping(target = "tenantId", source = "tenant.id")
    @Mapping(target = "authorId", source = "author.id")
    @Mapping(target = "createdAt", source = "createdAt", qualifiedByName = "formatDate")
    PostDto toDto(Post post);

    @Mapping(target = "tenant.id", source = "tenantId")
    @Mapping(target = "author.id", source = "authorId")
    Post toEntity(PostDto postDto);

    @Named("formatDate")
    default String formatDate(LocalDateTime dateTime) {
        return dateTime != null ? dateTime.format(DateTimeFormatter.ISO_DATE_TIME) : null;
    }
}
