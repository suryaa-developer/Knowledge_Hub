package com.PKH.Hub.repository;

import com.PKH.Hub.Entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagsRepo extends JpaRepository<Tag,Long> {
    boolean findByTagName(String TagName);
}
