package com.lecture.spring.discount;

import com.lecture.spring.member.Grade;
import com.lecture.spring.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10프로 할인이 적용되어야 한다.")
    void vip_o() {
        Member member = new Member(1L, "memberVIP", Grade.VIP);

        int discount = discountPolicy.discount(member, 20000);

        assertEquals(2000, discount);
    }

    @Test
    @DisplayName("VIP가 아니면 할인이 적용되지 않아야 한다.")
    void vip_x() {
        Member member = new Member(1L, "memberVIP", Grade.BASIC);

        int discount = discountPolicy.discount(member, 10000);

        assertEquals(0, discount);
    }
}