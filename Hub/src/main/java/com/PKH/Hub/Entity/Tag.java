package com.PKH.Hub.Entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Tag_id")
    private long id;

    @Column(nullable = false,length = 50)
    private String TagName;
    @ManyToMany(mappedBy = "tags",fetch = FetchType.LAZY)
    private Set<Notes> Notes = new HashSet<>();
}
