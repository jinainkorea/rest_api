package com.example.demo.Domain.Article.controller;

import com.example.demo.Domain.Article.dto.ArticleDTO;
import com.example.demo.Domain.Article.entity.Article;
import com.example.demo.Domain.Article.request.ArticleModifyRequest;
import com.example.demo.Domain.Article.service.ArticleService;
import com.example.demo.Domain.Article.request.ArticleCreateRequest;
import com.example.demo.Domain.Member.service.MemberService;
import com.example.demo.global.RsData.RsData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/articles")
@Tag(name="ApiV1Controller", description = "api")
public class ApiV1ArticleController {
    private final ArticleService articleService;
    private final MemberService memberService;

    @GetMapping("")
    @Operation(summary = "게시글 다건 조회")
    public RsData<List<ArticleDTO>> list() {
        return RsData.of("200", "다건 조회 성공", this.articleService.list());
    }

    @GetMapping("/{id}")
    @Operation(summary = "게시글 단건 조회")
    public RsData<ArticleDTO> getArticle(@PathVariable("id") Long id) {
        ArticleDTO articleDTO = this.articleService.getArticleDTO(id);

        return RsData.of("200", "단건 조회 성공", articleDTO);
    }

    @PostMapping("")
    @Operation(summary = "게시글 생성")
    public RsData create(@Valid @RequestBody ArticleCreateRequest articleCreateRequest) {
        Article article = new Article(articleCreateRequest.getSubject(), articleCreateRequest.getContent(), this.memberService.getMemberByName(articleCreateRequest.getAuthor()));
        ArticleDTO articleDTO = new ArticleDTO(article);
        this.articleService.create(articleDTO.getSubject(), articleDTO.getContent());
        return RsData.of("200", "생성 성공", articleDTO);
    }

    @PatchMapping("/{id}") // 특정 ID를 사용하여 수정
    @Operation(summary = "게시글 수정")
    public RsData<ArticleDTO> modify(@PathVariable Long id,
                                     @Valid @RequestBody ArticleModifyRequest articleModifyRequest) {
        Article article = this.articleService.getArticle(id);
        if (article == null) {
            return RsData.of("500", "게시물이 존재하지 않습니다.");
        }
        ArticleDTO articleDTO = new ArticleDTO(article);
        ArticleDTO modifiedDTO = this.articleService.modify(articleDTO, articleModifyRequest.getSubject(), articleModifyRequest.getContent());
        return RsData.of("200", "수정 성공", modifiedDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "게시글 삭제")
    public RsData<Object> delete(@PathVariable("id") Long id) {
        this.articleService.delete(this.articleService.getArticle(id));
        return RsData.of("200", "삭제 성공", this.list());
    }
}
