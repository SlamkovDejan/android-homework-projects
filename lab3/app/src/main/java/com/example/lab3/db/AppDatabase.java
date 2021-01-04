package com.example.lab3.db;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import com.example.lab3.db.converters.DateConverters;
import com.example.lab3.db.dao.ActorDao;
import com.example.lab3.db.dao.GenreDao;
import com.example.lab3.db.dao.MovieDao;
import com.example.lab3.db.entity.Actor;
import com.example.lab3.db.entity.Genre;
import com.example.lab3.db.entity.Movie;
import com.example.lab3.db.relationship.MovieActorCrossRef;
import com.example.lab3.db.relationship.MovieGenreCrossRef;

@TypeConverters({DateConverters.class})
@Database(
        entities = {Movie.class, Actor.class, Genre.class, MovieActorCrossRef.class, MovieGenreCrossRef.class},
        version = 1,
        exportSchema = false
)
public abstract class AppDatabase extends RoomDatabase {

    public abstract MovieDao movieDao();
    public abstract GenreDao genreDao();
    public abstract ActorDao actorDao();

    private static AppDatabase instance;

    public static AppDatabase getInstance(final Context application) {
        if(instance == null){
            synchronized (AppDatabase.class){
                if(instance == null){
                    instance = Room.databaseBuilder(application, AppDatabase.class, "movies.db").build();
                }
            }
        }
        return instance;
    }

}
