package com.semenov.dto;

public record EditPostDto(
        Long id,
        String title,
        String text,
        String tagsAsText) {
}
