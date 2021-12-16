package com.example.yourcircadian;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.KeyEventDispatcher;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final int DOWNLOAD_JOB_KEY = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initJobScheduler();
    }

    private void initJobScheduler() {
        Log.d(TAG, "initJobScheduler: started");
        //Esto se asegura que el API Level es igual o mayor que 21(el de la version LOLLIPOP)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ComponentName componentName = new ComponentName(this, SampleJobService.class);
            PersistableBundle bundle = new PersistableBundle();
            bundle.putInt("number", 10); //the key passed here is the same as in the onStartJob class
            JobInfo.Builder builder = new JobInfo.Builder(DOWNLOAD_JOB_KEY, componentName)
                    .setExtras(bundle)
                    .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                    .setPersisted(true);  //the jobs is rescheduled after the device reboots
            //this uses de setPeriodic according to the API level of the phone (para N es 24)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                builder.setPeriodic(15*60*1000, 13*60*1000);
            }else{
                builder.setPeriodic(15*60*1000);
            }
            JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
            scheduler.schedule(builder.build());
        }
    }
}