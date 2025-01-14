package com.miniapp.talks.popup;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.miniapp.talks.R;
import com.miniapp.talks.view.ShapeTextView;

import java.util.ArrayList;
import java.util.List;


/**
 * 房间输入
 */
public class KeybordWindow extends PopupWindow {


    private View mMenuView;
    private Context context;
    private EditText editMessage;
    private ShapeTextView btn_ok;

    public EditText getEditMessage() {
        return editMessage;
    }


    public ShapeTextView getBtn_ok() {
        return btn_ok;
    }


    public KeybordWindow(Activity context) {
        super(context);
        this.context = context;
        mMenuView = LayoutInflater.from(context).inflate(R.layout.dialog_city, null);
        editMessage = (EditText) mMenuView.findViewById(R.id.editMessage);
        btn_ok = (ShapeTextView) mMenuView.findViewById(R.id.btn_ok);
        // 设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
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
        this.setBackgroundDrawable(dw);
        //设置透明
        WindowManager.LayoutParams params= context.getWindow().getAttributes();
        params.alpha=0.7f;
        context.getWindow().setAttributes(params);

        editMessage.setFocusable(true);
        editMessage.setFocusableInTouchMode(true);
        editMessage.requestFocus();//获取焦点 光标出现

        editMessage.postDelayed(() -> {
            InputMethodManager inputMethodManager=(InputMethodManager)
                    editMessage.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
           inputMethodManager.showSoftInput(editMessage, 0);
        },0);
    }


}
