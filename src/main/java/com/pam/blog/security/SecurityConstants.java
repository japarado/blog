package com.pam.blog.security;


import com.pam.blog.SpringApplicationContext;

public class SecurityConstants
{
    public static final long EXPIRATION_TIME = 86400000000L;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/users";

    public static  String getTokenSecret()
    {
        AppProperties appProperties = (AppProperties) SpringApplicationContext
                .getBean("AppProperties");
        return appProperties.getTokenSecret();
    }
}

