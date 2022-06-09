package com.lecture.spring.member;

public interface MemberRepository {

    void save(Member member);

    Member findById(Long id);
}
