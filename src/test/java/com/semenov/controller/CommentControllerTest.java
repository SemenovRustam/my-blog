package com.semenov.controller;

import com.semenov.service.CommentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class CommentControllerTest {

    @Mock
    private CommentService commentService;

    @InjectMocks
    private CommentController commentController;

    private static final Long POST_ID = 1L;
    private static final Long COMMENT_ID = 10L;
    private static final String COMMENT_TEXT = "Test comment";
    private static final String REDIRECT_PATH = "redirect:posts/" + POST_ID;


    @Test
    public void addComment_ShouldCallServiceAndRedirect() {
        String result = commentController.addComment(POST_ID, COMMENT_TEXT);

        verify(commentService).addComment(POST_ID, COMMENT_TEXT);

        assertEquals(REDIRECT_PATH, result);
    }

    @Test
    public void editComment_ShouldCallServiceAndRedirect() {
        String result = commentController.editComment(POST_ID, COMMENT_ID, COMMENT_TEXT);

        verify(commentService).editComment(COMMENT_ID, COMMENT_TEXT);

        assertEquals(REDIRECT_PATH, result);
    }


    @Test
    public void deleteComment_ShouldCallServiceAndRedirect() {
        String result = commentController.delete(POST_ID, COMMENT_ID);

        verify(commentService).deleteComment(COMMENT_ID);

        assertEquals(REDIRECT_PATH, result);
    }
}
