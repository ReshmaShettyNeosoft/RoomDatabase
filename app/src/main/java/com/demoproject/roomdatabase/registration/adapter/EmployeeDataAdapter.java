package com.demoproject.roomdatabase.registration.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demoproject.roomdatabase.R;
import com.demoproject.roomdatabase.database.entity.Employee;
import com.demoproject.roomdatabase.registration.RegistrationViewModel;

import java.util.List;

public class EmployeeDataAdapter extends RecyclerView.Adapter<EmployeeDataAdapter.EmployeeDataHolder> {
    private Context context;
    private List<Employee> employeeList;
    private RegistrationViewModel registrationViewModel;

    public EmployeeDataAdapter(RegistrationViewModel registrationViewModel, List<Employee> employeeList) {
        this.registrationViewModel = registrationViewModel;
        this.employeeList = employeeList;
    }

    @Override
    public EmployeeDataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_recyclerview, parent, false);
        return new EmployeeDataHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeDataHolder holder, int position) {
        Employee employee = employeeList.get(position);
        holder.empNameTextView.setText(employee.getFirstName() + " " + employee.getLastName());
        holder.empNameTextView.setTag(employee);
        holder.empNameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Employee selectedEmployee = (Employee) v.getTag();
                registrationViewModel.deleteEmpRecord(selectedEmployee);
            }
        });
    }


    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    public class EmployeeDataHolder extends RecyclerView.ViewHolder {
        TextView empNameTextView;

        public EmployeeDataHolder(View itemView) {
            super(itemView);
            empNameTextView = (TextView) itemView.findViewById(R.id.empNameTextView);
        }
    }
}