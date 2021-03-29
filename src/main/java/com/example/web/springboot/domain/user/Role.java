package com.example.web.springboot.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    GUEST("ROLE_GUEST","손님"), //key는 전부 대문자, 공백은 _ 로
    USER("ROLE_USER","일반 사용자");
//    ADMIN("ROLE_ADMIN","관리자"); //스프링 시큐리티에서 key는 꼭 이런식으로 표기해야 한다.
    private final String key;
    private final String title;

}

