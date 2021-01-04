package com.example.lab3.db.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(tableName = "actors")
public class Actor {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "actor_name")
    private String name;

}
