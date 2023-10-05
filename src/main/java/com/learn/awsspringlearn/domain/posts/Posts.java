package com.learn.awsspringlearn.domain.posts;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor  // 기본 생성자 자동 추가. public Posts() {}
@Entity             // 테이블과 링크될 클래스임을 나타냄
public class Posts {
    @Id     // PK 필드임을 나타냅니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 생성 규칙을 나타내는 것으로 IDENTITY는 auto_increment
    private Long id;

    @Column(length = 500, nullable = false)     // 테이블의 칼럼을 나타내며 굳이 선언하지 않아도 되지만 값 변경이 필요할 때 사용
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder        // 해당 클래스의 빌더 패턴 클래스 생성, 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
