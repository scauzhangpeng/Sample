package com.xyz.sample;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

/**
 * Created by ZP on 2018/3/28.
 */

public class IntentFilterActivity extends BaseActivity implements View.OnClickListener {


    private Button mBtnShareWechat;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_intentfilter);
        initView();
    }

    private void initView() {
        mBtnShareWechat = (Button) findViewById(R.id.btn_share_wechat);

        mBtnShareWechat.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_share_wechat:
                sendShare();
                break;
        }
    }

    /**
     * 不需要集成SDK即可分享
     * from {@see https://www.jianshu.com/p/4d261a086e71}
     */
    public void sendShare() {
        Intent it = new Intent(Intent.ACTION_SEND);
        it.setComponent(new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareImgUI"));
        it.putExtra(Intent.EXTRA_TEXT, "分享成功");
        it.setType("text/plain");  //可以不要
        startActivity(Intent.createChooser(it, "本机未安装微信"));
    }
}
