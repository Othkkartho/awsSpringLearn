package com.learn.awsspringlearn.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);       // email을 통해 이미 생성된 사용자인지 판단하기위한 메소드
}
