package com.lecture.spring.discount;

import com.lecture.spring.member.Member;

public interface DiscountPolicy {

    int discount(Member member, int price);
}
