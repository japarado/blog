package com.pam.blog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException
{

    private static final long serialVersionUID = 1348771109171435607L;

    public UserNotFoundException(String userId)
    {
        super("User with ID of " + userId + " was not found");
    }
}

