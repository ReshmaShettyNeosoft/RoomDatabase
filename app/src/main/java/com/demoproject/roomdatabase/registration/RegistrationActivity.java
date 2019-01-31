package com.demoproject.roomdatabase.registration;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.demoproject.roomdatabase.BaseActivity;
import com.demoproject.roomdatabase.R;
import com.demoproject.roomdatabase.database.entity.Employee;
import com.demoproject.roomdatabase.registration.adapter.EmployeeDataAdapter;

import java.util.List;

public class RegistrationActivity extends BaseActivity {

    private EditText firstNameEditText, lastNameEditText;
    private Button registerButton;
    private RecyclerView empLisRecyclerView;
    private RegistrationViewModel registrationViewModel;
    private MutableLiveData<List<Employee>> empListLiveData;
    private EmployeeDataAdapter employeeDataAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_registration;
    }

    @Override
    public void viewInitialization() {
        firstNameEditText = findViewById(R.id.firstNameEditText);
        lastNameEditText = findViewById(R.id.lastNameEditText);
        registerButton = findViewById(R.id.registerButton);
        empLisRecyclerView = findViewById(R.id.empLisRecyclerView);
        //setting recyclerView.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        empLisRecyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void objectInitailization() {
        registrationViewModel = new RegistrationViewModel(getApplication());
        empListLiveData = registrationViewModel.getEmployeeLiveData();
        //set observer.
        empListLiveData.observe(this, new Observer<List<Employee>>() {
            @Override
            public void onChanged(@Nullable List<Employee> employees) {
                String names = registrationViewModel.getAllEmployeeName(employees);
                employeeDataAdapter = new EmployeeDataAdapter(registrationViewModel, empListLiveData.getValue());
                empLisRecyclerView.setAdapter(employeeDataAdapter);
            }
        });


    }

    public void registerClick(View v) {
        registrationViewModel.registerEmployee(firstNameEditText.getText().toString(), lastNameEditText.getText().toString());
    }

}
