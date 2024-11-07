package com.example.demo.Domain.Article.response;

import com.example.demo.Domain.Article.dto.ArticleDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ArticleResponse {
    private  final ArticleDTO article;
}
