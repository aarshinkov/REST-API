package com.safb.rest.security;

public class SecurityConstants
{
  public static final Long EXPIRATION_TIME = 864000000l; // 10 days
  public static final String TOKEN_PREFIX = "bss ";
  public static final String HEADER_STRING = "Authorization";
  public static final String SIGN_UP_URL = "/api/users";
}
