package com.example.lab3;

import android.app.Application;
import com.example.lab3.db.AppDatabase;
import com.example.lab3.network.OmdbApi;
import com.example.lab3.network.service.omdb.OmdbMovieService;
import com.example.lab3.repository.MovieRepository;


public class Lab3App extends Application {

    public OmdbMovieService omdbMovieService(){
        return OmdbApi.createService(OmdbMovieService.class);
    }

    public MovieRepository movieRepository(){
        return MovieRepository.getInstance(omdbMovieService(), appDatabase());
    }

    public AppDatabase appDatabase(){
        return AppDatabase.getInstance(this);
    }

    public void nukeAppDatabase(){
        new Thread(() -> appDatabase().clearAllTables()).start();
    }

}
