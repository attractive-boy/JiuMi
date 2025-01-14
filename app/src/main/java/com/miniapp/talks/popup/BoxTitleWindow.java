package com.miniapp.talks.popup;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.constraint.ConstraintLayout;
import android.text.Html;
import android.text.Spanned;
import android.text.method.ScrollingMovementMethod;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jess.arms.utils.LogUtils;
import com.miniapp.talks.R;
import com.miniapp.talks.activity.room.AdminHomeActivity;
import com.miniapp.talks.app.utils.RxUtils;
import com.miniapp.talks.base.MyBaseArmActivity;
import com.miniapp.talks.bean.BXShuoMingTextBean;
import com.miniapp.talks.service.CommonModel;
import com.miniapp.talks.utils.MyUtil;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

//宝箱文字说明
public class BoxTitleWindow extends PopupWindow {
    private View mMenuView;
    private AdminHomeActivity context;
    private TextView titText;
    private CommonModel commonModel;
    private RxErrorHandler rxErrorHandler;

    public TextView getTitText() {
        return titText;
    }

    public BoxTitleWindow(Activity context, ConstraintLayout view, CommonModel commonModel, RxErrorHandler rxErrorHandler) {
        super(context);
        this.context = (AdminHomeActivity) context;
        this.commonModel = commonModel;
        this.rxErrorHandler = rxErrorHandler;
        mMenuView = LayoutInflater.from(context).inflate(R.layout.box_rule_popu, null);
        titText = mMenuView.findViewById(R.id.box_tit_text);
        titText.setMovementMethod(ScrollingMovementMethod.getInstance());
        // 设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);

        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x00000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
        //设置透明
        WindowManager.LayoutParams params = context.getWindow().getAttributes();
        params.alpha = 0.7f;
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(view.getMeasuredWidth() - MyUtil.dip2px(context, 50));
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(view.getMeasuredHeight() - MyUtil.dip2px(context, 52));
//        params.width = d.getWidth() - MyUtil.dip2px(context, 120);
        context.getWindow().setAttributes(params);

        getRewardInfo();
    }

    public void dismiss() {
        super.dismiss();
        MyBaseArmActivity myBaseArmActivity = (MyBaseArmActivity) context;
        WindowManager.LayoutParams params = myBaseArmActivity.getWindow().getAttributes();
        params.alpha = 1f;
        myBaseArmActivity.getWindow().setAttributes(params);
    }

    //获取数据
    private void getRewardInfo() {
        RxUtils.loading(commonModel.getRewardInfo("xx"), context)
                .subscribe(new ErrorHandleSubscriber<BXShuoMingTextBean>(context.mErrorHandler) {
                    @Override
                    public void onNext(BXShuoMingTextBean bxShuoMingTextBean) {
                        String s = bxShuoMingTextBean.getData().getRemark().replaceAll("<\\/p ><p>", "\n");
                        String ss = s.replaceAll("<\\/p><p>", "\n");
                        String replace = ss.replace("<p>", "");
                        String replace1 = replace.replaceAll("<\\/p >", "");
                        titText.setText(replace1);
                    }
                });
    }
}
