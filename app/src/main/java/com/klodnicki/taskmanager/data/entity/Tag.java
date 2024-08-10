package com.klodnicki.taskmanager.data.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity(tableName = "tag_table", foreignKeys = @ForeignKey(entity = Task.class,
        parentColumns = "id",
        childColumns = "task_id",
        onDelete = ForeignKey.CASCADE), indices = {@Index(value = {"task_id"})})
public class Tag {

    @PrimaryKey
    @NonNull
    private UUID id;  // Primary Key for Tag entity

    @NonNull
    @ColumnInfo(name = "task_id")
    private UUID taskId;

}
