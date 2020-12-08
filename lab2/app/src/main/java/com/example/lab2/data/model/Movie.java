package com.example.lab2.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class Movie {

    private long id;

    private String title;

    private String description;

    private String directorName;

    private String[] actorNames;

}
