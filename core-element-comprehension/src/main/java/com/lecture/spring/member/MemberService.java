package com.lecture.spring.member;

public interface MemberService {

    void join(Member member);

    Member findMember(Long id);
}
