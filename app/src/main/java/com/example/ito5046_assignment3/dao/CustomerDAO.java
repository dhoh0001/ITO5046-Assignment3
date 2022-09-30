package com.example.ito5046_assignment3.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.ito5046_assignment3.entity.Customer;

import java.util.List;

@Dao
public interface CustomerDAO {
    @Query("SELECT * FROM customer ORDER BY last_name ASC")
    LiveData<List<Customer>> getAll();
    @Query("SELECT * FROM customer WHERE uid = :customerId LIMIT 1")
    Customer findByID(int customerId);
    @Insert
    void insert(Customer customer);
    @Delete
    void delete(Customer customer);
    @Update
    void updateCustomer(Customer customer);
    @Query("DELETE FROM customer")
    void deleteAll();
}