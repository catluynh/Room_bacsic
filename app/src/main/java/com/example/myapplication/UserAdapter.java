package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class UserAdapter extends BaseAdapter {
    private Context context;
    private List<User> users;
    private AppDatabase appDatabase;


    public UserAdapter(Context context, List<User> users,AppDatabase appDatabase) {
        this.context = context;
        this.users = users;
        this.appDatabase = appDatabase;
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int position) {
        return users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return users.get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        TextView tvName = view.findViewById(R.id.tvName);
        tvName.setText(users.get(position).getName());
        return view;
    }
}
