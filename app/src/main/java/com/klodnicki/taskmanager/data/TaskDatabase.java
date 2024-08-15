package com.klodnicki.taskmanager.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.klodnicki.taskmanager.data.dao.StatusDao;
import com.klodnicki.taskmanager.data.dao.TagDao;
import com.klodnicki.taskmanager.data.dao.TaskDao;
import com.klodnicki.taskmanager.data.entity.Status;
import com.klodnicki.taskmanager.data.entity.Tag;
import com.klodnicki.taskmanager.data.entity.Task;

@Database(entities = {Status.class, Tag.class, Task.class}, version = 1)
public abstract class TaskDatabase extends RoomDatabase {
    public StatusDao statusDao;
    public TagDao tagDao;
    public TaskDao taskDao;

    private static volatile TaskDatabase INSTANCE;

    public static TaskDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TaskDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TaskDatabase.class, "task_database").build();
                }
            }
        }
        return INSTANCE;
    }

    public StatusDao getStatusDao() {
        return statusDao;
    }

    public TagDao getTagDao() {
        return tagDao;
    }

    public TaskDao getTaskDao() {
        return taskDao;
    }
}
