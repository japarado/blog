package com.pam.blog.ui.controller;

import com.pam.blog.service.ContactService;
import com.pam.blog.shared.dto.UserDto;
import com.pam.blog.ui.model.response.UserRest;
import com.pam.blog.ui.model.response.UserRestLite;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.util.UserDataDocumentFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Slf4j
@RestController("contacts")
public class ContactController extends Controller
{
    private final ContactService contactService;
    private final ModelMapper modelMapper;

    @Autowired
    public ContactController(ContactService contactService)
    {
        this.contactService = contactService;
        this.modelMapper = new ModelMapper();
    }

    @GetMapping("/contacts")
    public List<UserRestLite> index(Authentication authentication)
    {
        String authUserEmail = authentication.getName();
        List<UserDto> contacts = this.contactService.index(authUserEmail);
        List<UserRestLite> returnValue = new ArrayList<>();

        for(UserDto contact : contacts)
        {
            returnValue.add(this.modelMapper.map(contact, UserRestLite.class));
        }
        return returnValue;
    }

    @PostMapping("/contacts/{id}")
    public UserRestLite store(@PathVariable String id, Authentication authentication)
    {
        String authUserEmail = authentication.getName();
        UserDto contact = this.contactService.store(authUserEmail, id);
        return this.modelMapper.map(contact, UserRestLite.class);
    }

    @DeleteMapping("/contacts/{id}")
    public UserRestLite destroy(@PathVariable String id, Authentication authentication)
    {
        String authUserEmail = authentication.getName();
        UserDto removedContact = this.contactService.destroy(authUserEmail, id);
        return this.modelMapper.map(removedContact, UserRestLite.class);
    }
}
