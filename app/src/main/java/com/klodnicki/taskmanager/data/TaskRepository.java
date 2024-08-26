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

    public void insert(Task task) {
        taskDao.create(task);
    }

    public void saveOrUpdate(Task task) {
        if (task.getId() != null)
            taskDao.update(task);
        else
            insert(task);
    }

    public void delete(Task task) {
        taskDao.delete(task);
    }

    public Task getTaskById(UUID id) {
        return taskDao.findById(id);
    }

    public List<Tag> getTagsForTask(UUID taskId) {
        return getTaskById(taskId).getTags();
    }

    public Status getStatusById(UUID statusId) {
        return statusDao.findById(statusId);
    }
}
