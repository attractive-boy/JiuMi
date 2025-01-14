package com.miniapp.talks.popup;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.miniapp.talks.R;
import com.miniapp.talks.activity.room.AdminHomeActivity;
import com.miniapp.talks.adapter.PaimaiAdapter;
import com.miniapp.talks.adapter.ReportAdapter;
import com.miniapp.talks.adapter.RoomDiaAdapter;
import com.miniapp.talks.base.MyBaseArmActivity;
import com.miniapp.talks.bean.DialogBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 举报
 */
public class ReportWindow extends PopupWindow {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private View mMenuView;
    private AdminHomeActivity context;

    public ReportWindow(AdminHomeActivity context) {
        super(context);
        this.context = context;
        mMenuView = LayoutInflater.from(context).inflate(R.layout.dialog_report, null);
        ButterKnife.bind(this, mMenuView);
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
        WindowManager.LayoutParams params = context.getWindow().getAttributes();
        params.alpha = 0.7f;
        context.getWindow().setAttributes(params);

        ReportAdapter reportAdapter = new ReportAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(reportAdapter);

        List<String> list = new ArrayList<>();
        list.add("广告");
        list.add("色情");
        list.add("违法");
        list.add("诈骗");
        list.add("未成年人直播");
        list.add("其他原因");
        list.add("取消");
        reportAdapter.setNewData(list);
        reportAdapter.setOnItemClickListener((adapter, view, position) -> {
            dismiss();
            if(position == list.size() - 1){

            }else {
                if(context instanceof AdminHomeActivity)
                    context.toast("感谢您的支持，我们会尽快处理，请您耐心等待！");
            }
        });
    }

    @Override
    public void dismiss() {
        super.dismiss();
        MyBaseArmActivity myBaseArmActivity = (MyBaseArmActivity) context;
        WindowManager.LayoutParams params = myBaseArmActivity.getWindow().getAttributes();
        params.alpha = 1f;
        myBaseArmActivity.getWindow().setAttributes(params);
    }

}
