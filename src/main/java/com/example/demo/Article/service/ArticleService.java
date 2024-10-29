package com.example.demo.Article.service;

import com.example.demo.Article.dto.ArticleDTO;
import com.example.demo.Article.entity.Article;
import com.example.demo.Article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public List<ArticleDTO> list() {
        List<Article> articleList = this.articleRepository.findAll();
        List<ArticleDTO> articleDTOList = articleList.stream()
                .map(article -> new ArticleDTO(article))
                .collect(Collectors.toList());
        if (articleDTOList == null) {
            return new ArrayList<>();
        }
        return articleDTOList;
    }

    public Article getArticle(Long id) {
        return this.articleRepository.findById(id).get();
    }

    public ArticleDTO getArticleDTO(Long id) {
        return new ArticleDTO(articleRepository.findById(id)
                .orElseThrow(() -> null));
    }

    public void create(String subject, String content) {
        Article article = new Article().builder()
                        .subject(subject)
                        .content(content)
                        .build();
        this.articleRepository.save(article);
    }

    public ArticleDTO modify(ArticleDTO articleDTO, String subject , String content) {
        // 게시글을 ID로 찾아서 수정
        Article article = new Article().builder()
                        .id(articleDTO.getId())
                        .subject(subject)
                        .content(content)
                        .build();

        this.articleRepository.save(article); // 변경 사항 저장
        return new ArticleDTO(article);
    }

    public void delete(Article article) {
        this.articleRepository.delete(article);
    }
}
