package com.example.lab3.db.relationship;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(primaryKeys = {"movie_id", "genre_name"}, tableName = "movie_genres")
public class MovieGenreCrossRef {

    @NonNull
    @ColumnInfo(name = "movie_id")
    private String movieId;

    @NonNull
    @ColumnInfo(name = "genre_name")
    private String genre;

}

