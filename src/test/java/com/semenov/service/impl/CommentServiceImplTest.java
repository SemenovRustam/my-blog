package com.semenov.service.impl;

import com.semenov.repository.CommentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class CommentServiceImplTest {

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentServiceImpl commentService;

    private static final Long POST_ID = 1L;
    private static final Long COMMENT_ID = 10L;
    private static final String COMMENT_TEXT = "Test comment";

    @Test
    public void addComment_ShouldCallRepositorySave() {
        commentService.addComment(POST_ID, COMMENT_TEXT);

        verify(commentRepository).save(POST_ID, COMMENT_TEXT);
    }

    @Test
    public void editComment_ShouldCallRepositoryUpdate() {
        commentService.editComment(COMMENT_ID, COMMENT_TEXT);

        verify(commentRepository).update(COMMENT_ID, COMMENT_TEXT);
    }

    @Test
    public void deleteComment_ShouldCallRepositoryDelete() {
        commentService.deleteComment(COMMENT_ID);


        verify(commentRepository).deleteById(COMMENT_ID);
    }
}