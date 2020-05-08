package com.pam.blog.ui.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class UserRestLite
{
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    @JsonIgnore
    private List<PostRestLite> posts;
}
