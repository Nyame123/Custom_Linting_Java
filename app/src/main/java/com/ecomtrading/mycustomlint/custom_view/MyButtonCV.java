package com.ecomtrading.mycustomlint.custom_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.ecomtrading.mycustomlint.R;

/**
 * TODO: document your custom view class.
 */
public class MyButtonCV extends LinearLayout {


    public MyButtonCV(Context context) {
        super(context);
        init();
    }

    public MyButtonCV(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyButtonCV(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyButtonCV(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void setItem(){

    }

    private void init(){
        setOrientation(VERTICAL);

        //Text View
        TextView textView = new TextView(getContext());
        textView.setText("This is just a test");
        textView.setTextColor(getResources().getColor(R.color.gray_400));


        //Button
        Button button = new Button(getContext());
        button.setText("This is our Button");
        button.setBackground(getResources().getDrawable(R.color.design_default_color_primary));

        LinearLayout.LayoutParams layoutParams =
                new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(8,8,8,8);

        addView(textView);
        addView(button);
        setLayoutParams(layoutParams);

    }
}