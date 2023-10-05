package com.learn.awsspringlearn.domain.posts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;

    @AfterEach // JUnit5 <-> @After JUnit4, Junit에서 단위 테스트가 끝날 때마다 수행되는 메소드를 지정. 배포 전 전체 테스트를 수행할 때 테스트간 데이터 침범을 막기 위해 사용
    public void cleanUp() {
        postsRepository.deleteAll();
    }

    @Test
    @DisplayName("게시글 저장 불러오기")
    public void postSaveLoad() {
        // given
        String title = "테스트 게시물";
        String content = "테스트 본문";

        postsRepository.save(       // insert/update 쿼리를 실행, id값이 있다면 update가 없다면 insert 쿼리가 실행
                Posts.builder()
                        .title(title)
                        .content(content)
                        .author("testauthor12@gmail.com")
                        .build());
        // when
        List<Posts> list = postsRepository.findAll();   // 테이블 post에 있는 모든 데이터 조회

        // then
        Posts post = list.get(0);
        assertThat(post.getTitle()).isEqualTo(title);
        assertThat(post.getContent()).isEqualTo(content);
    }

    @Test
    @DisplayName("BaseEntity 등록")
    public void BaseEntityRegistry() {
        // given
        LocalDateTime now = LocalDateTime.of(2023, 10, 5, 0, 0, 0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>>>>>> createDate=" + posts.getCreatedDate() + ", modifyDate=" + posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}
