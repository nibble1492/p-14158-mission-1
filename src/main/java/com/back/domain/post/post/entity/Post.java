package com.back.domain.post.post.entity;

import com.back.domain.member.member.entity.Member;
import com.back.global.jpa.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Post extends BaseEntity {
    @ManyToOne // Post 가 많고(Many) Member 가 적다(One) // N:1 관계
    private Member author; // 필드명 : AUTHOR_ID, 설명 : MEMBER 테이블의 ID값이 저장
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;

    public Post() {
    }

    public Post(Member author, String title, String content) {
        this.author = author;
        this.title = title;
        this.content = content;
    }

    public Member getAuthor() {
        return author;
    }

    public void setAuthor(Member author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Post{" +
                "author=" + author +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}