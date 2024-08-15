package com.klodnicki.taskmanager.data.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import java.util.List;
import java.util.UUID;

@Entity(tableName = "task_table",
        foreignKeys = @ForeignKey(entity = Status.class,
            parentColumns = "id",
            childColumns = "status_id",
            onDelete = ForeignKey.CASCADE),
        indices = {@Index(value = {"status_id"})})
public class Task {
    @PrimaryKey
    @NonNull
    private UUID id;
    private String name;
    @ColumnInfo(name = "status_id")
    @NonNull
    private UUID statusId;
    @Ignore
    @Relation(parentColumn = "id", entityColumn = "task_id")
    private List<Tag> tags;

    public List<Tag> getTags() {
        return tags;
    }
}
