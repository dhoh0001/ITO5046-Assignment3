package com.example.ito5046_assignment3.repository;

import android.app.Application;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;

import com.example.ito5046_assignment3.dao.UserDAO;
import com.example.ito5046_assignment3.database.UserDatabase;
import com.example.ito5046_assignment3.entity.User;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class UserRepository {
    private UserDAO UserDao;
    private LiveData<List<User>> allUsers;
    public UserRepository(Application application){
        UserDatabase db = UserDatabase.getInstance(application);
        UserDao =db.UserDao();
        allUsers= UserDao.getAll();
    }
    // Room executes this query on a separate thread
    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }
    public void insert(final User User){
        UserDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                UserDao.insert(User);
            }
        });
    }
    public void deleteAll(){
        UserDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                UserDao.deleteAll();
            }
        });
    }
    public void delete(final User User){
        UserDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                UserDao.delete(User);
            }
        });
    }
    public void updateUser(final User User){
        UserDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                UserDao.updateUser(User);
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public CompletableFuture<User> findByIDFuture(final int UserId) {
        return CompletableFuture.supplyAsync(new Supplier<User>() {
            @Override
            public User get() {
                return UserDao.findByID(UserId);
            }
        }, UserDatabase.databaseWriteExecutor);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public CompletableFuture<User> findByUsername(final String email) {
        return CompletableFuture.supplyAsync(new Supplier<User>() {
            @Override
            public User get() {
                return UserDao.findByUsername(email);
            }
        }, UserDatabase.databaseWriteExecutor);
    }
}