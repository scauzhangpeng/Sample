package com.xyz.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xyz.sample.adapter.LineDecoration;
import com.xyz.util.PackageUtil;
import com.xyz.util.bean.WrapperPackageInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
        LineDecoration lineDecoration = new LineDecoration(this, LineDecoration.VERTICAL_LIST,
                2, R.color.colorPrimaryDark);
        lineDecoration.setMargin(30, 0, 40, 0);
        mRvInstallApp.addItemDecoration(lineDecoration);
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<WrapperPackageInfo> installApp = PackageUtil.getInstallApp(this);
        Collections.sort(installApp, new Comparator<WrapperPackageInfo>() {
            @Override
            public int compare(WrapperPackageInfo info1, WrapperPackageInfo info2) {
                if (info1.getPackageInfo().lastUpdateTime > info2.getPackageInfo().lastUpdateTime) {
                    return -1;
                } else if (info1.getPackageInfo().lastUpdateTime < info2.getPackageInfo().lastUpdateTime) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        mData.clear();
        mData.addAll(installApp);
        mAdapter.notifyDataSetChanged();
    }
}
