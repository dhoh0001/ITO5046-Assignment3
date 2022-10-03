package com.example.ito5046_assignment3.viewmodel;

import android.app.Application;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.ito5046_assignment3.entity.Attempt;
import com.example.ito5046_assignment3.repository.AttemptRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class AttemptViewModel extends AndroidViewModel {
    private AttemptRepository cRepository;
    private LiveData<List<Attempt>> allAttempts;
    public AttemptViewModel (Application application) {
        super(application);
        cRepository = new AttemptRepository(application);
        allAttempts = cRepository.getAllAttempts();
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public CompletableFuture<Attempt> findByIDFuture(final int AttemptId){
        return cRepository.findByIDFuture(AttemptId);
    }
    public LiveData<List<Attempt>> getAllAttempts() {
        return allAttempts;
    }
    public void insert(Attempt Attempt) {
        cRepository.insert(Attempt);
    }
    public void deleteAll() {
        cRepository.deleteAll();
    }
    public void update(Attempt Attempt) {
        cRepository.updateAttempt(Attempt);
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public CompletableFuture<LiveData<List<Attempt>>> getInDateRange(long date1, long date2) {
        return cRepository.getInDateRange(date1, date2);
    }
}