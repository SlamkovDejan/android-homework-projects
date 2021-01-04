package com.example.lab3.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.*;
import com.example.lab3.db.entity.Movie;
import com.example.lab3.db.relationship.MovieActorCrossRef;
import com.example.lab3.db.relationship.MovieGenreCrossRef;

import java.util.List;

@Dao
public interface MovieDao {

    @Transaction
    @Query("SELECT * FROM movies")
    LiveData<List<Movie>> findAll();

    @Transaction
    @Query("SELECT * FROM movies WHERE movie_id = :id")
    LiveData<Movie> findById(String id);

    @Query("SELECT actor_name FROM movie_actors WHERE movie_id = :movieId")
    LiveData<List<String>> getActors(String movieId);

    @Query("SELECT genre_name FROM movie_genres WHERE movie_id = :movieId")
    LiveData<List<String>> getGenres(String movieId);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void save(Movie... movie);

    @Delete
    void delete(Movie... movies);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addActors(MovieActorCrossRef ...movieActors);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addGenres(MovieGenreCrossRef...movieGenres);

}
