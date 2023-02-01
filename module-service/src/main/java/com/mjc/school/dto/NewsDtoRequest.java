package com.mjc.school.dto;

public record NewsDtoRequest(
    Long id,
    String title,
    String content,
    Long authorId
) { }
