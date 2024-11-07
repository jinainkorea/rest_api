package com.example.demo.Domain.Article.entity;

import com.example.demo.Domain.Member.entity.Member;
import com.example.demo.global.jpa.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne
    @JoinColumn(name = "member_id") // 외래키 설정
    private Member member;
}
