package com.pam.blog.shared.dto;

import lombok.Data;

import java.util.List;

@Data
public class PostDto
{
    private long id;
    private String title;
    private String body;
    private UserDto user;
    private List<TagDto> tags;
}
