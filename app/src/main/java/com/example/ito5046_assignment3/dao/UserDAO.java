package com.example.ito5046_assignment3.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.ito5046_assignment3.entity.Customer;
import com.example.ito5046_assignment3.entity.User;

import java.util.List;

@Dao
public interface UserDAO {
    @Query("SELECT * FROM user ORDER BY id ASC")
    LiveData<List<User>> getAll();
    @Query("SELECT * FROM user WHERE id = :userId LIMIT 1")
    User findByID(int userId);
    @Query("SELECT * FROM user WHERE email = :email LIMIT 1")
    User findByUsername(String email);
    @Insert
    void insert(User user);
    @Delete
    void delete(User user);
    @Update
    void updateUser(User user);
    @Query("DELETE FROM user")
    void deleteAll();
}