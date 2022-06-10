package com.lecture.spring.order;

import com.lecture.spring.discount.DiscountPolicy;
import com.lecture.spring.member.Member;
import com.lecture.spring.member.MemberRepository;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    // @RequiredArgsConstructor를 통해 생성자가 만들어짐
    // 생성자가 하나만 있다면 @Autowired를 생략할 수 있다.
    // @Qualifier - Bean 이름 이외에 추가로 구분할 수 있는 구분자를 설정해 줄 수 있는 애노테이션
    public OrderServiceImpl(MemberRepository memberRepository,
                            DiscountPolicy discountPolicy) {
//                            @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트용
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
