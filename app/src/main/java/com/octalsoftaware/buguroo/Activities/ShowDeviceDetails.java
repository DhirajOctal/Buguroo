package com.octalsoftaware.buguroo.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.TextView;

import com.octalsoftaware.buguroo.R;
import com.octalsoftaware.buguroo.Utils.DeviceInstallationID;
import com.octalsoftaware.buguroo.Utils.DeviceRootedCheck;
import com.octalsoftaware.buguroo.Utils.EmulationDetection;

import java.util.Objects;

public class ShowDeviceDetails extends AppCompatActivity {

    private Context context;
    private Activity activity;
    private TextView tv_date_time, tv_device_id, tv_no_of_time_installed;

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_device_details);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        context = activity = ShowDeviceDetails.this;
        findById();
        init();
    }

    private void findById() {
        tv_date_time = findViewById(R.id.tv_date_time);
        tv_device_id = findViewById(R.id.tv_device_id);
        tv_no_of_time_installed = findViewById(R.id.tv_no_of_time_installed);
    }

    private void init() {
        if (getIntent().getExtras() != null) {
            int fromPosition = getIntent().getIntExtra("fromPosition", -1);
            switch (fromPosition) {
                case 0:
                    setDeviceID();
                    break;
                case 1:
                    setInstallationID();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:

                    break;
                case 5:
                    break;
                case 6:
                    checkDeviceRoot();
                    break;
                case 7:
                    checkEmulation();
                    break;
                case 8:
                    tv_date_time.setText(Html.fromHtml(getIntent().getStringExtra("data")));
                    break;
                default:
                    finish();
            }
        }
    }

    private void checkDeviceRoot() {
        tv_date_time.setText(Html.fromHtml("<b>Device Rooted : </b>" + DeviceRootedCheck.isDeviceRooted()));
    }

    private void checkEmulation() {
        tv_date_time.setText(Html.fromHtml("<b>Imitation/Emulation  Detected : </b>" + EmulationDetection.isEmulator()));
    }

    private void setInstallationID() {
        String installationID = getIntent().getStringExtra("InstallationID");
        String dateTime = installationID.substring(0, 43);
        String installationDeviceID = installationID.substring(43, installationID.length());
        tv_date_time.setText(Html.fromHtml(dateTime));
        tv_device_id.setText(Html.fromHtml(installationDeviceID));
        String value = installationID.substring(installationID.lastIndexOf("_") + 1);
        tv_no_of_time_installed.setText(Html.fromHtml("<b>Application Installation Count : </b>" + value));
    }

    private void setDeviceID() {
        String deviceID = DeviceInstallationID.getDeviceID(activity);
        tv_device_id.setText(Html.fromHtml(deviceID));
        SharedPreferences sharedPreferences = activity.getSharedPreferences("DeviceIDWithDateTimeSP", Context.MODE_PRIVATE);
        tv_date_time.setText(Html.fromHtml(sharedPreferences.getString("DeviceIDDateTime", "")));
    }
}