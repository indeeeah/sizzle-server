package com.sizzle.server.domains.enums;

import com.sizzle.server.enums.IEnumText;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Schema(name = "SocialType", description = "소셜 로그인 타입")
public enum SocialType implements IEnumText {

    GOOGLE("GOOGLE"), NAVER("NAVER"), KAKAO("KAKAO");

    private final String text;
}
