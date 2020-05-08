package com.pam.blog.ui.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class PostCreateUpdateModel
{
    private String title;
    private String body;
}
