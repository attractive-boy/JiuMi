package com.miniapp.talks.popup;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.miniapp.talks.R;
import com.miniapp.talks.adapter.RoomDiaAdapter;
import com.miniapp.talks.bean.DialogBean;
import com.miniapp.talks.view.ShapeTextView;

import java.util.ArrayList;
import java.util.List;


/**
 * 房间设置
 */
public class RoomSetWindow1 extends PopupWindow {


    private  GridView myGrid;
    private View mMenuView;
    private Context context;
    public GridView getMyGrid() {
        return myGrid;
    }

    public RoomSetWindow1(Activity context) {
        super(context);
        this.context = context;
        mMenuView = LayoutInflater.from(context).inflate(R.layout.dialog_room_admin3, null);
        myGrid = (GridView) mMenuView.findViewById(R.id.myGrid);
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

        RoomDiaAdapter roomDiaAdapter = new RoomDiaAdapter(context);
        myGrid.setAdapter(roomDiaAdapter);
        List<DialogBean> list = new ArrayList<>();
        list.add(new DialogBean("设置",R.mipmap.room_gengduo_shezhi));
        list.add(new DialogBean("管理员",R.mipmap.room_gengduo_guanli));
        list.add(new DialogBean("清空消息",R.mipmap.room_gengduo_qingkong));
        roomDiaAdapter.getList_adapter().addAll(list);
        roomDiaAdapter.notifyDataSetChanged();
    }


}
