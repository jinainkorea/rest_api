package com.example.demo.Domain.Article.dto;


import com.example.demo.Domain.Article.entity.Article;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter
public class ArticleDTO {
    private final Long id;

    private final String subject;

    private final String content;

    private final String author;

    private final LocalDateTime createdDate;

    private final LocalDateTime modifiedDate;

    public ArticleDTO(Article article) {
        this.id = article.getId();
        this.subject = article.getSubject();
        this.content = article.getContent();
        this.author = article.getMember() != null ? article.getMember().getUsername() : "Unknown"; // null 체크 추가
        this.createdDate = article.getCreatedDate();
        this.modifiedDate = article.getModifiedDate();
    }
}