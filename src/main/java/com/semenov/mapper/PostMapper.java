package com.semenov.mapper;


import com.semenov.dto.PostDto;
import com.semenov.model.Post;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class PostMapper {

    public abstract PostDto toPostDto(Post post);

}
