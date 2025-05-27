package com.semenov.controller;

import com.semenov.service.CommentService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CommentController.class)
class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentService commentService;

    @InjectMocks
    private CommentController commentController;

    private static final Long POST_ID = 1L;
    private static final Long COMMENT_ID = 10L;
    private static final String COMMENT_TEXT = "Test comment";
    private static final String REDIRECT_PATH = "redirect:posts/" + POST_ID;


    @Test
    @SneakyThrows
    void addComment_ShouldCallServiceAndRedirect() {
        mockMvc.perform(post("/posts/{postId}/comments", POST_ID)
                .param("text", COMMENT_TEXT))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("posts/" + POST_ID));


        verify(commentService).addComment(POST_ID, COMMENT_TEXT);
    }

    @Test
    void deleteComment_ShouldCallServiceAndRedirect() throws Exception {
        mockMvc.perform(post("/posts/{postId}/comments/{commentId}/delete", POST_ID, COMMENT_ID))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("posts/" + POST_ID));

        verify(commentService).deleteComment(COMMENT_ID);
    }
}
