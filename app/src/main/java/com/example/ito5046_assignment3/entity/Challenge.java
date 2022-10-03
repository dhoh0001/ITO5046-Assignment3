package com.example.ito5046_assignment3.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Challenge {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int challenge_id;
    @ColumnInfo(name = "location")
    @NonNull
    public String location;
    @ColumnInfo(name = "challenge_name")
    @NonNull
    public String challengeName;
    @ColumnInfo(name = "challenge_details")
    @NonNull
    public String challengeDetails;
    public Challenge( @NonNull String location, @NonNull String challengeName
            , String challengeDetails) {
        this.location=location;
        this.challengeName=challengeName;
        this.challengeDetails=challengeDetails;
    }
}