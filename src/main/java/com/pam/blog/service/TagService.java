package com.pam.blog.service;

import com.pam.blog.shared.dto.TagDto;

import java.util.List;

public interface TagService
{
    List<TagDto> index();

    TagDto store(TagDto tag);
}
