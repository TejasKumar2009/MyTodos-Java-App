package com.tejas.mytodos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class EditDeleteTodoActivity extends AppCompatActivity {
    EditText titleInput, descInput;
    Button delete_todo_btn, edit_todo_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_delete_todo);

        // Finding ids
        titleInput = findViewById(R.id.titleInput);
        descInput = findViewById(R.id.descInput);
        delete_todo_btn = findViewById(R.id.delete_todo_btn);
        edit_todo_btn = findViewById(R.id.edit_todo_btn);

        // Defining Database Variables
        TodosDatabase todosDatabase = TodosDatabase.getInstance(EditDeleteTodoActivity.this);
        ArrayList<Todos> todos = (ArrayList<Todos>) todosDatabase.todosDao().getTodos();

        // Getting data from intent
        Intent intent = getIntent();
        int todo_position = intent.getIntExtra("todo_position", -1);

        // Adding default values in TextView
        titleInput.setText(todos.get(todo_position).getTitle());
        descInput.setText(todos.get(todo_position).getDesc());

        delete_todo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                todosDatabase.todosDao().deleteTodo(todos.get(todo_position));
                Intent intent1 = new Intent(EditDeleteTodoActivity.this, MainActivity.class);
                intent1.putExtra("isRedirected", 2);
                startActivity(intent1);
            }
        });

        edit_todo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String editedTitle = titleInput.getText().toString();
                String editedDesc = descInput.getText().toString();

                if (editedTitle.length()>40)
                    Toast.makeText(EditDeleteTodoActivity.this, "Sorry, Title must be smaller than 40 letters!", Toast.LENGTH_SHORT).show();
                else if (editedDesc.length()>100)
                    Toast.makeText(EditDeleteTodoActivity.this, "Sorry, Description must be smaller than 100 letters!", Toast.LENGTH_SHORT).show();
                else{
                    Todos editedTodo = new Todos(todos.get(todo_position).getId(), editedTitle, editedDesc);
                    todosDatabase.todosDao().updateTodo(editedTodo);
                    Intent intent2 = new Intent(EditDeleteTodoActivity.this, MainActivity.class);
                    intent2.putExtra("isRedirected", 3);
                    startActivity(intent2);
                }
            }
        });

    }

    }