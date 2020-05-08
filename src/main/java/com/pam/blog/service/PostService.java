package com.pam.blog.service;

import com.pam.blog.shared.dto.PostDto;

import java.util.List;

public interface PostService
{
    PostDto store(PostDto post);

    List<PostDto> index();

    PostDto update(long id, PostDto post);

    PostDto destroy(long id);

    PostDto show(long id);
}
