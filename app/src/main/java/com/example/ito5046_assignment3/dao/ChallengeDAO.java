package com.example.ito5046_assignment3.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.ito5046_assignment3.entity.Customer;
import com.example.ito5046_assignment3.entity.Challenge;

import java.util.List;

@Dao
public interface ChallengeDAO {
    @Query("SELECT * FROM Challenge ORDER BY id ASC")
    LiveData<List<Challenge>> getAll();
    @Query("SELECT * FROM Challenge WHERE id = :ChallengeId LIMIT 1")
    Challenge findByID(int ChallengeId);
    @Insert
    void insert(Challenge Challenge);
    @Delete
    void delete(Challenge Challenge);
    @Update
    void updateChallenge(Challenge Challenge);
    @Query("DELETE FROM Challenge")
    void deleteAll();
}