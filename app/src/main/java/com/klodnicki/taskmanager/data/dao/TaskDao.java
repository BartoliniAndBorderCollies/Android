package com.klodnicki.taskmanager.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.klodnicki.taskmanager.data.entity.Task;

import java.util.List;
import java.util.UUID;

import kotlinx.coroutines.flow.Flow;

@Dao
public interface TaskDao {
    @Query("SELECT * FROM task_table")
    Flow<List<Task>> findAll();

    @Query("SELECT * FROM task_table WHERE id = :id")
    Task findById(UUID id);

    @Insert
    void create(Task task);

    @Update
    void update(Task updatedTask);

    @Delete
    void delete(Task task);
}
