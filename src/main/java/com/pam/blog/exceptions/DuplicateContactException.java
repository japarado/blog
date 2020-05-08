package com.pam.blog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class DuplicateContactException extends RuntimeException
{

    private static final long serialVersionUID = 1348771109171435607L;

    public DuplicateContactException(String userUd)
    {
        super("User with ID of " + userUd + " is already in your contact list");
    }
}

