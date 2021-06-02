package com.xyz.sample.lifecycle.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;

import com.xyz.sample.BaseActivity;
import com.xyz.sample.R;

/**
 * Created by ZP on 2018/6/4.
 */

public class SingleTopActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_launch_mode);
    }

    public void openStandardActivity(View view) {
        openActivity(StandardActivity.class);
    }

    public void openSingleTopActivity(View view) {
        openActivity(SingleTopActivity.class);
    }

    public void openSingleTaskActivity(View view) {
        openActivity(SingleTaskActivity.class);
    }

    public void openSingleInstanceActivity(View view) {
        openActivity(SingleInstanceActivity.class);
    }

    public void openFlagNewTaskActivity(View view) {
        openActivity(FlagNewTaskActivity.class, Intent.FLAG_ACTIVITY_NEW_TASK);
    }
}
