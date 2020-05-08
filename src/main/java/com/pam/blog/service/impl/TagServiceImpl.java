package com.pam.blog.service.impl;

import com.pam.blog.io.entity.TagEntity;
import com.pam.blog.io.repositories.TagRepository;
import com.pam.blog.service.TagService;
import com.pam.blog.shared.dto.TagDto;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class TagServiceImpl implements TagService
{
    private final TagRepository tagRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository)
    {
        this.tagRepository = tagRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public List<TagDto> index()
    {
        Iterable<TagEntity> tagsFromDb = this.tagRepository.findAll();
        List<TagDto> tags = new ArrayList<>();

        for(TagEntity tag : tagsFromDb)
        {
            tags.add(this.modelMapper.map(tag, TagDto.class));
        }

        return tags;
    }

    @Override
    public TagDto store(TagDto tag)
    {
        TagEntity tagEntity = this.modelMapper.map(tag, TagEntity.class);
        TagEntity storedTag = this.tagRepository.save(tagEntity);
        return this.modelMapper.map(storedTag, TagDto.class);
    }
}
