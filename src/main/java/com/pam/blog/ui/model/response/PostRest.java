package com.pam.blog.ui.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class PostRest
{
    private long id;
    private String title;
    private String body;
    private UserRestLite user;
    private List<TagRestLite> tags;
}
