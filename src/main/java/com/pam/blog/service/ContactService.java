package com.pam.blog.service;

import com.pam.blog.shared.dto.UserDto;

import java.util.List;

public interface ContactService
{
    List<UserDto> index(String email);

    UserDto store(String email, String userId);

    UserDto destroy(String email, String userId);
}
