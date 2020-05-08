package com.pam.blog.ui.controller;

import com.pam.blog.service.PostService;
import com.pam.blog.service.UserService;
import com.pam.blog.shared.dto.PostDto;
import com.pam.blog.shared.dto.UserDto;
import com.pam.blog.ui.model.request.PostCreateUpdateModel;
import com.pam.blog.ui.model.response.PostRest;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("posts")
public class PostController
{
    private final PostService postService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public PostController(PostService postService, UserService userService)
    {
        this.postService = postService;
        this.userService = userService;
        this.modelMapper = new ModelMapper();
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<PostRest> index()
    {
        List<PostDto> postsFromDb = this.postService.index();
        List<PostRest> posts = new ArrayList<>();

        for(PostDto post : postsFromDb)
        {
            posts.add(this.modelMapper.map(post, PostRest.class));
        }

        return posts;
    }

    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public PostRest show(@PathVariable long id)
    {
        PostDto postFromDb = this.postService.show(id);
        return this.modelMapper.map(postFromDb, PostRest.class);
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public PostRest store(@RequestBody PostCreateUpdateModel post, Authentication authentication)
    {
        String userEmail = authentication.getName();
        UserDto authUser = this.userService.getUser(userEmail);
        PostDto objectToSave = this.modelMapper.map(post, PostDto.class);
        objectToSave.setUser(authUser);
        PostDto savedPost = this.postService.store(objectToSave);
        return this.modelMapper.map(savedPost, PostRest.class);
    }

    @PatchMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public PostRest update(@RequestBody PostCreateUpdateModel post, @PathVariable long id)
    {
        PostDto postChangeSet = this.modelMapper.map(post, PostDto.class);
        PostDto updatedPost = this.postService.update(id, postChangeSet);
        return this.modelMapper.map(updatedPost, PostRest.class);
    }

    @DeleteMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public PostRest destroy(@PathVariable long id)
    {
        PostDto postFromDb = this.postService.destroy(id);
        return this.modelMapper.map(postFromDb, PostRest.class);
    }
}
