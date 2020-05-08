package com.pam.blog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ContactNotFoundException extends RuntimeException
{

    private static final long serialVersionUID = 1348771109171435607L;

    public ContactNotFoundException(String userId)
    {
        super("User with ID of " + userId + " is not part of your contact list");
    }
}

