package com.example.lab3.db.entity;

import android.icu.text.DateFormat;
import androidx.annotation.NonNull;
import androidx.room.*;
import lombok.*;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(tableName = "movies")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Movie {

    @NonNull
    @PrimaryKey
    @EqualsAndHashCode.Include
    @ColumnInfo(name = "movie_id")
    private String id;

    @ColumnInfo(name = "movie_title")
    private String title;

    @ColumnInfo(name = "movie_release_date")
    private Date releaseDate;

    @ColumnInfo(name = "movie_plot")
    private String plot;

    @ColumnInfo(name = "movie_director")
    private String director;

    @ColumnInfo(name = "movie_poster_url")
    private String posterUrl;

    public String getFormattedDate(String pattern){
        SimpleDateFormat formatter = new SimpleDateFormat(pattern, Locale.getDefault());
        return formatter.format(this.releaseDate);
    }

}
