package com.semenov.service;

import com.semenov.dto.PostDto;
import com.semenov.model.Post;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostService {
    List<PostDto> findPosts(String search, Integer pageNumber, Integer pageSize);

    Post getPostById(Long postId);

    Long addPost(String title, String text, MultipartFile image, String tags);

    void likePostById(Long postId, Boolean like);

    void editPost(Long postId, String title, String text, MultipartFile image, String tags);

    void deletePost(Long postId);
}
