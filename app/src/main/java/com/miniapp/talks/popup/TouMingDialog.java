package com.miniapp.talks.popup;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Display;
import android.view.Gravity;
import android.view.WindowManager;

import com.miniapp.talks.R;

import butterknife.ButterKnife;

public class TouMingDialog extends Dialog {
    private Activity mContext;


    public TouMingDialog(@NonNull Activity context) {
        super(context, R.style.myTouMingDialog);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_box_gift);
        ButterKnife.bind(this);
        setWidows();

    }

    private void setWidows() {

        Display display = getWindow().getWindowManager().getDefaultDisplay();

        WindowManager.LayoutParams lp = getWindow().getAttributes();

        lp.width = (int) (display.getWidth());

        lp.alpha = 1.0f;

        lp.gravity = Gravity.CENTER;

        getWindow().setAttributes(lp);

        this.setCanceledOnTouchOutside(false);
        this.setCancelable(true);
    }
}
