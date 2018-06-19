package com.xyz.sample;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.xyz.util.PackageUtil;

/**
 * Created by ZP on 2017/12/27.
 */

public class BaseActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: " + getActivityInfo());
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: " + getActivityInfo());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "onNewIntent: " + getActivityInfo());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: " + getActivityInfo());
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: " + getActivityInfo());
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: " + getActivityInfo());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: " + getActivityInfo());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: ");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestoreInstanceState: ");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult: " + "requestCode:" + requestCode + "resultCode:" + resultCode);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: " + getActivityInfo());
    }

    protected boolean openActivity(Class<?> cls) {
        return openActivity(cls, -1);
    }

    protected boolean openActivity(Class<?> clazz, int flag) {
        try {
            Intent intent = new Intent(this, clazz);
            if (flag != -1) {
                intent.addFlags(flag);
            }
            startActivity(intent);
        } catch (ActivityNotFoundException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    protected boolean openActivityForResult(Class<?> cls, int requestCode) {
        try {
            Intent intent = new Intent(this, cls);
            startActivityForResult(intent, requestCode);
        } catch (ActivityNotFoundException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    private String getActivityInfo() {
        int taskId = this.getTaskId();
        int hashCode = this.hashCode();
        String taskAffinity = PackageUtil.getTaskAffinity(this);
        return getResources().getString(R.string.activity_info, taskId, hashCode, taskAffinity);
    }
}
