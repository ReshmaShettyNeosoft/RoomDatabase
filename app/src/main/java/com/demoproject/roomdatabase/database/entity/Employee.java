package com.demoproject.roomdatabase.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.demoproject.roomdatabase.database.TimeStampConvertor;

import java.io.Serializable;
import java.util.Date;

@Entity
public class Employee implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String firstName;
    private String lastName;

    private boolean isActive;

    @TypeConverters({TimeStampConvertor.class})
    private Date createdDate;

    @TypeConverters({TimeStampConvertor.class})
    private Date editedDate;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getEditedDate() {
        return editedDate;
    }

    public void setEditedDate(Date editedDate) {
        this.editedDate = editedDate;
    }
}
