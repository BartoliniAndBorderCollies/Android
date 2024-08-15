package com.klodnicki.taskmanager.data;

import com.klodnicki.taskmanager.data.dao.StatusDao;
import com.klodnicki.taskmanager.data.dao.TagDao;
import com.klodnicki.taskmanager.data.dao.TaskDao;
import com.klodnicki.taskmanager.data.entity.Status;
import com.klodnicki.taskmanager.data.entity.Tag;
import com.klodnicki.taskmanager.data.entity.Task;

import java.util.List;
import java.util.UUID;

import kotlinx.coroutines.flow.Flow;

public class TaskRepository {

    private final StatusDao statusDao;

    private final TagDao tagDao;

    private final TaskDao taskDao;

    private final Flow<List<Task>> allTasks;

    public TaskRepository(StatusDao statusDao, TagDao tagDao, TaskDao taskDao) {
        this.statusDao = statusDao;
        this.tagDao = tagDao;
        this.taskDao = taskDao;
        allTasks = taskDao.findAll();
    }

    public Flow<List<Task>> getAllTasks() {
        return allTasks;
    }

    void insert(Task task) {
        taskDao.create(task);
    }

    void update(Task task) {
        taskDao.update(task);
    }

    void delete(Task task) {
        taskDao.delete(task);
    }

    Task getTaskById(UUID id) {
        return taskDao.findById(id);
    }

    List<Tag> getTagsForTask(UUID taskId) {
        return getTaskById(taskId).getTags();
    }

    Status getStatusById(UUID id) {
        return statusDao.findById(id);
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
