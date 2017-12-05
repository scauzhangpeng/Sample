package com.xyz.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xyz.util.PackageUtil;
import com.xyz.util.bean.WrapperPackageInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZP on 2017/12/5.
 */

public class InstallAppActivity extends AppCompatActivity {


    private RecyclerView mRvInstallApp;
    private FileAdapter mAdapter;
    private List<WrapperPackageInfo> mData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_install_app);
        initView();
    }

    private void initView() {
        mRvInstallApp = (RecyclerView) findViewById(R.id.rv_install_app);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvInstallApp.setLayoutManager(linearLayoutManager);
        mData = new ArrayList<>();
        mAdapter = new FileAdapter(R.layout.item_install_app, mData, this);
        mRvInstallApp.setAdapter(mAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<WrapperPackageInfo> installApp = PackageUtil.getInstallApp(this);
        mData.clear();
        mData.addAll(installApp);
        mAdapter.notifyDataSetChanged();
    }
}
