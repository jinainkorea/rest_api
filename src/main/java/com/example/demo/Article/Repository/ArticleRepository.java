package com.example.demo.Article.Repository;

import com.example.demo.Article.Entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
