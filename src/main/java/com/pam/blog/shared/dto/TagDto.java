package com.pam.blog.shared.dto;

import lombok.Data;

import java.util.List;

@Data
public class TagDto
{
    public long id;
    public String name;
    public String description;
    public List<PostDto>  posts;
}
