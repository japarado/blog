package com.pam.blog.exceptions;

public class PostServiceException extends RuntimeException{

    private static final long serialVersionUID = 1348771109171435607L;

    public PostServiceException(String message)
    {
        super(message);
    }
}

