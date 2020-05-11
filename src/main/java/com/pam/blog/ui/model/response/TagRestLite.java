package com.pam.blog.ui.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class TagRestLite
{
    private long id;
    private String name;
    private String description;
    @JsonIgnore
    private List<PostRestLite> posts;
}
