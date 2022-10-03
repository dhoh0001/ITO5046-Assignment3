package com.example.ito5046_assignment3.fragment;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.ito5046_assignment3.databinding.HistoryFragmentBinding;
import com.example.ito5046_assignment3.databinding.ViewFragmentBinding;
import com.example.ito5046_assignment3.entity.Attempt;
import com.example.ito5046_assignment3.entity.Customer;
import com.example.ito5046_assignment3.viewmodel.AttemptViewModel;
import com.example.ito5046_assignment3.viewmodel.CustomerViewModel;
import com.example.ito5046_assignment3.viewmodel.SharedViewModel;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class HistoryFragment extends Fragment {
    private HistoryFragmentBinding binding;
    private AttemptViewModel attemptViewModel;

    public HistoryFragment(){}
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the View for this fragment using the binding
        binding = HistoryFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        attemptViewModel = new
                ViewModelProvider(getActivity()).get(AttemptViewModel.class);

        String pattern = "dd/MM";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        long currentDate = new Date().getTime();
        Date date6DaysAgo = new Date(currentDate - 6 * 24 * 60 * 60 * 1000);
        String date6DaysAgoDisp = format.format(date6DaysAgo);
        Date date5DaysAgo = new Date(currentDate - 5 * 24 * 60 * 60 * 1000);
        String date5DaysAgoDisp = format.format(date6DaysAgo);
        Date date4DaysAgo = new Date(currentDate - 4 * 24 * 60 * 60 * 1000);
        String date4DaysAgoDisp = format.format(date6DaysAgo);
        Date date3DaysAgo = new Date(currentDate - 3 * 24 * 60 * 60 * 1000);
        String date3DaysAgoDisp = format.format(date6DaysAgo);
        Date date2DaysAgo = new Date(currentDate - 2 * 24 * 60 * 60 * 1000);
        String date2DaysAgoDisp = format.format(date6DaysAgo);
        Date date1DaysAgo = new Date(currentDate - 1 * 24 * 60 * 60 * 1000);
        String date1DaysAgoDisp = format.format(date6DaysAgo);
        Date date0DaysAgo = new Date(currentDate);
        String date0DaysAgoDisp = format.format(date6DaysAgo);

        List<BarEntry> barEntries = new ArrayList<>();
        CompletableFuture<LiveData<List<Attempt>>> attempts6DaysAgo =
                attemptViewModel.getInDateRange(date6DaysAgo.getTime(), date5DaysAgo.getTime());
        attempts6DaysAgo.thenApply(attempts -> barEntries.add(new BarEntry(0, attempts.getValue().size())));
        CompletableFuture<LiveData<List<Attempt>>> attempts5DaysAgo =
                attemptViewModel.getInDateRange(date5DaysAgo.getTime(), date4DaysAgo.getTime());
        attempts5DaysAgo.thenApply(attempts -> barEntries.add(new BarEntry(1, attempts.getValue().size())));
        CompletableFuture<LiveData<List<Attempt>>> attempts4DaysAgo =
                attemptViewModel.getInDateRange(date4DaysAgo.getTime(), date3DaysAgo.getTime());
        attempts4DaysAgo.thenApply(attempts -> barEntries.add(new BarEntry(2, attempts.getValue().size())));
        CompletableFuture<LiveData<List<Attempt>>> attempts3DaysAgo =
                attemptViewModel.getInDateRange(date3DaysAgo.getTime(), date2DaysAgo.getTime());
        attempts3DaysAgo.thenApply(attempts -> barEntries.add(new BarEntry(3, attempts.getValue().size())));
        CompletableFuture<LiveData<List<Attempt>>> attempts2DaysAgo =
                attemptViewModel.getInDateRange(date2DaysAgo.getTime(), date1DaysAgo.getTime());
        attempts2DaysAgo.thenApply(attempts -> barEntries.add(new BarEntry(4, attempts.getValue().size())));
        CompletableFuture<LiveData<List<Attempt>>> attempts1DaysAgo =
                attemptViewModel.getInDateRange(date1DaysAgo.getTime(), date0DaysAgo.getTime());
        attempts1DaysAgo.thenApply(attempts -> barEntries.add(new BarEntry(5, attempts.getValue().size())));
        CompletableFuture<LiveData<List<Attempt>>> attempts0DaysAgo =
                attemptViewModel.getInDateRange(date0DaysAgo.getTime(), currentDate);
        attempts0DaysAgo.thenApply(attempts -> barEntries.add(new BarEntry(6, attempts.getValue().size())));

        BarDataSet barDataSet = new BarDataSet(barEntries, "Steps");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        List<String> xAxisValues = new ArrayList<>(Arrays.asList(date6DaysAgoDisp,
                date5DaysAgoDisp, date4DaysAgoDisp, date3DaysAgoDisp, date2DaysAgoDisp,
                date1DaysAgoDisp, date0DaysAgoDisp));
        binding.barChart.getXAxis().setValueFormatter(new
                com.github.mikephil.charting.formatter.IndexAxisValueFormatter(xAxisValues));
        BarData barData = new BarData(barDataSet);
        binding.barChart.setData(barData);
        barData.setBarWidth(1.0f);
        binding.barChart.setVisibility(View.VISIBLE);
        binding.barChart.animateY(4000);
        //description will be displayed as "Description Label" if not provided
        Description description = new Description();
        description.setText("Daily Steps");
        binding.barChart.setDescription(description);
        //refresh the chart
        binding.barChart.invalidate();

        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
