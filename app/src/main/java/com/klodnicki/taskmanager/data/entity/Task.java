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

    public Task(@NonNull UUID id, String name, @NonNull UUID statusId, List<Tag> tags) {
        this.id = id;
        this.name = name;
        this.statusId = statusId;
        this.tags = tags;
    }
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

    @NonNull
    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @NonNull
    public UUID getStatusId() {
        return statusId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatusId(@NonNull UUID statusId) {
        this.statusId = statusId;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
