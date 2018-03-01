package com.xyz.sample;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xyz.util.CameraUtil;
import com.xyz.util.DeviceUtil;
import com.xyz.util.IntentUtil;
import com.xyz.util.PackageUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEtPackage;
    private EditText mEtLabel;
    private LinearLayout mLlXiaomi;
    private TextView mTvVersion;
    private Button btn_open_flash;
    private Button btn_close_flash;

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
        btn_open_flash = (Button) findViewById(R.id.btn_open_flash);
        btn_open_flash.setOnClickListener(this);
        btn_close_flash = (Button) findViewById(R.id.btn_close_flash);
        btn_close_flash.setOnClickListener(this);
    }

    private void initData() {
        if (Build.MANUFACTURER.equalsIgnoreCase("xiaomi") || Build.MANUFACTURER.equalsIgnoreCase("RedMi")) {
            mLlXiaomi.setVisibility(View.VISIBLE);
            mEtPackage.setText(this.getPackageName());
            mEtLabel.setText(PackageUtil.getAppName(this));
            mTvVersion.setText("MIUI 版本：" + DeviceUtil.getSysUIVersionCompat(DeviceUtil.ROM.MIUI));
        }
    }

    public void openFlash() {
        boolean b = CameraUtil.getInstance().openFlash(this);
        if (!b) {
            Toast.makeText(this, "打开闪关灯失败", Toast.LENGTH_LONG).show();
        }
    }

    public void closeFlash() {
        boolean b = CameraUtil.getInstance().closeFlash();
        if (!b) {
            Toast.makeText(this, "关闭闪关灯失败", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_open_flash:
                openFlash();
                break;
            case R.id.btn_close_flash:
                closeFlash();
                break;
        }
    }
}
