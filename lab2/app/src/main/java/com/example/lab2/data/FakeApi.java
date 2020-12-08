package com.example.lab2.data;

import com.example.lab2.data.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class FakeApi {

    private static FakeApi instance;
    private List<Movie> movies;

    private FakeApi(){
        this.movies = new ArrayList<>();
    }

    public static FakeApi getInstance() {
        if(instance == null){
            instance = new FakeApi();
            init();
        }
        return instance;
    }

    private static void init(){
        String[] actors = actors();
        instance.addMovie(
                new Movie(0, "Movie 0", "Movie about 0", actors[0], new String[]{actors[5], actors[1], actors[2]})
        );
        instance.addMovie(
                new Movie(1, "Movie 1", "Movie about 1", actors[1], new String[]{actors[11], actors[12], actors[2]})
        );
        instance.addMovie(
                new Movie(2, "Movie 2", "Movie about 2", actors[2], new String[]{actors[6], actors[5], actors[12]})
        );
        instance.addMovie(
                new Movie(3, "Movie 3", "Movie about 3", actors[3], new String[]{actors[5], actors[9], actors[7]})
        );
        instance.addMovie(
                new Movie(4, "Movie 4", "Movie about 4", actors[4], new String[]{actors[8], actors[10], actors[11]})
        );
        instance.addMovie(
                new Movie(5, "Movie 5", "Movie about 5", actors[5], new String[]{actors[4], actors[6], actors[7]})
        );
        instance.addMovie(
                new Movie(6, "Movie 6", "Movie about 6", actors[6], new String[]{actors[13], actors[0], actors[3]})
        );
        instance.addMovie(
                new Movie(7, "Movie 7", "Movie about 7", actors[7], new String[]{actors[0], actors[1], actors[2]})
        );
        instance.addMovie(
                new Movie(8, "Movie 8", "Movie about 8", actors[8], new String[]{actors[3], actors[4], actors[5]})
        );
        instance.addMovie(
                new Movie(9, "Movie 9", "Movie about 9", actors[9], new String[]{actors[6], actors[7], actors[9]})
        );
        instance.addMovie(
                new Movie(10, "Movie 10", "Movie about 10", actors[10], new String[]{actors[8], actors[10], actors[11]})
        );
        instance.addMovie(
                new Movie(11, "Movie 11", "Movie about 11", actors[11], new String[]{actors[12], actors[13], actors[2]})
        );
        instance.addMovie(
                new Movie(12, "Movie 12", "Movie about 12", actors[12], new String[]{actors[5], actors[8], actors[9]})
        );
        instance.addMovie(
                new Movie(13, "Movie 13", "Movie about 13", actors[13], new String[]{actors[11], actors[12], actors[10]})
        );
    }

    private static String[] actors(){
        return new String[]{
                "Dejan Slamkov", "Venko Stojanov", "Borce Stevanoski", "Pance Kotev", "Brucosh B", "Sara Dzekova",
                "Dragan Petkov", "Igor Miskovski", "Dimitar Kitanovski", "Frosina Stojanovska", "Martina Tosevska",
                "Dua Lipa", "Miley Cyrus", "Alex Turner"
        };
    }

    public void addMovie(Movie movie){
        this.movies.add(movie);
    }

    public Movie getMovie(long index){
        return movies.get((int) index);
    }

    public int count(){
        return this.movies.size();
    }

}
