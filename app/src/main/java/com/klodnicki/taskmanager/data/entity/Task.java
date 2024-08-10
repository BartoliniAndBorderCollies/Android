package com.klodnicki.taskmanager.data.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

import java.util.UUID;

@Entity(tableName = "task_table", foreignKeys = @ForeignKey(entity = Status.class,
        parentColumns = "id",
        childColumns = "status_id",
        onDelete = ForeignKey.CASCADE), indices = {@Index(value = {"status_id"})})
public class Task {

    @NonNull
    @ColumnInfo(name = "status_id")
    private UUID statusId;

}
