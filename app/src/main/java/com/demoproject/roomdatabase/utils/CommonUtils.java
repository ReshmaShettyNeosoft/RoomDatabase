package com.demoproject.roomdatabase.utils;

import android.content.Context;
import android.text.TextUtils;

public class CommonUtils {
    Context context;
    public CommonUtils(Context context){
        this.context = context;
    }

    public boolean Isvalid(String value) {
        return !TextUtils.isEmpty(value);
    }
}
