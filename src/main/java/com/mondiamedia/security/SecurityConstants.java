package com.mondiamedia.security;

public class SecurityConstants {

	public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 300;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/user/signup";
    public static final String LOGIN_URL = "/user/login";
}
