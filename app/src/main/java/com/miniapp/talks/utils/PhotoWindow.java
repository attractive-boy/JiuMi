package com.miniapp.talks.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.miniapp.talks.R;


/**
 * 拍照选择
 */
public class PhotoWindow extends PopupWindow {


    private View view;
    private Context context;
    private LinearLayout take_photo, chose_pic;

    public LinearLayout getTake_photo() {
        return take_photo;
    }

    public LinearLayout getChose_pic() {
        return chose_pic;
    }

    public PhotoWindow(Activity context) {
        super(context);
        this.context = context;
        view = LayoutInflater.from(context).inflate(R.layout.pop_upload_head, null);
        final LinearLayout dismiss_pop = view.findViewById(R.id.dismiss_pop);
        take_photo = view.findViewById(R.id.take_photo);
        chose_pic = view.findViewById(R.id.chose_pic);
        // 设置SelectPicPopupWindow的View
        this.setContentView(view);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        // 刷新状态
//        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x00000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        WindowManager.LayoutParams params= context.getWindow().getAttributes();
        params.alpha=0.7f;
        context.getWindow().setAttributes(params);
        this.setBackgroundDrawable(dw);
        dismiss_pop.setOnClickListener(view -> {
            dismiss();
        });
    }

}
