package com.PKH.Hub.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
 public class Notes {

   public enum Visibility { PUBLIC, PRIVATE }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "noteid")
    private long id;

    @NotBlank(message =  "Title should not be Empty")
    private String title;
    @Column(length = 1000)
    private String Description;
    private LocalDateTime CreatedAt;
    private LocalDateTime UpdatedAt;

   private Visibility visibility = Visibility.PRIVATE;

    @NotNull(message = "User Must be provided")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    private  User user;

   @ManyToMany(fetch = FetchType.LAZY)
   @JoinTable(
           name = "note_tags",
           joinColumns = @JoinColumn(name = "note_id"),
           inverseJoinColumns = @JoinColumn(name = "tag_id")
   )
   private Set<Tag> tags = new HashSet<>();
 }
