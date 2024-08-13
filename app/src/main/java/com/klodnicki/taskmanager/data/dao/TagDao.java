package com.klodnicki.taskmanager.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.klodnicki.taskmanager.data.entity.Tag;

import java.util.List;
import java.util.UUID;

import kotlinx.coroutines.flow.Flow;

@Dao
public interface TagDao {

    @Query("SELECT * FROM tag_table")
    Flow<List<Tag>> findAll();

    @Query("SELECT * FROM task_table WHERE id = :id")
    Tag findById(UUID id);

    @Insert
    void create(Tag tag);

    @Update
    void update(Tag updatedTag);

    @Delete
    void delete(Tag tag);

}
