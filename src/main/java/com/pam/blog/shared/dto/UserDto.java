package com.pam.blog.shared.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDto
{
    private long id;
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String encryptedPassword;
    private String emailVerificationToken;
    private Boolean emailVerificationStatus = false;
    private List<PostDto> posts;
    private List<UserDto> contacts;
}


