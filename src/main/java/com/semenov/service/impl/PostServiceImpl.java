package com.semenov.service.impl;

import com.semenov.dto.PostDto;
import com.semenov.mapper.PostMapper;
import com.semenov.model.Post;
import com.semenov.repository.PostRepository;
import com.semenov.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostMapper mapper;

    @Override
    public List<PostDto> findPosts(String search, Integer pageNumber, Integer pageSize) {
        return postRepository.findAll(search, pageNumber, pageSize)
                .stream()
                .map(mapper::toPostDto)
                .toList();
    }

    @Override
    public Post getPostById(Long postId) {
        return null;
    }

    @Override
    public Long addPost(String title, String text, MultipartFile image, String tags) {
        return 0L;
    }

    @Override
    public void likePostById(Long postId, Boolean like) {

    }

    @Override
    public void editPost(Long postId, String title, String text, MultipartFile image, String tags) {

    }

    @Override
    public void deletePost(Long postId) {

    }
}
