package com.octalsoftaware.buguroo.Activities;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.octalsoftaware.buguroo.Adapters.Adapter;
import com.octalsoftaware.buguroo.Delegate.SetOnClickListener;
import com.octalsoftaware.buguroo.R;
import com.octalsoftaware.buguroo.Utils.DeviceInstallationID;
import com.octalsoftaware.buguroo.Utils.EmulationDetection;
import com.octalsoftaware.buguroo.Utils.PermissionUtils;
import com.octalsoftaware.buguroo.Utils.Utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

import static com.octalsoftaware.buguroo.Utils.Utils.isShowLog;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private Activity activity;
    private RecyclerView recyclerView;
    public static final int REQUEST_WRITE_STORAGE = 112;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = activity = MainActivity.this;
        findById();
        boolean hasPermission = (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
        if (!hasPermission) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_STORAGE);
        } else {
            init();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_WRITE_STORAGE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    init();
                    // Reload the activity with permission granted or use the features what required the permission.
                } else {
//                    Toast.makeText(this, "The app was not allowed to write to your storage. Hence, it cannot function properly. Please consider granting it this permission", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    private void findById() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }

    private void init() {
        Adapter adapter = new Adapter(context, new SetOnClickListener() {
            @Override
            public void onClick(int position) {
                Intent newActivity = new Intent(context, ShowDeviceDetails.class);
                switch (position) {
                    case 0:
                        newActivity.putExtra("fromPosition", 0);
                        startActivity(newActivity);
                        break;
                    case 1:
                        InstallationID();
                        break;
                    case 2:
                        newActivity.putExtra("fromPosition", 2);
                        startActivity(newActivity);
                        break;
                    case 3:
                        Intent newActivity3 = new Intent(context, ContextActivity.class);
                        startActivity(newActivity3);
                        break;
                    case 4:
                        newActivity.putExtra("fromPosition", 4);
                        startActivity(newActivity);
                        break;
                    case 5:
                        newActivity.putExtra("fromPosition", 5);
                        startActivity(newActivity);
                        break;
                    case 6:
                        newActivity.putExtra("fromPosition", 6);
                        startActivity(newActivity);
                        break;
                    case 7:
                        newActivity.putExtra("fromPosition", 7);
                        startActivity(newActivity);
                        break;
                }
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void InstallationID() {

        // You are allowed to write external storage:
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Buguroo";
        File storageDir = new File(path);
        SharedPreferences sharedPreferences = context.getSharedPreferences("SAVEID", Context.MODE_PRIVATE);
        if (!storageDir.exists()) {
            storageDir.mkdirs();
            File file = new File(storageDir, "myData.txt");
            try {
                FileOutputStream fos = new FileOutputStream(file);
                String separator = System.getProperty("line.separator");
                PrintWriter pw = new PrintWriter(fos);
                pw.append(separator);
                String installationIDWithDateTime = "<b>Date & Time : </b>" + Utils.getDateandTimeStamp() + "<b>Installation ID : </b>" + DeviceInstallationID.getInstallationID(activity) + "_1";
                pw.println(installationIDWithDateTime);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("InstallationID", installationIDWithDateTime);
                editor.apply();
//                    Toast.makeText(context, "Installation ID : " + DeviceInstallationID.getInstallationID(activity) + "_1", Toast.LENGTH_LONG).show();
//                    Toast.makeText(context, Html.fromHtml(sharedPreferences.getString("InstallationID", "")), Toast.LENGTH_LONG).show();
                pw.flush();
                pw.close();
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Log.i("MainActivity", "******* File not found. Did you" + " add a WRITE_EXTERNAL_STORAGE permission to the   manifest?");
            } catch (IOException e) {
                e.printStackTrace();
            }
            Intent newActivity = new Intent(context, ShowDeviceDetails.class);
            newActivity.putExtra("InstallationID", sharedPreferences.getString("InstallationID", ""));
            newActivity.putExtra("fromPosition", 1);
            startActivity(newActivity);

        } else {
            String deviceID = sharedPreferences.getString("InstallationID", "");
            if (isShowLog)
                Log.e("InstallationID : ", "InstallationID : " + deviceID);
            if (TextUtils.isEmpty(deviceID)) {
                try {
                    File secondInputFile = new File(storageDir, "myData.txt");
                    InputStream secondInputStream = new BufferedInputStream(new FileInputStream(secondInputFile));
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(secondInputStream));
                    StringBuilder total = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        total.append(line);
                    }
                    bufferedReader.close();
                    secondInputStream.close();
                    if (isShowLog)
                        Log.e("File", "File contents: " + total);
                    String value = total.substring(total.lastIndexOf("_") + 1);
                    int valueNew = Integer.parseInt(value);
                    valueNew++;
                    if (isShowLog)
                        Log.e("valueNew : ", "valueNew : " + valueNew);
                    total.replace(total.lastIndexOf("_") + 1, total.length(), "" + valueNew);
                    if (isShowLog)
                        Log.e("total : ", "total : " + total);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("InstallationID", "<b>Date & Time : </b>" + Utils.getDateandTimeStamp() + "<b>Installation ID : </b>" + total.toString());
                    editor.apply();

                    Intent newActivity = new Intent(context, ShowDeviceDetails.class);
                    newActivity.putExtra("InstallationID", sharedPreferences.getString("InstallationID", ""));
                    newActivity.putExtra("fromPosition", 1);
                    startActivity(newActivity);
//                        Toast.makeText(context, Html.fromHtml(sharedPreferences.getString("InstallationID", "")), Toast.LENGTH_LONG).show();
                    File file = new File(storageDir, "myData.txt");
                    try {
                        FileOutputStream fos = new FileOutputStream(file);
                        PrintWriter pw = new PrintWriter(fos);
                        String separator = System.getProperty("line.separator");
                        pw.append(separator);
                        pw.println(total);
                        pw.flush();
                        pw.close();
                        fos.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        Log.i("MainActivity", "******* File not found. Did you" + " add a WRITE_EXTERNAL_STORAGE permission to the   manifest?");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    File secondInputFile = new File(storageDir, "myData.txt");
                    InputStream secondInputStream = new BufferedInputStream(new FileInputStream(secondInputFile));
                    BufferedReader r = new BufferedReader(new InputStreamReader(secondInputStream));
                    StringBuilder total = new StringBuilder();
                    String line;
                    while ((line = r.readLine()) != null) {
                        total.append(line);
                    }
                    r.close();
                    secondInputStream.close();
                    if (isShowLog)
                        Log.e("File", "File contents: " + total);

                    Intent newActivity = new Intent(context, ShowDeviceDetails.class);
                    newActivity.putExtra("InstallationID", total.toString());
                    newActivity.putExtra("fromPosition", 1);
                    startActivity(newActivity);
//                        Toast.makeText(context, Html.fromHtml(total.toString()), Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}