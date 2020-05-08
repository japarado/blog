package com.pam.blog.ui.controller;

import com.pam.blog.service.UserService;
import com.pam.blog.shared.dto.UserDto;
import com.pam.blog.ui.model.request.UserDetailsRequestModel;
import com.pam.blog.ui.model.response.UserRest;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.util.UserDataDocumentFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("users")
public class UserController
{
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService)
    {
        this.userService = userService;
        this.modelMapper = new ModelMapper();
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<UserRest> index()
    {
        List<UserDto> usersFromDb = this.userService.index();
        List<UserRest> users = new ArrayList<>();
        for(UserDto user : usersFromDb)
        {
            users.add(this.modelMapper.map(user, UserRest.class));
        }

        return users;
    }

    @GetMapping(path = "/{id}")
    public UserRest getUser(@PathVariable String id)
    {
        UserDto userDto = userService.getUserByUserId(id);
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(userDto, UserRest.class);
    }

    @PostMapping
    public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails)
    {
        UserRest returnValue = new UserRest();

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetails, userDto);

        UserDto createdUser = userService.createUser(userDto);
        BeanUtils.copyProperties(createdUser, returnValue);

        return returnValue;
    }

    @PutMapping
    public String updateUser()
    {
        return "update user was called";
    }

    @DeleteMapping
    public String deleteUser()
    {
        return "delete user was called";
    }
}
