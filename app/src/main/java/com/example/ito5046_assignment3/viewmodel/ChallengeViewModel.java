package com.example.ito5046_assignment3.viewmodel;

import android.app.Application;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.ito5046_assignment3.entity.Challenge;
import com.example.ito5046_assignment3.repository.ChallengeRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ChallengeViewModel extends AndroidViewModel {
    private ChallengeRepository cRepository;
    private LiveData<List<Challenge>> allChallenges;
    public ChallengeViewModel (Application application) {
        super(application);
        cRepository = new ChallengeRepository(application);
        allChallenges = cRepository.getAllChallenges();
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public CompletableFuture<Challenge> findByIDFuture(final int ChallengeId){
        return cRepository.findByIDFuture(ChallengeId);
    }
    public LiveData<List<Challenge>> getAllChallenges() {
        return allChallenges;
    }
    public void insert(Challenge Challenge) {
        cRepository.insert(Challenge);
    }
    public void deleteAll() {
        cRepository.deleteAll();
    }
    public void update(Challenge Challenge) {
        cRepository.updateChallenge(Challenge);
    }
}