package com.team9.boardapi.entity;

public enum UserRoleEnum {

    USER(Authority.USER),  // 사용자 권한
    ADMIN(Authority.ADMIN);  // 관리자 권한

    //UserDetailsImpl에서 authority가 추상화 되어서 반환이 된다.(SimpleGrantedAuthority)
    private final String authority;

    UserRoleEnum(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return this.authority;
    }

    public static class Authority {
        public static final String USER = "ROLE_USER";
        public static final String ADMIN = "ROLE_ADMIN";
    }
}
