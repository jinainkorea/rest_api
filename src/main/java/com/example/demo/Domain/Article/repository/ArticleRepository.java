package com.example.demo.Domain.Article.repository;

import com.example.demo.Domain.Article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
