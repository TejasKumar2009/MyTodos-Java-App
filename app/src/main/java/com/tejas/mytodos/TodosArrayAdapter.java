package com.tejas.mytodos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.util.ViewInfo;

import java.util.ArrayList;
import java.util.List;

public class TodosArrayAdapter extends ArrayAdapter<Todos> {
    public TodosArrayAdapter(@NonNull Context context, ArrayList<Todos> arrayList) {
        super(context, 0, arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View currentItemView = convertView;

        if (currentItemView==null){
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.custom_list_view, parent, false);
        }

        Todos currentTodoView = getItem(position);

        TextView titleTv = currentItemView.findViewById(R.id.titleTv);
        titleTv.setText(currentTodoView.getTitle());

        TextView descTv = currentItemView.findViewById(R.id.descTv);
        descTv.setText(currentTodoView.getDesc());


        return currentItemView;
    }
}
