package com.example.ito5046_assignment3.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;

@Entity
public class Attempt {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int attempt_id;
    @ColumnInfo(name = "user_id")
    @NonNull
    public int userId;
    @ColumnInfo(name = "challenge_id")
    @NonNull
    public int challengeId;
    @ColumnInfo(name = "date_attempted")
    @NonNull
    public long dateAttempted;
    @ColumnInfo(name = "attempt_status")
    @NonNull
    public int attemptStatus;

    public Attempt( int userId, int challengeId, long dateAttempted, int attemptStatus) {
        this.userId = userId;
        this.challengeId = challengeId;
        this.dateAttempted = dateAttempted;
        this.attemptStatus = attemptStatus;
    }
}