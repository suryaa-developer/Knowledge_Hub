package com.PKH.Hub.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;

@Entity
@Data
 public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "noteid")
    private int id;

    @NotBlank(message =  "Title should not be Empty")
    private String title;
    @Column(length = 1000)
    private String Description;
    private LocalDateTime CreatedAt;
    private LocalDateTime UpdatedAt;

    @NotNull(message = "User Must be provided")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    private  User user;
 }
