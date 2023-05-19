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

public class AddTodoActivity extends AppCompatActivity {
    Button add_todo_btn;
    EditText titleInput, descInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        // Finding ids
        add_todo_btn = findViewById(R.id.add_todo_btn);
        titleInput = findViewById(R.id.titleInput);
        descInput = findViewById(R.id.descInput);
        
        add_todo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title_str = titleInput.getText().toString();
                String desc_str = descInput.getText().toString();
                
                if (title_str.length()>30)
                    Toast.makeText(AddTodoActivity.this, "Sorry, Title must be smaller than 30 letters!", Toast.LENGTH_SHORT).show();
                 else if (desc_str.length()>100)
                    Toast.makeText(AddTodoActivity.this, "Sorry, Description must be smaller than 100 letters!", Toast.LENGTH_SHORT).show();
                 else{
                    Todos myTodo = new Todos(title_str, desc_str);
                    TodosDatabase todosDatabase = TodosDatabase.getInstance(AddTodoActivity.this);
                    todosDatabase.todosDao().addTodo(myTodo);
                    Intent intent = new Intent(AddTodoActivity.this, MainActivity.class);
                    intent.putExtra("isRedirected", 1);
                    startActivity(intent);
                }
            }
        });

    }
}