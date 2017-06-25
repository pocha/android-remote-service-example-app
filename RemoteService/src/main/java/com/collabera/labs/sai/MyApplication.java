package com.collabera.labs.sai;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import java.util.List;

/**
 * Created by ashishsharma on 25/06/17.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate()
    {
        super.onCreate();

        if (isRemoteProcess(this)) {
            Log.d("Ashish","inside the remote process with pid " + android.os.Process.myPid());
        }
        else {
            Log.d("Ashish","inside the Activity process with pid " + android.os.Process.myPid());
        }

    }

    private boolean isRemoteProcess(Context context)
    {
        Log.d("Ashish","inside isRemoteProcess - should get called twice");
        Context applicationContext = context.getApplicationContext();
        long myPid = (long)android.os.Process.myPid();
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) applicationContext.getSystemService(Context.ACTIVITY_SERVICE)).getRunningAppProcesses();
        if (runningAppProcesses != null && runningAppProcesses.size() != 0)
        {
            Log.d("Ashish","dumping all process names");
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses)
            {
                Log.d("Ashish", runningAppProcessInfo.processName);
                if (((long) runningAppProcessInfo.pid) == myPid && "com.collabera.labs.sai:remote".equals(runningAppProcessInfo.processName))
                {
                    return true;
                }
            }
        }
        return false;
    }
}
