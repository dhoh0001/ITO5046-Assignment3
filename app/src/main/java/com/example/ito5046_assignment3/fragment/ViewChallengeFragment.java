package com.example.ito5046_assignment3.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.ito5046_assignment3.databinding.CreateChallengeFragmentBinding;
import com.example.ito5046_assignment3.databinding.RoomFragmentBinding;
import com.example.ito5046_assignment3.databinding.ViewChallengeFragmentBinding;
import com.example.ito5046_assignment3.entity.Challenge;
import com.example.ito5046_assignment3.entity.Customer;
import com.example.ito5046_assignment3.entity.User;
import com.example.ito5046_assignment3.viewmodel.ChallengeViewModel;
import com.example.ito5046_assignment3.viewmodel.CustomerViewModel;

import java.util.ArrayList;
import java.util.List;

public class ViewChallengeFragment extends Fragment {
    private ChallengeViewModel challengeViewModel;
    private ViewChallengeFragmentBinding binding;
    public ViewChallengeFragment(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the View for this fragment using the binding
        binding = ViewChallengeFragmentBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        ChallengeViewModel model = new
                ViewModelProvider(getActivity()).get(ChallengeViewModel.class);

        model.getAllChallenges().observe(getViewLifecycleOwner(), challenges -> {
            String allCustomers = "";
            /*TextView[] textArray = new TextView[challenges.size() *2];
            TableRow[] tr_head = new TableRow[challenges.size()];*/

            for(int i=0; i<challenges.size();i++) {

                TableRow tr_head= new TableRow(getContext());
                tr_head.setId(i + 1);
                tr_head.setBackgroundColor(Color.GRAY);
                tr_head.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));

                TextView textArray = new TextView(getContext());
                textArray.setId(i + 111);
                textArray.setText("id: " + challenges.get(i).challenge_id);
                textArray.setTextColor(Color.WHITE);
                textArray.setPadding(5, 5, 5, 5);
                tr_head.addView(textArray);

                TableRow tr_coor= new TableRow(getContext());
                tr_coor.setId(i + 1);
                tr_coor.setBackgroundColor(Color.GRAY);
                tr_coor.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                TextView textArray2a = new TextView(getContext());
                textArray2a.setId(i + 111);
                textArray2a.setText("lattitude: " + challenges.get(i).location_lattitude);
                textArray2a.setTextColor(Color.WHITE);
                textArray2a.setPadding(5, 5, 5, 5);
                tr_coor.addView(textArray2a);
                TextView textArray2b = new TextView(getContext());
                textArray2b.setId(i + 111);
                textArray2b.setText("longitude: " + challenges.get(i).location_longitude);
                textArray2b.setTextColor(Color.WHITE);
                textArray2b.setPadding(5, 5, 5, 5);
                tr_coor.addView(textArray2b);

                TableRow tr_head2a= new TableRow(getContext());
                tr_head2a.setId(i + 1);
                tr_head2a.setBackgroundColor(Color.GRAY);
                tr_head2a.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                TextView textArray3 = new TextView(getContext());
                textArray3.setId(i + 111);
                textArray3.setText("Title: " + challenges.get(i).challengeName);
                textArray3.setTextColor(Color.WHITE);
                textArray3.setPadding(5, 5, 5, 5);
                tr_head2a.addView(textArray3);

                TableRow tr_head2b= new TableRow(getContext());
                tr_head2b.setId(i + 1);
                tr_head2b.setBackgroundColor(Color.GRAY);
                tr_head2b.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));

                TextView textArray4 = new TextView(getContext());
                textArray4.setId(i + 111);
                textArray4.setText("Details: " + challenges.get(i).challengeDetails);
                textArray4.setTextColor(Color.WHITE);
                textArray4.setPadding(5, 5, 5, 5);
                tr_head2b.addView(textArray4);

                TableRow tr_head3= new TableRow(getContext());
                tr_head3.setId(i + 1);
                tr_head3.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));

                TextView textArray5 = new TextView(getContext());
                textArray5.setId(i + 111);
                textArray5.setText("");
                textArray5.setTextColor(Color.WHITE);
                textArray5.setPadding(5, 5, 5, 5);
                tr_head3.addView(textArray5);

                binding.textViewRead.addView(tr_head, new TableLayout.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                binding.textViewRead.addView(tr_coor, new TableLayout.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                binding.textViewRead.addView(tr_head2a, new TableLayout.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                binding.textViewRead.addView(tr_head2b, new TableLayout.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                binding.textViewRead.addView(tr_head3, new TableLayout.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
            }
        });

        ArrayList<String> productsList = new ArrayList<>();
        productsList.add("test");
        productsList.add("test1");
        productsList.add("test2");


        /*customerViewModel.getAllCustomers().observe(getViewLifecycleOwner(), new
                Observer<List<Customer>>() {
                    @Override
                    public void onChanged(@Nullable final List<Customer> customers) {
                        String allCustomers = "";
                        for (Customer temp : customers) {
                            String customerDetails = (temp.uid + " " + temp.firstName
                                    + temp.lastName + " " + temp.salary);
                            allCustomers = allCustomers +
                                    System.getProperty("line.separator") + customerDetails;
                        }
                        binding.textViewRead.setText("All data: " + allCustomers);
                    }
                });*/
        return view;
    }
}