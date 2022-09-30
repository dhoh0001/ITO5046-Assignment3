package com.example.ito5046_assignment3.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.ito5046_assignment3.databinding.RoomFragmentBinding;
import com.example.ito5046_assignment3.databinding.ViewFragmentBinding;
import com.example.ito5046_assignment3.entity.Customer;
import com.example.ito5046_assignment3.viewmodel.CustomerViewModel;
import com.example.ito5046_assignment3.viewmodel.SharedViewModel;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class RoomFragment  extends Fragment {
    private RoomFragmentBinding binding;
    private CustomerViewModel customerViewModel;

    public RoomFragment(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the View for this fragment using the binding
        binding = RoomFragmentBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        binding.idTextField.setPlaceholderText("This is only used for Edit");

        customerViewModel = new
                ViewModelProvider(getActivity()).get(CustomerViewModel.class);
        customerViewModel.getAllCustomers().observe(getViewLifecycleOwner(), new
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
                });

        binding.addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String name=
                        binding.nameTextField.getEditText().getText().toString();
                String
                        surname=binding.surnameTextField.getEditText().getText().toString();
                String strSalary
                        =binding.salaryTextField.getEditText().getText().toString();
                if ((!name.isEmpty() && name!= null) && (!surname.isEmpty() &&
                        strSalary!=null) && (!strSalary.isEmpty() && surname!=null)) {
                    double salary = Double.parseDouble(strSalary);
                    Customer customer = new Customer(name, surname, salary);
                    customerViewModel.insert(customer);
                    binding.textViewAdd.setText("Added Record: " + name + " "
                            + surname + " " + salary);
                }
            }});
        binding.deleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                customerViewModel.deleteAll();
                binding.textViewDelete.setText("All data was deleted");
            }
        });
        binding.clearButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                binding.nameTextField.getEditText().setText("");
                binding.surnameTextField.getEditText().setText("");
                binding.salaryTextField.getEditText().setText("");
            }
        });
        binding.updateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String strId
                        =binding.idTextField.getEditText().getText().toString();
                int id=0;
                if (!strId.isEmpty() && strId!= null)
                    id=Integer.parseInt(strId);
                String name=
                        binding.nameTextField.getEditText().getText().toString();
                String
                        surname=binding.surnameTextField.getEditText().getText().toString();
                String strSalary
                        =binding.salaryTextField.getEditText().getText().toString();
                if ((!name.isEmpty() && name!= null) && (!surname.isEmpty() &&
                        strSalary!=null) && (!strSalary.isEmpty() && surname!=null)) {
                    double salary = Double.parseDouble(strSalary);
//this deals with versioning issues
                    if (android.os.Build.VERSION.SDK_INT >=
                            android.os.Build.VERSION_CODES.N) {
                        CompletableFuture<Customer> customerCompletableFuture =
                                customerViewModel.findByIDFuture(id);
                        customerCompletableFuture.thenApply(customer -> {
                            if (customer != null) {
                                customer.firstName = name;
                                customer.lastName = surname;
                                customer.salary = salary;
                                customerViewModel.update(customer);
                                binding.textViewUpdate.setText("Update was successful " +
                                        "for ID: " + customer.uid);
                            } else {
                                binding.textViewUpdate.setText("Id does not exist");
                            }
                            return customer;
                        });
                    }
                }}
        });
        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
