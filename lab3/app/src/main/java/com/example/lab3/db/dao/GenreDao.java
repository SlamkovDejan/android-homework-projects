package com.example.lab3.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import com.example.lab3.db.entity.Actor;
import com.example.lab3.db.entity.Genre;

@Dao
public interface GenreDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void save(Genre actor);

}
