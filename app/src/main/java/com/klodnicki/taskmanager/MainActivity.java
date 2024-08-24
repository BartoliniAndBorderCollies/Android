package com.klodnicki.taskmanager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.klodnicki.taskmanager.data.entity.Task;
import com.klodnicki.taskmanager.ui.viewmodel.TaskViewModel;

public class MainActivity extends AppCompatActivity {

    private TaskViewModel taskViewModel;
    private ListView taskListView;
    private Button addButton;
    private ArrayAdapter<Task> taskArrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializing the ViewModel
        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);

        //initializing views
        taskListView = findViewById(R.id.taskListView);
        addButton = findViewById(R.id.addButton);


//TODO: taskViewModel.getAllTasks().observe


        //set on click listener on addButton
        addButton.setOnClickListener(v -> {
            //add logic
            Intent intent = new Intent(MainActivity.this, FormActivity.class);
            startActivity(intent);
        });

        //set on item click listener on taskListView
        taskListView.setOnItemClickListener((adapterView, view, position, id) -> {
            //przy pomocy adaptera pobrać zaznaczony obiekt typu Task przy pomocy getITem
            Task task = taskArrayAdapter.getItem(position); //jest to zaznaczony obiekt typy Task

            // przejsc do FormActivity
            Intent intent = new Intent(MainActivity.this, FormActivity.class);
            startActivity(intent);

            // dodać do intent parametr 'extra' ktory bedzie przechowywal id zaznaczonego obiektu
            // Passing the UUID as a Serializable
            intent.putExtra("idTask", task.getId().toString());

            // wystartowac activity
            startActivity(intent);

        });


    }
}
