package com.xyz.sample;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.widget.TextView;

import com.xyz.util.DeviceUtil;
import com.xyz.util.NetWorkUtil;
import com.xyz.util.SdCardUtil;
import com.xyz.util.bean.SDCardInfo;

import java.util.List;

/**
 * Created by ZP on 2019/4/17.
 */
public class RomInfoActivity extends BaseActivity {

    private TextView mTvAllExtendMemory;
    private TextView mTvSdMemory;
    private TextView mTvMacAddress;
    private TextView mTvRomUiVersion;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_rom_info);
        initView();
        initData();
    }

    private void initView() {

        mTvAllExtendMemory = (TextView) findViewById(R.id.tv_all_extend_memory);
        mTvSdMemory = (TextView) findViewById(R.id.tv_sd_memory);
        mTvMacAddress = (TextView) findViewById(R.id.tv_mac_address);
        mTvRomUiVersion = (TextView) findViewById(R.id.tv_rom_ui_version);
    }

    private void initData() {
        if (SdCardUtil.isSdCardUseful()) {
            List<SDCardInfo> allExtendedMemory = SdCardUtil.getAllExtendedMemory(this);
            StringBuilder info = new StringBuilder();
            for (SDCardInfo sdCardInfo : allExtendedMemory) {
                info.append(sdCardInfo.toString());
            }
            mTvAllExtendMemory.setText(mTvAllExtendMemory.getText().toString() + info.toString());

            String sdMemory = SdCardUtil.formatSdMemory(this);

            mTvSdMemory.setText(mTvSdMemory.getText().toString() + sdMemory);
        }

        String macAddressCompat = NetWorkUtil.getMacAddressCompat(this);

        //Intent permission require
        mTvMacAddress.setText(mTvMacAddress.getText().toString() + macAddressCompat);

        String sysUIVersionCompat = DeviceUtil.getSysUIVersionCompat();

        mTvRomUiVersion.setText(mTvRomUiVersion.getText().toString() + sysUIVersionCompat);


    }
}
