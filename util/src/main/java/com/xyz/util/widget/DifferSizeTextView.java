package com.xyz.util.widget;

import android.content.Context;
import androidx.appcompat.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by ZP on 2018/6/19.
 */

public class DifferSizeTextView extends AppCompatTextView {

    private static final String TAG = "DifferSizeTextView";


    public DifferSizeTextView(Context context) {
        super(context);
    }

    public DifferSizeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DifferSizeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
