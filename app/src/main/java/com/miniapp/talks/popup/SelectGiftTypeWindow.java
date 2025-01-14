package com.miniapp.talks.popup;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.miniapp.talks.R;
import com.miniapp.talks.adapter.GiftDiaAdapter;
import com.miniapp.talks.base.MyBaseArmActivity;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;

import java.util.ArrayList;
import java.util.List;


/**
 * 选择礼物、宝石、或者我的背包
 */
public class SelectGiftTypeWindow extends PopupWindow {


    private ListView myGrid;
    private Context context;
    private GiftDiaAdapter giftDiaAdapter;
    private View mMenuView;

    public View getmMenuView() {
        return mMenuView;
    }

    public GiftDiaAdapter getGiftDiaAdapter() {
        return giftDiaAdapter;
    }

    public SelectGiftTypeWindow(Context context) {
        super(context);
        this.context = context;
    }

    public ListView getMyGrid() {
        return myGrid;
    }


    public SelectGiftTypeWindow(Activity context) {
        super(context);
        this.context = context;
        mMenuView = LayoutInflater.from(context).inflate(R.layout.dialog_room_select_gift_type, null);
        myGrid = mMenuView.findViewById(R.id.myGrid);
//        noLike = mMenuView.findViewById(R.id.nolike_btn);

        // 设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(RelativeLayout.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(QMUIDisplayHelper.dpToPx(110));
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

        giftDiaAdapter = new GiftDiaAdapter(context);
        myGrid.setAdapter(giftDiaAdapter);
        List<String> list = new ArrayList<>();
        list.add("礼物");
        list.add("宝石");
        list.add("我的");
        giftDiaAdapter.getList_adapter().addAll(list);
        giftDiaAdapter.notifyDataSetChanged();
    }

    public void dismiss() {
        super.dismiss();
        MyBaseArmActivity myBaseArmActivity = (MyBaseArmActivity) context;
        WindowManager.LayoutParams params = myBaseArmActivity.getWindow().getAttributes();
        params.alpha = 1f;
        myBaseArmActivity.getWindow().setAttributes(params);
    }

}
