package com.pam.blog.ui.controller;

import com.pam.blog.service.TagService;
import com.pam.blog.shared.dto.TagDto;
import com.pam.blog.ui.model.request.TagCreateUpdateModel;
import com.pam.blog.ui.model.response.TagRest;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("tags")
public class TagController
{
    private final TagService tagService;
    private final ModelMapper modelMapper;

    @Autowired
    public TagController(TagService tagService)
    {
        this.tagService = tagService;
        this.modelMapper = new ModelMapper();
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<TagRest> index()
    {
        List<TagDto> tagsFromDb = this.tagService.index();
        List<TagRest> tags = new ArrayList<>();

        for(TagDto tag : tagsFromDb)
        {
            tags.add(this.modelMapper.map(tag, TagRest.class));
        }
        return tags;
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public TagRest store(@RequestBody TagCreateUpdateModel tag)
    {
        TagDto tagDto = this.modelMapper.map(tag, TagDto.class);
        TagDto storedTag = this.tagService.store(tagDto);
        return this.modelMapper.map(storedTag, TagRest.class);
    }
}
