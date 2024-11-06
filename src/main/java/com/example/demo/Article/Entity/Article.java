package com.example.demo.Article.Entity;

import com.example.demo.Global.Jpa.BaseEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString
@Entity
public class Article extends BaseEntity {
    private String subject;

    private String content;
}
