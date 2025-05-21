package com.semenov.mapper;

import com.semenov.dto.EditPostDto;
import com.semenov.dto.PostDto;
import com.semenov.model.Post;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;



// Укажите классы, которые нужно загрузить в контекст
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {PostMapperImpl.class  }) // Указываем, какие бины загружать
class PostMapperTest {

    @Autowired
    private PostMapper postMapper;

    @Test
    public void toPostDto_ShouldMapCorrectly() {
        // Подготовка тестовых данных
        Post post = new Post();
        post.setId(1L);
        post.setTitle("Test Title");

        // Вызов маппера
        PostDto postDto = postMapper.toPostDto(post);

        // Проверки
        assertNotNull(postDto);
        assertEquals(post.getId(), postDto.getId());
        assertEquals(post.getTitle(), postDto.getTitle());
    }

    @Test
    public void toEditPostDto_ShouldMapCorrectly() {
        // Подготовка тестовых данных
        PostDto postDto = new PostDto();
        postDto.setId(1L);
        postDto.setTitle("Test Title");

        // Вызов маппера
        EditPostDto editPostDto = postMapper.toEditPostDto(postDto);

        // Проверки
        assertNotNull(editPostDto);
        assertEquals(postDto.getId(), editPostDto.id());
        assertEquals(postDto.getTitle(), editPostDto.title());
    }

    @Test
    public void toPostDtos_ShouldMapListCorrectly() {
        // Подготовка тестовых данных
        Post post1 = new Post();
        post1.setId(1L);
        post1.setTitle("Title 1");

        Post post2 = new Post();
        post2.setId(2L);
        post2.setTitle("Title 2");

        List<Post> posts = List.of(post1, post2);

        // Вызов маппера
        List<PostDto> postDtos = postMapper.toPostDtos(posts);

        // Проверки
        assertNotNull(postDtos);
        assertEquals(2, postDtos.size());
        assertEquals(post1.getId(), postDtos.get(0).getId());
        assertEquals(post2.getTitle(), postDtos.get(1).getTitle());
    }
}
