package com.back.domain.post.post.repository

import com.back.domain.member.member.repository.MemberRepository
import com.back.domain.post.post.entity.Post
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional

@ActiveProfiles("test")
@SpringBootTest
@Transactional
class PostRepositoryTest {

    // 1. lateinit var 사용으로 널 안정성 확보 및 !! 제거
    @Autowired
    private lateinit var memberRepository: MemberRepository

    @Autowired
    private lateinit var postRepository: PostRepository

    @Test
    @DisplayName("2번 글 조회")
    fun t1() {
        // 2. findByIdOrNull 과 엘비스 연산자 사용
        val post2 = postRepository.findByIdOrNull(2) ?: throw NoSuchElementException()

        // 3. AssertJ static import로 코드 간소화
        assertThat(post2.title).isEqualTo("제목 2")
        assertThat(post2.content).isEqualTo("내용 2")
    }

    @Test
    @DisplayName("글 생성")
    fun t2() {
        val memberUser1 = memberRepository.findByIdOrNull(3) ?: throw NoSuchElementException()
        val post = Post(memberUser1, "제목 new", "내용 new")

        // ★ 중요: id가 Int? = null로 바뀌었으므로 저장 전 검증은 isNull()이어야 합니다.
        assertThat(post.id).isNull()

        // 4. 불필요한 제네릭 인수(<Post?>) 생략
        postRepository.save(post)

        assertThat(post.id).isGreaterThan(0)
        assertThat(post.title).isEqualTo("제목 new")
        assertThat(post.content).isEqualTo("내용 new")
    }

    @Test
    @DisplayName("글 개수 조회")
    fun t3() {
        val count = postRepository.count()

        assertThat(count).isEqualTo(2)
    }
}