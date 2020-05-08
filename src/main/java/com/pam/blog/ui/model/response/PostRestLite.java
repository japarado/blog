package com.pam.blog.ui.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class PostRestLite
{
    private long id;
    private String title;
    private String body;
    @JsonIgnore
    private UserRest user;
}
