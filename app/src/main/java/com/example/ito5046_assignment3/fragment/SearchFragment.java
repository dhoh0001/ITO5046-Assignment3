package com.example.ito5046_assignment3.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ito5046_assignment3.adapter.RecyclerViewAdapter;
import com.example.ito5046_assignment3.databinding.RvLayoutBinding;
import com.example.ito5046_assignment3.databinding.SearchFragmentBinding;
import com.example.ito5046_assignment3.databinding.ViewFragmentBinding;
import com.example.ito5046_assignment3.model.CourseResult;
import com.example.ito5046_assignment3.viewmodel.SharedViewModel;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {
    private RecyclerView.LayoutManager layoutManager;
    private List<CourseResult> units;
    private RecyclerViewAdapter adapter;
    private SearchFragmentBinding binding;

    public SearchFragment(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the View for this fragment using the binding
        binding = SearchFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        units=new ArrayList<CourseResult>();
        units = CourseResult.createContactsList();
        adapter = new RecyclerViewAdapter(units);
//this just creates a line divider between rows
        binding.recyclerView.addItemDecoration(new
                DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        binding.recyclerView.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(getContext());
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String unit = binding.etUnit.getText().toString().trim();
                String smark= binding.etMark.getText().toString().trim();
                if (!unit.isEmpty() || !smark.isEmpty()) {
                    int mark=new Integer(smark).intValue();
                    saveData(unit, mark);
                }
            }
        });
        return view;
    }

    private void saveData(String unit, int mark) {
        CourseResult courseResult = new CourseResult(unit, mark);
        units.add(courseResult);
        adapter.addUnits(units);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
