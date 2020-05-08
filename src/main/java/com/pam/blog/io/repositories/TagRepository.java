package com.pam.blog.io.repositories;

import com.pam.blog.io.entity.TagEntity;
import org.springframework.data.repository.CrudRepository;

public interface TagRepository extends CrudRepository<TagEntity, Long>
{
}
