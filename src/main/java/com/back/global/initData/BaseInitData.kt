package com.back.global.initData

import com.back.domain.member.member.service.MemberService
import com.back.domain.post.post.service.PostService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Lazy
import org.springframework.transaction.annotation.Transactional

@Configuration // dev, test, prod 등 활성화된 프로파일에 상관없이 항상 실행
class BaseInitData(
    private val memberService: MemberService,
    private val postService: PostService
) {
    // 1. lateinit var를 사용하여 널 안정성 확보 및 !! 제거
    @Autowired
    @Lazy
    private lateinit var self: BaseInitData

    // 2. 단일 표현식 함수 및 람다 매개변수 생략
    @Bean
    fun baseInitDataApplicationRunner(): ApplicationRunner = ApplicationRunner {
        self.work1()
    }

    @Transactional
    fun work1() {
        if (memberService.count() > 0) return

        // 3. 미사용 변수 선언(memberSystem 등) 제거
        memberService.join("system", "1234", "시스템")
        memberService.join("admin", "1234", "관리자")
        val memberUser1 = memberService.join("user1", "1234", "유저1")
        val memberUser2 = memberService.join("user2", "1234", "유저2")
        memberService.join("user3", "1234", "유저3")

        if (postService.count() > 0) return

        // 3. 미사용 변수 선언(post1 등) 제거
        postService.write(memberUser1, "제목 1", "내용 1")
        postService.write(memberUser2, "제목 2", "내용 2")

        println("기본 데이터가 초기화되었습니다.")
    }
}