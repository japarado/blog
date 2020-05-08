package com.pam.blog.ui.controller;

import com.pam.blog.shared.dto.UserDto;
import org.springframework.security.core.Authentication;

public abstract class Controller
{
    private String getAuthEmail(Authentication authentication)
    {
        return authentication.getName();
    }
}
