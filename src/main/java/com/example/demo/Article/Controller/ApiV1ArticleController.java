package com.example.demo.Article.Controller;

import com.example.demo.Article.DTO.ArticleDTO;
import com.example.demo.Article.Entity.Article;
import com.example.demo.Article.Request.ArticleCreateRequest;
import com.example.demo.Article.Request.ArticleModifyRequest;
import com.example.demo.Article.Service.ArticleService;
import com.example.demo.Global.RsData.RsData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/articles")
@Tag(name="ApiV1Controller", description = "api")
public class ApiV1ArticleController {
    private final ArticleService articleService;

    @Operation(summary = "게시글 다건 조회")
    @GetMapping("")
    public RsData list() {
        return RsData.of("200", "게시글 다건 조회 성공", this.articleService.list());
    }


    @Operation(summary = "게시글 단건 조회")
    @GetMapping("/{id}")
    public RsData getArticle(@PathVariable(value = "id") Long id) {
        ArticleDTO articleDTO = this.articleService.getArticleDTOById(id);

        if (articleDTO == null) {
            return RsData.of("404", "게시글이 존재하지 않습니다.");
        } else {
            return RsData.of("200", "게시글 단건 조회 성공", articleDTO);
        }
    }

    @Operation(summary = "게시글 생성")
    @PostMapping("")
    public RsData create(@Valid @RequestBody ArticleCreateRequest articleCreateRequest) {
        ArticleDTO articleDTO = this.articleService.create(articleCreateRequest.getSubject(), articleCreateRequest.getContent());

        return RsData.of("201", "등록성공", articleDTO);
    }

    @Operation(summary = "게시글 수정")
    @PatchMapping("/{id}")
    public RsData update(@Valid @RequestBody ArticleModifyRequest articleModifyRequest, @PathVariable(value = "id") Long id) {
        Article article = this.articleService.getArticleById(id);

        if (article == null) {
            return RsData.of("404", "게시글이 존재하지 않습니다.");
        }

        ArticleDTO articleDTO = this.articleService.update(article, articleModifyRequest.getSubject(), articleModifyRequest.getContent());

        return RsData.of("200", "수정성공", articleDTO);
    }

    @Operation(summary = "게시글 삭제")
    @DeleteMapping("/{id}")
    public RsData delete(@PathVariable(value = "id") Long id) {
        Article article = this.articleService.getArticleById(id);

        if (article == null) {
            return RsData.of("404", "게시글이 존재하지 않습니다.");
        }

        this.articleService.delete(article);

        return RsData.of("200", "삭제성공", new ArticleDTO(article));
    }
}
