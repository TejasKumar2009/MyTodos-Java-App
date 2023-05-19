package com.tejas.mytodos;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button add_btn;
    ListView todosListView;
    TextView no_todos_here_tv;
    LinearLayout mainLinearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Finding ids
        add_btn = findViewById(R.id.add_btn);
        todosListView = findViewById(R.id.todosListView);
        no_todos_here_tv = findViewById(R.id.no_todos_here_tv);
        mainLinearLayout = findViewById(R.id.mainLinearLayout);


        // Check that it is firstly opened activity or redirected from an another activity
        Intent intent = getIntent();
        int isRedirected = intent.getIntExtra("isRedirected", 0);
        if (isRedirected==1)
            Snackbar.make(mainLinearLayout, "Todo Added!", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
         else if (isRedirected==2)
            Snackbar.make(mainLinearLayout, "Todo Deleted!", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
         else if (isRedirected==3)
            Snackbar.make(mainLinearLayout, "Todo Edited!", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
         else{
            Toast.makeText(this, "It's 0 but why?", Toast.LENGTH_SHORT).show();
        }


        ArrayList<Todos> todos = (ArrayList<Todos>) TodosDatabase.getInstance(getApplicationContext()).todosDao().getTodos();
        TodosArrayAdapter todosArrayAdapter = new TodosArrayAdapter(this, todos);
        todosListView.setAdapter(todosArrayAdapter);
        
        // Checking that todos are empty or not
        if (todos.size()==0)
            no_todos_here_tv.setVisibility(View.VISIBLE);
        else
            no_todos_here_tv.setVisibility(View.GONE);
        

        // Adding Click Listener on Listview item
        todosListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, EditDeleteTodoActivity.class);
                intent.putExtra("todo_position", position);
                startActivity(intent);
            }
        });

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddTodoActivity.class);
                startActivity(intent);
            }
        });
    }
}