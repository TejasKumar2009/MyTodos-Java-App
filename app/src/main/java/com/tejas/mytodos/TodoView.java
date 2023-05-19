package com.tejas.mytodos;

public class TodoView {
    private String todoTitle;
    private String todoDesc;

    TodoView(String todoTitle, String todoDesc){
        this.todoTitle = todoTitle;
        this.todoDesc = todoDesc;
    }

    public String getTodoTitle() {
        return todoTitle;
    }

    public String getTodoDesc() {
        return todoDesc;
    }
}
