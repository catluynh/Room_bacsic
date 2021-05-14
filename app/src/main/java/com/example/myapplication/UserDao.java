package com.example.myapplication;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM User")
    List<User> getAll();

    @Query("SELECT * FROM User WHERE id = :userId ")
    User getById(Integer userId);

    @Insert
    void addUser(User User);

    @Delete
    void deleteUser(User User);

}
