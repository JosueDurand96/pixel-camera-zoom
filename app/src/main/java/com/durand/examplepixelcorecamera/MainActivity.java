package com.durand.examplepixelcorecamera;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.durand.examplepixelcorecamera.api2.CameraApi2Activity;
import com.durand.examplepixelcorecamera.common.AppPermissions;
import com.durand.examplepixelcorecamera.common.Intents;
import com.durand.examplepixelcorecamera.common.Preferences;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;

    private AppPermissions appPermissions;
    private boolean isModeApi1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_main);
        isModeApi1 = new Preferences(this).isModeApi1();
        appPermissions = new AppPermissions(this);
        if (appPermissions.checkPermissions()) {
            startCameraActivity();
        } else {
            appPermissions.requestPermissions(REQUEST_CODE);
        }
    }



    @Override
    public void onRequestPermissionsResult(
            int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            if (appPermissions.onRequestPermissionResult(grantResults)) {
                startCameraActivity();
            }
        }
    }

    private void startCameraActivity() {
        finish();
        Intent intent = new Intent(this, CameraApi2Activity.class);
        startActivity(intent);
       // startActivity(isModeApi1 ? Intents.createApi1Intent() : Intents.createApi2Intent());
    }
}