package com.klodnicki.taskmanager.data.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity(tableName = "status_table")
public class Status {
    @PrimaryKey
    @NonNull
    private UUID id;

    private String name;

    @NonNull
    public UUID getId() {
        return id;
    }
}
