package com.demoproject.roomdatabase.registration;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.Toast;

import com.demoproject.roomdatabase.database.DatabaseClient;
import com.demoproject.roomdatabase.database.entity.Employee;
import com.demoproject.roomdatabase.utils.CommonUtils;

import java.util.Date;
import java.util.List;


public class RegistrationViewModel extends AndroidViewModel {
    private Application application;
    private MutableLiveData<List<Employee>> empListLiveData;
    private CommonUtils commonUtils;

    public RegistrationViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
        commonUtils = new CommonUtils(application.getApplicationContext());
    }

    //Register employee
    public void registerEmployee(String firstName, String lastName) {
        if (commonUtils.Isvalid(firstName) && commonUtils.Isvalid(lastName))
            new RegisterEmployeeAsync(firstName, lastName).execute();
        else
            Toast.makeText(application.getApplicationContext(), "Enter all fields", Toast.LENGTH_SHORT).show();
    }

    //Method to prepare Employee Data
    public Employee prepareEmployeeData(String firstName, String lastName) {
        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setActive(true);
        employee.setCreatedDate(new Date());
        employee.setEditedDate(new Date());
        return employee;
    }

    public MutableLiveData<List<Employee>> getEmployeeLiveData() {
        if (empListLiveData == null) empListLiveData = new MutableLiveData();
        return empListLiveData;
    }


    public String getAllEmployeeName(List<Employee> empList) {
        String empNames = "";
        for (Employee employee : empList) {
            empNames = empNames + "\n" + employee.getFirstName();
        }
        return empNames;
    }

    public void deleteEmpRecord(Employee employee) {
        new DeleteEmployeeDataAsync(employee).execute();
    }

    /*
       AsynTask to register Data
     */
    class RegisterEmployeeAsync extends AsyncTask<String, String, String> {
        String firstName, lastName;

        RegisterEmployeeAsync(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        @Override
        protected String doInBackground(String... strings) {
            Employee employee = prepareEmployeeData(firstName, lastName);

            Long insert = DatabaseClient.getInstance(application.getApplicationContext()).getAppDatabase()
                    .employeeDaoAccess()
                    .insertEmployee(employee);
            if (insert != -1) return "success";
            return "";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s.equalsIgnoreCase("success")) {
                Toast.makeText(application.getApplicationContext(), "Registraion successfull", Toast.LENGTH_SHORT).show();
                new GetEmployeeDataAsync().execute();
            }
        }
    }

    /*
       AsynTask to get list of Employee Data
    */
    class GetEmployeeDataAsync extends AsyncTask<String, String, String> {
        List<Employee> employeeList = null;

        @Override
        protected String doInBackground(String... strings) {
            //get all data
            employeeList = DatabaseClient.getInstance(application.getApplicationContext()).getAppDatabase()
                    .employeeDaoAccess().getAllEmployeeList();
            if (employeeList.size() > 0) return "success";
            return "";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s.equalsIgnoreCase("success")) {
                empListLiveData.setValue(employeeList);
                Toast.makeText(application.getApplicationContext(), "Registraion successfull", Toast.LENGTH_SHORT).show();

            }
        }
    }

    /*
       AsynTask Delete Employee Data
    */
    class DeleteEmployeeDataAsync extends AsyncTask<String, String, String> {
        Employee employee = null;

        public DeleteEmployeeDataAsync(Employee employee) {
            this.employee = employee;
        }

        @Override
        protected String doInBackground(String... strings) {
            //delete record
            DatabaseClient.getInstance(application.getApplicationContext()).getAppDatabase()
                    .employeeDaoAccess().deleteEmployeeRecord(employee);

            return "";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(application.getApplicationContext(), "Employee Deleted successfully", Toast.LENGTH_SHORT).show();
            new GetEmployeeDataAsync().execute(); //get all record
        }
    }
}






