package com.example.lab3.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import com.example.lab3.db.entity.Actor;

@Dao
public interface ActorDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void save(Actor actor);

}
