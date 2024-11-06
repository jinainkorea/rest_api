package com.example.demo.Article.Service;

import com.example.demo.Article.DTO.ArticleDTO;
import com.example.demo.Article.Entity.Article;
import com.example.demo.Article.Repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleService {
    public final ArticleRepository articleRepository;

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

    public Article getArticleById(Long id) {
        Optional<Article> articleObj = this.articleRepository.findById(id);
        if (articleObj.isEmpty()) {
            return null;
        } else {
            return articleObj.get();
        }
    }

    public ArticleDTO getArticleDTOById(Long id) {
        Optional<Article> articleObj = this.articleRepository.findById(id);
        if (articleObj.isEmpty()) {
            return null;
        } else {
            return new ArticleDTO(articleObj.get());
        }
    }

    public ArticleDTO create(String subject, String content) {
        Article article = new Article().builder()
                .subject(subject)
                .content(content)
                .author("작성자")
                .build();

        this.articleRepository.save(article);

        return new ArticleDTO(article);
    }

    public ArticleDTO update(Article article, String subject, String content) {
        Article modifiedArticle = new Article().builder()
                .id(article.getId())
                .subject(subject)
                .content(content)
                .author(article.getAuthor())
                .createdDate(article.getCreatedDate())
                .build();

        this.articleRepository.save(modifiedArticle);

        return new ArticleDTO(modifiedArticle);
    }

    public void delete(Article article) {
        this.articleRepository.delete(article);
    }
}
