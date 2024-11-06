package com.example.demo.Article.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ArticleModifyRequest {
    @NotBlank
    private final String subject;

    @NotBlank
    private final String content;
}
