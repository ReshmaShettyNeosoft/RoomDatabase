package com.demoproject.roomdatabase.database;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.demoproject.roomdatabase.R;

public class DatabaseClient {

    private Context context;
    private static DatabaseClient mInstance;

    //our app database object
    private AppDatabase appDatabase;

    private DatabaseClient(Context context) {
        this.context = context;
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, context.getString(R.string.db_name)).build();
    }

    public static synchronized DatabaseClient getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new DatabaseClient(context);
        }
        return mInstance;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
