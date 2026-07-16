package com.back.domain.post.post.service

import com.back.domain.member.member.entity.Member
import com.back.domain.post.post.entity.Post
import com.back.domain.post.post.repository.PostRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PostService(private val postRepository: PostRepository) {

    fun count(): Long = postRepository.count()

    // 1. Optional 대신 코틀린 Nullable 타입 사용
    fun findById(id: Int): Post? = postRepository.findByIdOrNull(id)

    fun modify(post: Post, title: String, content: String) {
        post.title = title
        post.content = content
    }

    // 2. 단일 표현식 함수 및 반환식 간소화
    fun write(author: Member?, title: String, content: String): Post =
        postRepository.save(Post(author, title, content))
}