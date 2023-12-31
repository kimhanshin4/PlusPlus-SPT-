package com.sparta.plusplus.global.exception;

import lombok.*;

@Getter
public enum ResultCode {
    //System
    SUCCESS(0, "정상 처리 되었습니다"),
    SYSTEM_ERROR(1000, "알 수 없는 애러가 발생했습니다."),
    //User
    NOT_EXIST_USER(2000, "등록되지 않은 녀석이로군!"),
    EXIST_USER(2001, "이미 있는 사용자 잖아요!"),
    EXIST_NICKNAME(2002, "오잉? 닉네임 중복검사 다시하고 오세요!"),
    EXIST_EMAIL(2003, "이미 사용 중인 이메일 이에요!"),
    NOT_EXIST_POST(2010, "게시글이 없거나 작성자가 아니군요!"),
    NOT_EXIST_COMMENT(2012, "댓글이 존재하지 않거나 작성자가 아닙니다."),
    ALREADY_FOLLOWED(3000, "이미 팔로우된 유저입니다."),
    NOT_YET_FOLLOWED(3001, "아직 팔로우되지 않은 유저입니다."),
    //Post
    ALREADY_LIKED_BOARD(3002, "이미 좋아요가 눌린 게시글입니다."),
    NOT_YET_LIKED_BOARD(3003, "아직 좋아요를 누르지 않은 게시글입니다."),
    //Comment
    ALREADY_LIKED_COMMENT(3004, "이미 좋아요가 눌린 댓글입니다."),
    NOT_YET_LIKED_COMMENT(3005, "아직 좋아요를 누르지 않은 댓글입니다."),
    //Security
    EXPIRED_JWT_TOKEN(4000, "만료된 JWT token 입니다."),
    INVALID_JWT_SIGNATURE(4001, "유효하지 않는 JWT 서명 입니다."),
    UNSUPPORTED_JWT_TOKEN(4002, "지원되지 않는 JWT 토큰 입니다."),
    JWT_CLAIMS_IS_EMPTY(4003, "잘못된 JWT 토큰 입니다."),
    NO_MATCHES_PASSWORD(5000, "!!! 비밀번호가 틀려! 누구인가 당신!"),
    NOT_AUTHORIZATION(5000, "!!! 비밀번호가 틀려! 누구인가 당신!");

    private Integer code;
    private String message;

    ResultCode(Integer code, String errorMessage) {
        this.code = code;
        this.message = errorMessage;
    }

}
