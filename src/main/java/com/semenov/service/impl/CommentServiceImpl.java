package com.semenov.service.impl;

import com.semenov.repository.CommentRepository;
import com.semenov.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public void addComment(Long postId, String text) {
        commentRepository.save(postId, text);
    }

    @Override
    public void editComment(Long commentId, String text) {
        commentRepository.update(commentId, text);
    }

    @Override
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
