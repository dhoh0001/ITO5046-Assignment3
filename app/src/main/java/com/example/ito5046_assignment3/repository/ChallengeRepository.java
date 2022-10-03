package com.example.ito5046_assignment3.repository;

import android.app.Application;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;

import com.example.ito5046_assignment3.dao.ChallengeDAO;
import com.example.ito5046_assignment3.database.ChallengeDatabase;
import com.example.ito5046_assignment3.entity.Challenge;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class ChallengeRepository {
    private ChallengeDAO ChallengeDao;
    private LiveData<List<Challenge>> allChallenges;
    public ChallengeRepository(Application application){
        ChallengeDatabase db = ChallengeDatabase.getInstance(application);
        ChallengeDao =db.ChallengeDao();
        allChallenges= ChallengeDao.getAll();
    }
    // Room executes this query on a separate thread
    public LiveData<List<Challenge>> getAllChallenges() {
        return allChallenges;
    }
    public void insert(final Challenge Challenge){
        ChallengeDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                ChallengeDao.insert(Challenge);
            }
        });
    }
    public void deleteAll(){
        ChallengeDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                ChallengeDao.deleteAll();
            }
        });
    }
    public void delete(final Challenge Challenge){
        ChallengeDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                ChallengeDao.delete(Challenge);
            }
        });
    }
    public void updateChallenge(final Challenge Challenge){
        ChallengeDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                ChallengeDao.updateChallenge(Challenge);
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public CompletableFuture<Challenge> findByIDFuture(final int ChallengeId) {
        return CompletableFuture.supplyAsync(new Supplier<Challenge>() {
            @Override
            public Challenge get() {
                return ChallengeDao.findByID(ChallengeId);
            }
        }, ChallengeDatabase.databaseWriteExecutor);
    }
}