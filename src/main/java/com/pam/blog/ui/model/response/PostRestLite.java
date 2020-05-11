package com.pam.blog.ui.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class PostRestLite
{
    private long id;
    private String title;
    private String body;
    @JsonIgnore
    private UserRest user;
    @JsonIgnore
    private List<PostRest> posts;
}
