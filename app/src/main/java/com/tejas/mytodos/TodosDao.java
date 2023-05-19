package com.tejas.mytodos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TodosDao {
    @Query("select * from todos")
    List<Todos> getTodos();

    @Insert
    void addTodo(Todos todos);

    @Update
    void updateTodo(Todos todos);

    @Delete
    void deleteTodo(Todos todos);
}
