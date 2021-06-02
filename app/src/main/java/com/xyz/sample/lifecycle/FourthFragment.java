package com.xyz.sample.lifecycle;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xyz.sample.R;

/**
 * Created by ZP on 2018/2/8.
 */

public class FourthFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f_fourth, container, false);
        return view;
    }
}
