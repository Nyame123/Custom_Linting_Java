package com.ecomtrading.mycustomlint.custom_view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CheckTv extends androidx.appcompat.widget.AppCompatEditText {
    public CheckTv(@NonNull Context context) {
        super(context);
    }

    public CheckTv(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CheckTv(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
