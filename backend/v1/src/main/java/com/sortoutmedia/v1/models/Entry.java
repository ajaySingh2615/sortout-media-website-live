package com.sortoutmedia.v1.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "entries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Entry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String category;
    private int followers;
    private int age;
    private String language;
    private String gender;
    private String profession;
    private String imageUrl;
}