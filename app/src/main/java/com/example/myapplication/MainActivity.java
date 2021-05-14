package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button btnAdd, btnRemove, btnCancel;
    private TextView tvAdd;
    private List<User> listUser=new ArrayList<User>();;
    private UserAdapter userAdapter;
    private ListView listView;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd=findViewById(R.id.btnAdd);
        btnRemove=findViewById(R.id.btnRemove);
        btnCancel=findViewById(R.id.btnCancel);
        tvAdd=findViewById(R.id.tvAdd);
        listView=findViewById(R.id.listView);
        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "Userdb")
                .allowMainThreadQueries()
                .build();
        UserDao userDao = db.userDao();

//        userDao.addUser(new User("duonh luynh"));
//        userDao.addUser(new User("nguyen trung"));

        listUser = userDao.getAll();

        userAdapter=new UserAdapter(MainActivity.this, listUser, db);
        listView.setAdapter(userAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=tvAdd.getText().toString();
                userDao.addUser(new User(name));
                listUser=userDao.getAll();
                userAdapter=new UserAdapter(MainActivity.this, listUser, db);
                listView.setAdapter(userAdapter);
                tvAdd.setText("");
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                index = position;
            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userDao.deleteUser(listUser.get(index));
                listUser=userDao.getAll();
                userAdapter=new UserAdapter(MainActivity.this, listUser, db);
                listView.setAdapter(userAdapter);
                tvAdd.setText("");
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = 0;
                tvAdd.setText("");
            }
        });

    }

}