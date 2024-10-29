package com.example.demo.Article.entity;

import com.example.demo.global.jpa.BaseEntity;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString
public class Article extends BaseEntity {
    private String subject;

    private String content;
}
