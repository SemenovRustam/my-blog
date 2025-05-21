package com.semenov.controller;

import com.semenov.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.semenov.utils.Const.REDIRECT;
import static com.semenov.utils.Const.SLASH;

@Controller
@RequestMapping("/posts/{id}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService service;

    @PostMapping
    public String addComment(
            @PathVariable("id") Long postId,
            @RequestParam("text") String text
    ) {
        service.addComment(postId, text);

        return REDIRECT + SLASH + postId;
    }

    @PostMapping("/{commentId}")
    public String editComment(
            @PathVariable("id") Long postId,
            @PathVariable("commentId") Long commentId,
            @RequestParam("text") String text
    ) {
        service.editComment(commentId, text);

        return REDIRECT + SLASH + postId;
    }

    @PostMapping(value = "/{commentId}/delete")
    public String delete(
            @PathVariable("id") Long postId,
            @PathVariable("commentId") Long commentId
    ) {
        service.deleteComment(commentId);

        return REDIRECT + SLASH + postId;
    }
}
