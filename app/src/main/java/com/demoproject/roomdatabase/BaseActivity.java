package com.demoproject.roomdatabase;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        viewInitialization();
        objectInitailization();
    }

    public abstract int getLayoutId();
    public abstract void viewInitialization();
    public abstract void objectInitailization();
}
