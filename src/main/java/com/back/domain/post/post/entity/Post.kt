package com.back.domain.post.post.entity

import com.back.domain.member.member.entity.Member
import com.back.global.jpa.entity.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne

@Entity
class Post(
    @ManyToOne // N:1 관계
    var author: Member? = null, // 작성자는 Nullable하게 유지 (또는 필요에 따라 필수값 지정 가능)

    var title: String = "",

    @Column(columnDefinition = "TEXT")
    var content: String = ""
) : BaseEntity() {

    override fun toString(): String {
        return "Post(author=$author, title='$title', content='$content')"
    }
}