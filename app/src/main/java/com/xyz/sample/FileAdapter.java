package com.xyz.sample;

import android.content.Context;
import android.text.format.DateUtils;

import com.xyz.sample.adapter.BaseAdapter;
import com.xyz.sample.adapter.BaseViewHolder;
import com.xyz.util.bean.WrapperPackageInfo;

import java.util.List;

/**
 * Created by ZP on 2017/12/5.
 */

public class FileAdapter extends BaseAdapter<WrapperPackageInfo> {

    public FileAdapter(int layoutId, List<WrapperPackageInfo> datas, Context context) {
        super(layoutId, datas, context);
    }

    @Override
    protected void bindData(BaseViewHolder holder, WrapperPackageInfo wrapperPackageInfo, int position) {

        holder.setText(R.id.tv_app_name, wrapperPackageInfo.getAppName());
        holder.setText(R.id.tv_app_version, wrapperPackageInfo.getPackageInfo().versionName);

        long lastUpdateTime = wrapperPackageInfo.getPackageInfo().lastUpdateTime;
        String installDate = DateUtils.formatDateTime(mContext, lastUpdateTime, DateUtils.FORMAT_SHOW_YEAR);
        holder.setText(R.id.tv_install_date, installDate);

        holder.setText(R.id.tv_cache_size, wrapperPackageInfo.formatAppSize(mContext));
        holder.setImageDrawable(R.id.iv_app_icon, wrapperPackageInfo.getDrawable());
    }
}
