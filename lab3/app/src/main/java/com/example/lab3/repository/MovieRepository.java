package com.example.lab3.repository;

import android.content.Context;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import com.example.lab3.db.AppDatabase;
import com.example.lab3.db.entity.Actor;
import com.example.lab3.db.entity.Genre;
import com.example.lab3.db.entity.Movie;
import com.example.lab3.db.relationship.MovieActorCrossRef;
import com.example.lab3.db.relationship.MovieGenreCrossRef;
import com.example.lab3.network.OmdbApi;
import com.example.lab3.network.response.omdb.OmdbMovieDTO;
import com.example.lab3.network.service.omdb.OmdbMovieService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MovieRepository {

    private final OmdbMovieService omdbMovieService;
    private final AppDatabase appDatabase;
    private final Executor worker;

    private MovieRepository(final OmdbMovieService omdbMovieService, AppDatabase appDatabase) {
        this.omdbMovieService = omdbMovieService;
        this.appDatabase = appDatabase;
        this.worker = Executors.newSingleThreadExecutor();
    }

    private static MovieRepository instance;

    public static MovieRepository getInstance(final OmdbMovieService omdbMovieService, final AppDatabase appDatabase) {
        if(instance == null){
            synchronized (MovieRepository.class){
                if (instance == null){
                    instance = new MovieRepository(omdbMovieService, appDatabase);
                }
            }
        }
        return instance;
    }

    public void searchAndSave(String title, @Nullable Integer year, Context toastContext){
        Map<String, String> urlParams = OmdbApi.getDefaultUrlParams();
        urlParams.put("t", title);
        if(year != null)
            urlParams.put("y", year.toString());

        this.omdbMovieService.search(urlParams).enqueue(new Callback<OmdbMovieDTO>() {
            @Override
            public void onResponse(Call<OmdbMovieDTO> call, Response<OmdbMovieDTO> response) {
                if(!response.body().isValid()){
                    Toast.makeText(toastContext, "The search result is not valid! Try again.", Toast.LENGTH_LONG).show();
                    return;
                }
                saveMovie(response.body());
            }

            @Override
            public void onFailure(Call<OmdbMovieDTO> call, Throwable t) {
                Toast.makeText(toastContext, "Network request error", Toast.LENGTH_LONG).show();
            }

        });
    }

    private void saveMovie(OmdbMovieDTO movieDTO){
        worker.execute(() -> {
            Movie newMovie = new Movie(
                    movieDTO.getImdbId(),
                    movieDTO.getTitle(),
                    movieDTO.getReleaseDate(),
                    movieDTO.getPlot(),
                    movieDTO.getDirector(),
                    movieDTO.getPosterUrl()
            );

            Arrays.stream(movieDTO.getActors().split(", "))
                    .map(Actor::new)
                    .forEach((actor) -> {
                        appDatabase.actorDao().save(actor);
                        appDatabase.movieDao().addActors(new MovieActorCrossRef(newMovie.getId(), actor.getName()));
                    });
            Arrays.stream(movieDTO.getGenres().split(", "))
                    .map(Genre::new)
                    .forEach((genre) -> {
                        appDatabase.genreDao().save(genre);
                        appDatabase.movieDao().addGenres(new MovieGenreCrossRef(newMovie.getId(), genre.getName()));
                    });
            appDatabase.movieDao().save(newMovie);
        });
    }

    public LiveData<Movie> findMovieById(String id){
        return this.appDatabase.movieDao().findById(id);
    }

    public LiveData<List<Movie>> findAll(){
        return this.appDatabase.movieDao().findAll();
    }

    public LiveData<List<String>> getActorsOfMovie(String movieId){
        return this.appDatabase.movieDao().getActors(movieId);
    }

    public LiveData<List<String>> getGenresOfMovie(String movieId){
        return this.appDatabase.movieDao().getGenres(movieId);
    }

    public void deleteMovieFromDatabase(Movie movie) {
        worker.execute(() -> {
            try {
                this.appDatabase.movieDao().delete(movie);
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        });
    }
}
