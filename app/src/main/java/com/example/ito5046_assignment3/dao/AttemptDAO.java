package com.example.ito5046_assignment3.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.ito5046_assignment3.entity.Customer;
import com.example.ito5046_assignment3.entity.Attempt;

import java.util.List;

@Dao
public interface AttemptDAO {
    @Query("SELECT * FROM Attempt ORDER BY id ASC")
    LiveData<List<Attempt>> getAll();
    @Query("SELECT * FROM Attempt WHERE id = :AttemptId LIMIT 1")
    Attempt findByID(int AttemptId);
    @Query("SELECT * FROM Attempt where date_attempted > :date1 and date_attempted < :date2 ORDER BY id ASC")
    LiveData<List<Attempt>> getInDateRange(long date1, long date2);
    @Insert
    void insert(Attempt Attempt);
    @Delete
    void delete(Attempt Attempt);
    @Update
    void updateAttempt(Attempt Attempt);
    @Query("DELETE FROM Attempt")
    void deleteAll();
}