package com.lecture.spring;

import com.lecture.spring.discount.DiscountPolicy;
import com.lecture.spring.discount.RateDiscountPolicy;
import com.lecture.spring.member.MemberRepository;
import com.lecture.spring.member.MemberService;
import com.lecture.spring.member.MemberServiceImpl;
import com.lecture.spring.member.MemoryMemberRepository;
import com.lecture.spring.order.OrderService;
import com.lecture.spring.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
}
