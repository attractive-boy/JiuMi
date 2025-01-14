package com.miniapp.talks.popup;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.miniapp.talks.R;
import com.miniapp.talks.base.MyBaseArmActivity;
import com.miniapp.talks.utils.MyUtil;
import com.miniapp.talks.view.ShapeTextView;

public class BoxFirstOpenWindow extends PopupWindow {
    private View mMenuView;
    private Context context;
    private ImageView boxImage;
    private ShapeTextView okBtn;

    public ImageView getBoxImage() {
        return boxImage;
    }

//    public ShapeTextView getOkBtn() {
//        return okBtn;
//    }

    public BoxFirstOpenWindow(Activity context) {
        super(context);
        this.context = context;
        mMenuView = LayoutInflater.from(context).inflate(R.layout.box_first_open_window, null);
        boxImage = mMenuView.findViewById(R.id.box_first_image);
        okBtn = mMenuView.findViewById(R.id.ok_btn);
        this.setContentView(mMenuView);
        // 设置SelectPicPopupWindow弹出窗体的高

        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        // 刷新状态
//        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x00000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
        //设置透明
        WindowManager.LayoutParams params = context.getWindow().getAttributes();
        Display d = context.getWindowManager().getDefaultDisplay();
        params.alpha = 0.7f;
        this.setWidth(d.getWidth() - MyUtil.dip2px(context, 126));
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        context.getWindow().setAttributes(params);

        okBtn.setOnClickListener(v -> {
            dismiss();
        });
    }

    public void dismiss() {
        super.dismiss();
        MyBaseArmActivity myBaseArmActivity = (MyBaseArmActivity) context;
        WindowManager.LayoutParams params = myBaseArmActivity.getWindow().getAttributes();
        params.alpha = 1f;
        myBaseArmActivity.getWindow().setAttributes(params);
    }
}
