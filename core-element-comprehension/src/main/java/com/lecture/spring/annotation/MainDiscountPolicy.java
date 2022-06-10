package com.lecture.spring.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

// @Qualifier에 있는 어노테이션들
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Qualifier("mainDiscountPolicy")
public @interface MainDiscountPolicy {
}
