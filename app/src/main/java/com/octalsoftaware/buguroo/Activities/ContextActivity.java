package com.octalsoftaware.buguroo.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.octalsoftaware.buguroo.Adapters.ContextAdapter;
import com.octalsoftaware.buguroo.Delegate.SetOnClickListener;
import com.octalsoftaware.buguroo.R;
import com.octalsoftaware.buguroo.Utils.CalendarContentResolver;
import com.octalsoftaware.buguroo.Utils.ContextInformation;
import com.octalsoftaware.buguroo.Utils.DiskUtils;

import java.io.File;
import java.util.Objects;

public class ContextActivity extends AppCompatActivity {

    private Context context;
    private Activity activity;
    private RecyclerView recyclerView;
    private ContextAdapter contextAdapter;

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        context = activity = ContextActivity.this;
        findById();
        init();
    }

    private void findById() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }

    private void init() {
        contextAdapter = new ContextAdapter(context, new SetOnClickListener() {
            @Override
            public void onClick(int position) {
                switch (position) {
                    case 0:
                        numberOfEntriesInAgenda();
                        break;
                    case 1:
                        diskSpace();
                        break;
                    case 2:
                        screenLockStatus();
                        break;
                    case 3:
                        batteryConsumptionPerApp();
                        break;
                    case 4:
                        dataConsumptionPerApp();
                        break;
                    case 5:
                        numberOfAppsInstalled();
                        break;
                    case 6:
                        osInformationVersion();
                        break;
                    case 7:
                        deviceType();
                        break;
                    case 8:
                        language();
                        break;
                }
            }
        });
        recyclerView.setAdapter(contextAdapter);
    }

    private void numberOfEntriesInAgenda() {

        CalendarContentResolver calendarContentResolver = new CalendarContentResolver(context);
        Log.e("calendar : ", "Calendar : " + calendarContentResolver.getCalendars());
    }

    private void diskSpace() {
        StringBuilder data = new StringBuilder();
        if (DiskUtils.isExternalStorageIsAvailabe()) {
            data.append("<b>External Memory Information</b>");
            data.append("<br/>");
            data.append("<b>Used Space  : </b>" + DiskUtils.bytesToHuman(DiskUtils.usedMemory(true)));
            data.append("<br/>");
            data.append("<b>Free Space  : </b>" + DiskUtils.bytesToHuman(DiskUtils.freeMemory(true)));
            data.append("<br/>");
            data.append("<b>Total Space : </b>" + DiskUtils.bytesToHuman(DiskUtils.totalMemory(true)));
            data.append("<br/><br/>");
        }
        data.append("<b>Internal Memory Information</b><br/>");
        data.append("<br/>");
        data.append("<b>Used Space : </b>" + DiskUtils.bytesToHuman(DiskUtils.usedMemory(false)));
        data.append("<br/>");
        data.append("<b>Free Space : </b>" + DiskUtils.bytesToHuman(DiskUtils.freeMemory(false)));
        data.append("<br/>");
        data.append("<b>Total Space : </b>" + DiskUtils.bytesToHuman(DiskUtils.totalMemory(false)));
        Intent newActivity8 = new Intent(context, ShowDeviceDetails.class);
        newActivity8.putExtra("fromPosition", 8);
        newActivity8.putExtra("data", data.toString());
        startActivity(newActivity8);
    }

    private void screenLockStatus() {
        Intent newActivity8 = new Intent(context, ShowDeviceDetails.class);
        newActivity8.putExtra("fromPosition", 8);
        newActivity8.putExtra("data", "<b>Screen Lock Status : </b>" + ContextInformation.getScreenLockStatus(activity));
        startActivity(newActivity8);
    }

    private void batteryConsumptionPerApp() {

    }

    private void dataConsumptionPerApp() {

    }

    private void numberOfAppsInstalled() {
        Intent newActivity8 = new Intent(context, ShowDeviceDetails.class);
        newActivity8.putExtra("fromPosition", 8);
        newActivity8.putExtra("data", "<b>Number of APPs installed : </b>" + ContextInformation.getNumberOfInstalledApp(activity));
        startActivity(newActivity8);
    }

    private void osInformationVersion() {
        Intent newActivity8 = new Intent(context, ShowDeviceDetails.class);
        newActivity8.putExtra("fromPosition", 8);
        newActivity8.putExtra("data", "<b>OS Information(Version) : </b>" + ContextInformation.getOsInformation());
        startActivity(newActivity8);
    }

    private void deviceType() {
        Intent newActivity8 = new Intent(context, ShowDeviceDetails.class);
        newActivity8.putExtra("fromPosition", 8);
        newActivity8.putExtra("data", "<b>Device Type : </b>" + ContextInformation.getDeviceType());
        startActivity(newActivity8);
    }

    private void language() {
        Intent newActivity8 = new Intent(context, ShowDeviceDetails.class);
        newActivity8.putExtra("fromPosition", 8);
        newActivity8.putExtra("data", "<b>Language : </b>" + ContextInformation.getDeviceLanguage());
        startActivity(newActivity8);
    }
}