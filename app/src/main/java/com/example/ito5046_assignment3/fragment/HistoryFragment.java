package com.example.ito5046_assignment3.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.ito5046_assignment3.databinding.HistoryFragmentBinding;
import com.example.ito5046_assignment3.databinding.ViewFragmentBinding;
import com.example.ito5046_assignment3.viewmodel.SharedViewModel;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HistoryFragment extends Fragment {
    private HistoryFragmentBinding binding;
    public HistoryFragment(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the View for this fragment using the binding
        binding = HistoryFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        SharedViewModel model = new
                ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        List<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(0, 6766));
        barEntries.add(new BarEntry(1, 4444));
        barEntries.add(new BarEntry(2, 2222));
        barEntries.add(new BarEntry(3, 5555));
        barEntries.add(new BarEntry(4, 1111));
        barEntries.add(new BarEntry(5, 3656));
        barEntries.add(new BarEntry(6, 3435));
        BarDataSet barDataSet = new BarDataSet(barEntries, "Steps");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        List<String> xAxisValues = new ArrayList<>(Arrays.asList("Sun", "Mon", "Tues",
                "Wed", "Thurs", "Fri","Sat"));
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
