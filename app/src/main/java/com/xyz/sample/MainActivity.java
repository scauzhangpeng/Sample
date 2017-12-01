package com.xyz.sample;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xyz.util.DeviceUtil;
import com.xyz.util.IntentUtil;
import com.xyz.util.PackageUtil;

public class MainActivity extends AppCompatActivity {

    private EditText mEtPackage;
    private EditText mEtLabel;
    private LinearLayout mLlXiaomi;
    private TextView mTvVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    public void toHiddenList(View view) {
        IntentUtil.toHiddenList(this);
    }

    public void toHiddenConfig(View view) {
        String packageName = mEtPackage.getText().toString();
        if (TextUtils.isEmpty(packageName)) {
            return;
        }
        String label = mEtLabel.getText().toString();
        if (TextUtils.isEmpty(label)) {
            return;
        }
        IntentUtil.toHiddenConfig(this, packageName, label);
    }

    private void initView() {
        mEtPackage = (EditText) findViewById(R.id.et_package);
        mEtLabel = (EditText) findViewById(R.id.et_label);
        mLlXiaomi = (LinearLayout) findViewById(R.id.ll_xiaomi);
        mTvVersion = (TextView) findViewById(R.id.tv_version);
    }

    private void initData() {
        if (Build.MANUFACTURER.equalsIgnoreCase("xiaomi") || Build.MANUFACTURER.equalsIgnoreCase("RedMi")) {
            mLlXiaomi.setVisibility(View.VISIBLE);
            mEtPackage.setText(this.getPackageName());
            mEtLabel.setText(PackageUtil.getAppName(this));
            mTvVersion.setText("MIUI 版本：" + DeviceUtil.getSysUIVersionCompat(DeviceUtil.ROM.MIUI));
        }
    }
}
