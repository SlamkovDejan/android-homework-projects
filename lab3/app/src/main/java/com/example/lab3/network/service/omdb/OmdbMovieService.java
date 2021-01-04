package com.example.lab3.network.service.omdb;

import com.example.lab3.network.response.omdb.OmdbMovieDTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

import java.util.Map;

public interface OmdbMovieService {

    @GET("/")
    Call<OmdbMovieDTO> search(@QueryMap Map<String, String> urlArgs);

}
