package com.xyz.sample;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by ZP on 2018/2/7.
 * <p>
 * 在<include>标签当中，我们是可以覆写所有layout属性的，即include中指定的属性将会覆盖掉layout中指定的属性
 * 非layout属性则无法在<include>标签当中进行覆写。
 * 另外需要注意的是，如果我们想要在<include>标签当中覆写layout属性，必须要将layout_width和layout_height这两个属性也进行覆写，
 * 否则覆写效果将不会生效。
 * </p>
 * <p>
 * <p>
 * <merge>标签是作为<include>标签的一种辅助扩展来使用的，它的主要作用是为了防止在引用布局文件时产生多余的布局嵌套。
 * </p>
 * <p>
 * <p>
 * ViewStub 是一个轻量级的View，它一个看不见的，不占布局位置，占用资源非常小的控件。
 * ViewStub所加载的布局是不可以使用<merge>标签的。
 * 因此这有可能导致加载出来的布局存在着多余的嵌套结构，具体如何去取舍就要根据各自的实际情况来决定了。
 * ViewStub会传递参数至Layout布局中
 * </p>
 */

public class ViewStubActivity extends BaseActivity implements View.OnClickListener {

    private EditText mEt1;
    private Button mBtnMore;
    private Button mBtnOk;
    private Button mBtnClear;
    private EditText mEtViewStub1;
    private LinearLayout mLlViewStub;
    private Button mBtnLess;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_stub);
        initView();
    }


    private void initView() {
        mEt1 = (EditText) findViewById(R.id.et_1);
        mBtnMore = (Button) findViewById(R.id.btn_more);

        mBtnMore.setOnClickListener(this);
        mBtnOk = (Button) findViewById(R.id.btn_ok);
        mBtnOk.setOnClickListener(this);
        mBtnClear = (Button) findViewById(R.id.btn_clear);
        mBtnClear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_more:
                showViewStub();
                break;
            case R.id.btn_ok:
                if (mEtViewStub1 != null) {
                    Toast.makeText(ViewStubActivity.this, mEtViewStub1.getText().toString(), Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btn_clear:
                break;
        }
    }

    private void showViewStub() {
        ViewStub viewStub = (ViewStub) findViewById(R.id.viewstub);
        if (viewStub != null) {
            View inflate = viewStub.inflate();
//        viewStub.setVisibility(View.VISIBLE);
            mEtViewStub1 = (EditText) inflate.findViewById(R.id.viewstub_et_1);
            mLlViewStub = (LinearLayout) inflate.findViewById(R.id.ll_root);
            mBtnLess = (Button) inflate.findViewById(R.id.btn_less);
            mBtnLess.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mLlViewStub.setVisibility(View.GONE);
                }
            });
        } else {
            if (mLlViewStub != null) {
                mLlViewStub.setVisibility(View.VISIBLE);
            }
        }
    }
}
