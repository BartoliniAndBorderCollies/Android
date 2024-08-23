package com.klodnicki.taskmanager;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.klodnicki.taskmanager.data.entity.Status;
import com.klodnicki.taskmanager.data.entity.Tag;
import com.klodnicki.taskmanager.data.entity.Task;
import com.klodnicki.taskmanager.ui.viewmodel.TaskViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FormActivity extends AppCompatActivity {
    // MainActivity.java onCreate (addButton, taskListView.setOnClickItemListener (?))
    // FormActivity.java onCreate (saveButton)
    // TaskAdapter.java getView (editButton/deleteButton)

    // base class: AppCompactActivity
    // fields:
    // -> TaskViewModel, EditText, Spinner, ListView, Button, UUI, List<Tag>
    // methods:
    // -> onCreate:
    // tagsListView.setOnClickListener( /* TO DO */ )
    // saveButton.onClickListener(/* TO DO */ )

    private TaskViewModel taskViewModel;
    private EditText nameEditText;
    private Spinner statusSpinner;
    private ListView tagListView;
    private Button saveButton;

    private UUID taskUUID;
    private Task task;
    private List<Tag> selectedTags;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        //initializing taskViewModel
        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);

        //initializing views
        nameEditText = findViewById(R.id.nameEditText);
        statusSpinner = findViewById(R.id.statusSpinner);
        TextView tagLabel = findViewById(R.id.tagLabel);
        tagListView = findViewById(R.id.tagListView);
        saveButton = findViewById(R.id.saveButton);

        selectedTags = new ArrayList<>();

        // Check if there's a taskId passed from MainActivity
        if (getIntent().hasExtra("idTask")) {
            String taskIdString = getIntent().getStringExtra("idTask");
            taskUUID = UUID.fromString(taskIdString);

            task = taskViewModel.getTaskById(taskUUID);

            //update task if exists
            //on w tym momencie pobiera dane z modelu i wypelnia obraz tymi danymi taska, które obecnie istnieje
            if (task != null) {
                nameEditText.setText(task.getName());

                List<Status> allStatuses = taskViewModel.getAllStatuses();
                for (int i = 0; i < allStatuses.size(); i++) {
                    statusSpinner.setSelection(i);
                }

                selectedTags.addAll(task.getTags());
                for (int i = 0; i < tagListView.getCount(); i++) {//tutaj wybieram zaznaczone tagi
                    Tag tag = (Tag) tagListView.getItemAtPosition(i);//ustalasz ich pozycje
                    if(selectedTags.contains(tag))
                        tagListView.setItemChecked(i, true);
                }
            }
        }



    }


}
