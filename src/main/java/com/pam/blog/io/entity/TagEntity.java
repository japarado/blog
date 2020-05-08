package com.pam.blog.io.entity;

import lombok.Data;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity(name = "tags")
public class TagEntity implements Serializable
{
    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

//    @ManyToMany(mappedBy = "tags")
//    private List<PostEntity> posts;
}
