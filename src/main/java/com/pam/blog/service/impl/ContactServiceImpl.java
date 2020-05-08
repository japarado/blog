package com.pam.blog.service.impl;

import com.pam.blog.exceptions.ContactNotFoundException;
import com.pam.blog.exceptions.DuplicateContactException;
import com.pam.blog.exceptions.UserNotFoundException;
import com.pam.blog.io.entity.UserEntity;
import com.pam.blog.io.repositories.UserRepository;
import com.pam.blog.service.ContactService;
import com.pam.blog.shared.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ContactServiceImpl implements ContactService
{
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public ContactServiceImpl(UserRepository userRepository)
    {
        this.userRepository = userRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public List<UserDto> index(String email)
    {
        UserEntity userEntity = this.userRepository.findByEmail(email);
        List<UserEntity> contacts = userEntity.getContacts();

        List<UserDto> returnValue = new ArrayList<>();
        for(UserEntity contact : contacts)
        {
            returnValue.add(this.modelMapper.map(contact, UserDto.class));
        }
        return returnValue;
    }

    @Override
    public UserDto store(String email, String userId) throws DuplicateContactException, UserNotFoundException
    {
        UserEntity authUser = this.userRepository.findByEmail(email);
        UserEntity contactToStore = this.userRepository.findByUserId(userId);

        if(contactToStore == null)
        {
            throw new UserNotFoundException(userId);
        }
        if(authUser.getContacts().contains(contactToStore))
        {
            System.out.println("Contains contact");
            throw new DuplicateContactException(contactToStore.getUserId());
        }
        else
        {
            authUser.getContacts().add(contactToStore);
            this.userRepository.save(authUser);
            return this.modelMapper.map(contactToStore, UserDto.class);
        }
    }

    @Override
    public UserDto destroy(String email, String userId) throws UserNotFoundException, ContactNotFoundException
    {
        UserEntity authUser = this.userRepository.findByEmail(email);
        UserEntity contactToRemove = this.userRepository.findByUserId(userId);

        if(contactToRemove == null)
        {
            throw new UserNotFoundException(userId);
        }
        if(!authUser.getContacts().contains(contactToRemove))
        {
            throw new ContactNotFoundException(contactToRemove.getUserId());
        }

        authUser.getContacts().remove(contactToRemove);
        this.userRepository.save(authUser);
        return this.modelMapper.map(contactToRemove, UserDto.class);
    }
}
