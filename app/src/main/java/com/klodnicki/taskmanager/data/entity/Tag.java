package com.klodnicki.taskmanager.data.entity;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity(tableName = "tag_table")
public class Tag {
    @PrimaryKey
    @NonNull
    private UUID id;
    private String name;
}
