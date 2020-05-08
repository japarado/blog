package com.pam.blog.ui.model.request;

import lombok.Data;

@Data
public class UserDetailsRequestModel
{
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @Override
    public String toString()
    {
        return "UserDetailsRequestModel{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
