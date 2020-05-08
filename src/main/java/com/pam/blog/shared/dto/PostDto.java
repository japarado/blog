package com.pam.blog.shared.dto;

import lombok.Data;

@Data
public class PostDto
{
    private long id;
    private String title;
    private String body;
    private UserDto user;
}
