package com.semenov.mapper;


import com.semenov.dto.EditPostDto;
import com.semenov.dto.PostDto;
import com.semenov.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PostMapper {
    PostDto toPostDto(Post post);
    EditPostDto toEditPostDto(PostDto postDto);
    List<PostDto> toPostDtos(List<Post> posts);
}
