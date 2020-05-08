package com.pam.blog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class PostNotFoundException extends RuntimeException
{

    private static final long serialVersionUID = 1348771109171435607L;

    public PostNotFoundException(long id)
    {
        super("Post with ID of " + id + " not found");
    }
}

