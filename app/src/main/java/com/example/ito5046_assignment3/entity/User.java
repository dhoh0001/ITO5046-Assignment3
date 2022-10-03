package com.example.ito5046_assignment3.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int user_id;
    @ColumnInfo(name = "first_name")
    @NonNull
    public String firstName;
    @ColumnInfo(name = "last_name")
    @NonNull
    public String lastName;
    public String password;
    public String salt;
    public String email;
    public String gender;
    public int age;
    public int fitnessLevel;
    public int numberChallengesCompleted;
    public User( @NonNull String firstName, @NonNull String lastName
                     , String password, String salt, String email
                     , String gender, int age, int fitnessLevel,
                 int numberChallengesCompleted) {
        this.firstName=firstName;
        this.lastName=lastName;
        this.password = password;
        this.salt = salt;
        this.email = email;
        this.gender = gender;
        this.age = age;
        this.fitnessLevel = fitnessLevel;
        this.numberChallengesCompleted = numberChallengesCompleted;
    }
}