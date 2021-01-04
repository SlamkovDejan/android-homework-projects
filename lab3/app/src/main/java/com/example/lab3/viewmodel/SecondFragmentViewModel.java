package com.example.lab3.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.lab3.Lab3App;
import com.example.lab3.db.entity.Movie;
import com.example.lab3.repository.MovieRepository;

import java.util.List;

public class SecondFragmentViewModel extends AndroidViewModel {

    private MovieRepository movieRepository;

    public SecondFragmentViewModel(@NonNull Application application) {
        super(application);
        this.movieRepository = ((Lab3App) application).movieRepository();
    }

    public LiveData<Movie> getMovieById(String id){
        return this.movieRepository.findMovieById(id);
    }

    public LiveData<List<String>> getActorsOfMovie(String id){
        return this.movieRepository.getActorsOfMovie(id);
    }

    public LiveData<List<String>> getGenresOfMovie(String id){
        return this.movieRepository.getGenresOfMovie(id);
    }

}
