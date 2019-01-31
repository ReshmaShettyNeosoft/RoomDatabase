package com.demoproject.roomdatabase.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.demoproject.roomdatabase.database.dao.EmployeeDaoAccess;
import com.demoproject.roomdatabase.database.entity.Employee;


@Database(entities = {Employee.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract EmployeeDaoAccess employeeDaoAccess();
}

