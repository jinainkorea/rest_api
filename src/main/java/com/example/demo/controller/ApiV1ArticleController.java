package com.example.demo.controller;

import com.example.demo.dto.ArticleDTO;
import com.example.demo.entity.Article;
import com.example.demo.global.RsData.RsData;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api/v1/articles")
public class ApiV1ArticleController {
    private List<ArticleDTO> articleDTOList;

    public ApiV1ArticleController() {
        this.articleDTOList = new ArrayList<>();
    }

    @GetMapping("")
    public RsData<List<ArticleDTO>> list() {
        List<ArticleDTO> articleList = new ArrayList<>();
        Article article1 = new Article("제목1", "내용1");
        ArticleDTO articleDTO1 = new ArticleDTO(article1);
        this.articleDTOList.add(articleDTO1);

        Article article2 = new Article("제목2", "내용2");
        ArticleDTO articleDTO2 = new ArticleDTO(article2);
        this.articleDTOList.add(articleDTO2);

        Article article3 = new Article("제목3", "내용3");
        ArticleDTO articleDTO3 = new ArticleDTO(article3);
        this.articleDTOList.add(articleDTO3);

        return RsData.of("200", "다건 조회 성공", this.articleDTOList);
    }

    @GetMapping("/{id}")
    public RsData<ArticleDTO> getArticle(@PathVariable("id") int id) {
        ArticleDTO article = this.articleDTOList.get(id-1);

        return RsData.of("200", "단건 조회 성공", article);
    }

    @PostMapping("")
    public RsData create(@RequestParam("subject") String subject, @RequestParam("content") String content) {
        Article article3 = new Article(subject, content);
        ArticleDTO articleDTO3 = new ArticleDTO(article3);
        this.articleDTOList.add(articleDTO3);
        return RsData.of("200", "생성 성공", this.articleDTOList.get(this.articleDTOList.size()-1));
    }

    @PatchMapping("/{id}")
    public RsData<ArticleDTO> modify(@PathVariable("id")int id, @RequestParam("subject") String subject, @RequestParam("content") String content) {
        this.delete(id);
        Article article3 = new Article(subject, content);
        ArticleDTO articleDTO3 = new ArticleDTO(article3);
        this.articleDTOList.add(id-1, articleDTO3);

        return RsData.of("200", "변경 성공", articleDTO3);
    }

    @DeleteMapping("/{id}")
    public RsData<Object> delete(@PathVariable("id") int id) {
        this.articleDTOList.remove(id-1);

        return RsData.of("200", "삭제 성공", this.articleDTOList);
    }
}
