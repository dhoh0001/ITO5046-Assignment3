package com.example.ito5046_assignment3.repository;

import android.app.Application;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;

import com.example.ito5046_assignment3.dao.AttemptDAO;
import com.example.ito5046_assignment3.database.AttemptDatabase;
import com.example.ito5046_assignment3.entity.Attempt;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class AttemptRepository {
    private AttemptDAO AttemptDao;
    private LiveData<List<Attempt>> allAttempts;
    public AttemptRepository(Application application){
        AttemptDatabase db = AttemptDatabase.getInstance(application);
        AttemptDao =db.AttemptDao();
        allAttempts= AttemptDao.getAll();
    }
    // Room executes this query on a separate thread
    public LiveData<List<Attempt>> getAllAttempts() {
        return allAttempts;
    }
    public void insert(final Attempt Attempt){
        AttemptDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                AttemptDao.insert(Attempt);
            }
        });
    }
    public void deleteAll(){
        AttemptDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                AttemptDao.deleteAll();
            }
        });
    }
    public void delete(final Attempt Attempt){
        AttemptDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                AttemptDao.delete(Attempt);
            }
        });
    }
    public void updateAttempt(final Attempt Attempt){
        AttemptDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                AttemptDao.updateAttempt(Attempt);
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public CompletableFuture<Attempt> findByIDFuture(final int AttemptId) {
        return CompletableFuture.supplyAsync(new Supplier<Attempt>() {
            @Override
            public Attempt get() {
                return AttemptDao.findByID(AttemptId);
            }
        }, AttemptDatabase.databaseWriteExecutor);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public CompletableFuture<LiveData<List<Attempt>>> getInDateRange(final long date1, final long date2) {
        return CompletableFuture.supplyAsync(new Supplier<LiveData<List<Attempt>>>() {
            @Override
            public LiveData<List<Attempt>> get() {
                return AttemptDao.getInDateRange(date1, date2);
            }
        }, AttemptDatabase.databaseWriteExecutor);
    }
}