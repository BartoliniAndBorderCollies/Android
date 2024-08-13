package com.klodnicki.taskmanager.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.klodnicki.taskmanager.data.entity.Status;

import java.util.List;
import java.util.UUID;

import kotlinx.coroutines.flow.Flow;

@Dao
public interface StatusDao {

    @Query("SELECT * FROM status_table")
    Flow<List<Status>> findAll();

    @Query("SELECT * FROM status_table WHERE id = :id")
    Status findById(UUID id);

    @Insert
    void create(Status status);

    @Update
    void update(Status status);

    @Delete
    void delete(Status status);


}
