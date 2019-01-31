package com.demoproject.roomdatabase.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.demoproject.roomdatabase.database.entity.Employee;

import java.util.List;

@Dao
public interface EmployeeDaoAccess {
    @Insert
    Long insertEmployee(Employee employee);

    @Query("SELECT * FROM Employee")
    List<Employee> getAllEmployeeList();


    @Query("SELECT * FROM Employee WHERE id =:empId")
    LiveData<Employee> getEmployeeData(int empId);

    @Update
    void updateEmployeeRecord(Employee employee);

    @Delete
    void deleteEmployeeRecord(Employee employee);

}
