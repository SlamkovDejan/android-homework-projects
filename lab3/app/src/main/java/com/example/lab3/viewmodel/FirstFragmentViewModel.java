package com.example.lab3.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.lab3.Lab3App;
import com.example.lab3.db.entity.Movie;
import com.example.lab3.repository.MovieRepository;

import java.util.List;

public class FirstFragmentViewModel extends AndroidViewModel {

    private final MovieRepository movieRepository;

    private LiveData<List<Movie>> movies;

    public FirstFragmentViewModel(@NonNull Application application) {
        super(application);
        this.movieRepository = ((Lab3App) application).movieRepository();
        this.movies = this.movieRepository.findAll();
    }

    public LiveData<List<Movie>> getMovies(){
        return this.movies;
    }

    public void search(String title, Integer year){
        this.movieRepository.searchAndSave(title, year, getApplication());
    }

    public void search(String title){
        this.search(title, null);
    }


    public void deleteMovie(Movie movie) {
        this.movieRepository.deleteMovieFromDatabase(movie);
    }

    public LiveData<Movie> getMovieById(String id){
        return this.movieRepository.findMovieById(id);
    }
}
