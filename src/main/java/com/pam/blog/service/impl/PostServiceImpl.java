package com.pam.blog.service.impl;

import com.pam.blog.exceptions.PostNotFoundException;
import com.pam.blog.exceptions.PostServiceException;
import com.pam.blog.io.entity.PostEntity;
import com.pam.blog.io.repositories.PostRepository;
import com.pam.blog.io.repositories.UserRepository;
import com.pam.blog.service.PostService;
import com.pam.blog.shared.dto.PostDto;
import com.pam.blog.ui.model.response.ErrorMessages;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PostServiceImpl implements PostService
{
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PostServiceImpl(PostRepository postRepository)
    {
        this.postRepository = postRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public PostDto store(PostDto post)
    {
        ModelMapper modelMapper = new ModelMapper();
        PostEntity postEntity = modelMapper.map(post, PostEntity.class);
        PostEntity storedPost = this.postRepository.save(postEntity);
        return modelMapper.map(storedPost, PostDto.class);
    }


    @Override
    public List<PostDto> index()
    {
        Iterable<PostEntity> posts = this.postRepository.findAll();
        ModelMapper modelMapper = new ModelMapper();

        List<PostDto> returnValue = new ArrayList<>();

        for(PostEntity post : posts)
        {
            returnValue.add(modelMapper.map(post, PostDto.class));
        }

        return returnValue;
    }

    @Override
    public PostDto update(long id, PostDto post) throws PostNotFoundException
    {
        Optional<PostEntity> optionalPost = this.postRepository.findById(id);

        if(optionalPost.isPresent())
        {
            PostEntity postFromDb = optionalPost.get();
            postFromDb.setTitle(post.getTitle());
            postFromDb.setBody(post.getBody());
            PostEntity updatedPost = this.postRepository.save(postFromDb);
            return this.modelMapper.map(updatedPost, PostDto.class);
        }
        else
        {
            throw new PostNotFoundException(id);
        }
    }

    @Override
    public PostDto destroy(long id) throws PostNotFoundException
    {
        Optional<PostEntity> optionalPost = this.postRepository.findById(id);

        if(optionalPost.isPresent())
        {
            PostEntity post = optionalPost.get();
            this.postRepository.delete(post);
            post.getUser().getPosts().remove(post);
            return this.modelMapper.map(post, PostDto.class);
        }
        else
        {
            throw new PostNotFoundException(id);
        }
    }

    @Override
    public PostDto show(long id)
    {
        Optional<PostEntity> optionalPost = this.postRepository.findById(id);
        if(optionalPost.isPresent())
        {
//            ModelMapper modelMapper = new ModelMapper();
//            return post.map(postEntity -> modelMapper.map(postEntity, PostDto.class)).orElse(null);
            PostEntity post = optionalPost.get();
            return modelMapper.map(post, PostDto.class);
        }
        else
        {
            throw new PostNotFoundException(id);
        }
    }
}
