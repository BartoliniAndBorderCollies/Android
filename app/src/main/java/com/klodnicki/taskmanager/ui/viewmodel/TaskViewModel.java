package com.klodnicki.taskmanager.ui.viewmodel;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.klodnicki.taskmanager.data.TaskDatabase;
import com.klodnicki.taskmanager.data.TaskRepository;
import com.klodnicki.taskmanager.data.entity.Status;
import com.klodnicki.taskmanager.data.entity.Tag;
import com.klodnicki.taskmanager.data.entity.Task;

import java.util.List;
import java.util.UUID;

import kotlinx.coroutines.flow.Flow;

public class TaskViewModel extends AndroidViewModel {
    private TaskRepository taskRepository;
    private Flow<List<Task>> allTasks;

    public Flow<List<Task>> getAllTasks() {
        return allTasks;
    }

    public TaskViewModel(@NonNull Application application) {
        super(application);
        TaskDatabase taskDatabase = TaskDatabase.getDatabase(application);
        taskRepository = new TaskRepository(taskDatabase.getStatusDao(), taskDatabase.getTagDao(),
                taskDatabase.getTaskDao());
    }

    public Flow<List<Task>> findAll() {
        return taskRepository.getAllTasks();
    }

    // insert
    public void create(Task task) {
        new InsertAsyncTask(taskRepository).execute(task);
    }

    // update
    public void update(Task task) {
        new UpdateAsyncTask(taskRepository).execute(task);
    }

    // delete
    public void delete(Task task) {
        new DeleteAsyncTask(taskRepository).execute(task);
    }

    // getTaskById
    public Task getTaskById(UUID id) {
        return taskRepository.getTaskById(id);
    }

    // getTagsForTask
    public List<Tag> getTagsForTask(UUID taskId) {
        return taskRepository.getTagsForTask(taskId);
    }

    // getStatusById
    public Status getStatusById(UUID statusId) {
        return taskRepository.getStatusById(statusId);
    }

    private static class UpdateAsyncTask extends AsyncTask<Task, Void, Void> {
        private final TaskRepository taskRepository;

        UpdateAsyncTask(TaskRepository taskRepository) {
            this.taskRepository = taskRepository;
        }

        @Override
        protected Void doInBackground(final Task... tasks) {
            taskRepository.update(tasks[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Task, Void, Void> {

        private final TaskRepository taskRepository;

        DeleteAsyncTask(TaskRepository taskRepository) {
            this.taskRepository = taskRepository;
        }

        @Override
        protected Void doInBackground(final Task... tasks) {
            taskRepository.delete(tasks[0]);
            return null;
        }
    }

    private static class InsertAsyncTask extends AsyncTask<Task, Void, Void> {

        private final TaskRepository taskRepository;

        InsertAsyncTask(TaskRepository taskRepository) {
            this.taskRepository = taskRepository;
        }

        @Override
        protected Void doInBackground(final Task... tasks) {
            taskRepository.insert(tasks[0]);
            return null;
        }
    }

    // UI zaimplementować UI (lista zadań + formularz Add/Update)
    // activity_main.xml
    // ListView, Button -> RelativeLayout - tutaj beda dwa buttony

    // activity_form.xml; - formularz
    // EditText (nameEditText), TextView (tagLabel), Spinner (statusSpinner), ListView (tagListView), Button (saveButton) -> LinearLayout

    // list_item_task.xml - podgląd pojedynczego taska - szczegóły widać jego
    // TextView (taskNameTextView), ImageButton (editButton -> android:src="@android:drawable/ic_menu_edit"), ImageButton (deleteButton -> android:src="@android:drawable/ic_menu_delete") -> LinearLayout


}