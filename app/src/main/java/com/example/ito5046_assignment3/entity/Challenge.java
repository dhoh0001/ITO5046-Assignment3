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
    @ColumnInfo(name = "location_lattitude")
    @NonNull
    public double location_lattitude;
    @ColumnInfo(name = "location_longitude")
    @NonNull
    public double location_longitude;
    @ColumnInfo(name = "challenge_name")
    @NonNull
    public String challengeName;
    @ColumnInfo(name = "challenge_details")
    @NonNull
    public String challengeDetails;
    public Challenge(@NonNull double location_lattitude, @NonNull double location_longitude, @NonNull String challengeName
            , @NonNull String challengeDetails) {
        this.location_lattitude=location_lattitude;
        this.location_longitude=location_longitude;
        this.challengeName=challengeName;
        this.challengeDetails=challengeDetails;
    }
}