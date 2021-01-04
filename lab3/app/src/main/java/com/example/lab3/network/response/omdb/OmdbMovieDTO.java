package com.example.lab3.network.response.omdb;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OmdbMovieDTO {

    @SerializedName("imdbID")
    private String imdbId;

    @SerializedName("Title")
    private String title;

    @SerializedName("Released")
    private Date releaseDate;

    @SerializedName("Genre")
    private String genres;

    @SerializedName("Actors")
    private String actors;

    @SerializedName("Plot")
    private String plot;

    @SerializedName("Director")
    private String director;

    @SerializedName("Poster")
    private String posterUrl;

    @SerializedName("Response")
    private boolean valid;

}
