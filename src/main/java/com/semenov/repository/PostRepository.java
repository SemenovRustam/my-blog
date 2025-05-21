package com.semenov.repository;

import com.semenov.model.Post;
import java.util.List;
import java.util.Optional;

public interface PostRepository  {

    List<Post> findAll(String search, Integer pageSize, Integer offset);

    Optional<Post> getPostById(Long postId);

    Long save(Post post);

    void updateImagePath(Long postId, String imagePath);

    void updateLikesCount(Long postId, Integer likesCount);

    void update(Post post);

    void deleteById(Long postId);
}
