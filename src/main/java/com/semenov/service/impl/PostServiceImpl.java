package com.semenov.service.impl;


import com.semenov.dto.EditPostDto;
import com.semenov.dto.PostDto;
import com.semenov.mapper.PostMapper;
import com.semenov.model.Comment;
import com.semenov.model.Post;
import com.semenov.repository.CommentRepository;
import com.semenov.repository.PostRepository;
import com.semenov.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.apache.commons.io.FilenameUtils.getExtension;
import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {


    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final PostMapper postMapper;

    @Override
    public List<PostDto> findPosts(String search, Integer pageNumber, Integer pageSize) {
        Integer offset = (pageNumber - 1) * pageSize;

        return postMapper.toPostDtos(postRepository.findAll(search, pageSize, offset).stream()
                .map(this::enrichPost)
                .toList());
    }

    @Override
    public PostDto getPostById(Long postId) {
        PostDto postDto = postRepository.getPostById(postId)
                .map(this::enrichPost)
                .map(postMapper::toPostDto)
                .orElseThrow(() -> new RuntimeException("Не найден поста с id = " + postId));
        return postDto;
    }


    @Override
    public Long addPost(String title, String text, MultipartFile image, String tags) {

        Post post = Post.builder()
                .title(title)
                .text(text)
                .likesCount(0L)
                .tags(tags.split(","))
                .build();

        Long postId = postRepository.save(post);

        if (image.isEmpty()) {
            return postId;
        }
        try {
            String imagePath = uploadImage(postId, image);

            postRepository.updateImagePath(postId, imagePath);
        } catch (IOException e) {
            log.error(e.getMessage(), postId);
        }

        return postId;
    }

    @Override
    public void likePostById(Long postId, Boolean like) {
        postRepository.updateLikesCount(postId, like ? 1 : -1);
    }

    @Override
    public void editPost(Long postId, String title, String text, MultipartFile image, String tags) {
        PostDto postDto = getPostById(postId);
        String imagePath = postDto.getImagePath();

        Post post = Post.builder()
                .id(postId)
                .title(title)
                .imagePath(imagePath)
                .text(text)
                .tags(isEmpty(tags) ? postDto.getTags() : tags.split(","))
                .build();

        postRepository.update(post);
    }

    @Override
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    @Override
    public EditPostDto getEditPostDtoById(Long postId) {
        return postMapper.toEditPostDto(getPostById(postId));
    }

    private String uploadImage(Long postId, MultipartFile image) throws IOException {

        String imagePath = "image-post-" + postId + "." + getExtension(image.getOriginalFilename());

        Path uploadDir = Paths.get(System.getProperty("user.dir"), "uploads");
        Files.createDirectories(uploadDir);

        Path path = uploadDir.resolve(imagePath);

        image.transferTo(path);

        return imagePath;
    }

    private Post enrichPost(Post post) {
        List<Comment> comments = commentRepository.findByPostId(post.getId());
        post.setComments(comments);
        return post;
    }
}
