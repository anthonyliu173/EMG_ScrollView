package com.anthony.emq_scrollview;

import android.app.Application;
import android.content.Context;

/**
 * Created by anthonyliu on 2016/3/30.
 */
public class App  extends Application
{

    //TODO measure each API request time taken and dynamically change [visibleThreshold] in [TransactionAdapter]
    //TODO implement internet availability check

    @Override
    public void onCreate()
    {
        super.onCreate();

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

}
