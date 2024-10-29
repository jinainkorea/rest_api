package com.example.demo.Article.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ArticleModifyRequest {
    @NotBlank
    private String subject;
    @NotBlank
    private String content;
}
