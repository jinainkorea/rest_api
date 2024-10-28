package com.example.demo.dto;


import com.example.demo.entity.Article;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter
public class ArticleDTO {
    private final Long id;

    private final String subject;

    private final String content;

    private final LocalDateTime createdDate;

    private final LocalDateTime modifiedDate;

    public ArticleDTO(Article article) {
        this.id = article.getId();
        this.subject = article.getSubject();
        this.content = article.getContent();
        this.createdDate = article.getCreatedDate();
        this.modifiedDate = article.getModifiedDate();
    }
}
