package com.xyz.util.widget;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.text.style.SuperscriptSpan;
import android.util.AttributeSet;

import java.util.Locale;

/**
 * 仿QQ钱包金额显示的TextView.
 * 输入人民币以分为单位的金额，显示出以元为单位的金额（很多时候金额的传输都是采用分，避免精度丢失）。
 * 例如：输入：1628，显示¥16.28(元的字体和分的字体大小会区别)
 * Created by ZP on 2018/6/19.
 */

public class RMBTextView extends DifferSizeTextView {

    public RMBTextView(Context context) {
        super(context);
    }

    public RMBTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RMBTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        try {
            text = toAmountString(Long.valueOf(text.toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        text = "¥" + text.toString();
        float proportion = 0.8f;
        SpannableString spannableString = new SpannableString(text);
        SuperscriptSpan superscriptSpan = new SuperscriptSpan();
        spannableString.setSpan(superscriptSpan, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        RelativeSizeSpan relativeSizeSpan = new RelativeSizeSpan(proportion);
        spannableString.setSpan(relativeSizeSpan, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        if (text.length() >= 4) {
            SuperscriptSpan suffixRMBSuper = new SuperscriptSpan();
            RelativeSizeSpan suffixRMBSize = new RelativeSizeSpan(proportion);
            spannableString.setSpan(suffixRMBSuper, text.length() - 3, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(suffixRMBSize, text.length() - 3, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        super.setText(spannableString, type);
    }

    /**
     * 金额单位转换，分转元
     *
     * @param value 金额，单位分
     * @return 金额， 单位元
     */
    private String toAmountString(long value) {
        return String.format(Locale.CHINA, "%.2f", value / 100.0f);
    }
}
