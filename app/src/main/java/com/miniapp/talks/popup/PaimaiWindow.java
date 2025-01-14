package com.miniapp.talks.popup;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.miniapp.talks.R;
import com.miniapp.talks.activity.room.AdminHomeActivity;
import com.miniapp.talks.adapter.LocalMusciAdapter;
import com.miniapp.talks.adapter.PaimaiAdapter;
import com.miniapp.talks.app.utils.RxUtils;
import com.miniapp.talks.base.MyBaseArmActivity;
import com.miniapp.talks.base.UserManager;
import com.miniapp.talks.bean.BaseBean;
import com.miniapp.talks.bean.FirstEvent;
import com.miniapp.talks.bean.GiftListBean;
import com.miniapp.talks.bean.MessageBean;
import com.miniapp.talks.bean.Microphone;
import com.miniapp.talks.bean.WaitList;
import com.miniapp.talks.service.CommonModel;
import com.miniapp.talks.utils.Constant;
import com.miniapp.talks.utils.ToastUtil;
import com.miniapp.talks.view.ShapeTextView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;


/**
 * 排麦,popupwindow和dialog不能嵌套viewpager使用，此处是一个巨坑，草特大爷
 */
@SuppressLint("ValidFragment")
public class PaimaiWindow extends PopupWindow {


    @BindView(R.id.textNum)
    TextView textNum;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.btn_ok)
    ShapeTextView btnOk;
    private String id = "";//礼物id
    private AdminHomeActivity context;
    private CommonModel commonModel;
    private String uid;
    private PaimaiAdapter paimaiAdapter;
    public PaimaiWindow(AdminHomeActivity context,String uid,
                        CommonModel commonModel) {
        super(context);
        this.context = context;
        this.commonModel = commonModel;
        this.uid = uid;
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_paimai, null);
        ButterKnife.bind(this, view);
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
        this.setBackgroundDrawable(dw);
        //设置透明
        WindowManager.LayoutParams params = context.getWindow().getAttributes();
        params.alpha = 0.7f;
        context.getWindow().setAttributes(params);
        paimaiAdapter = new PaimaiAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(paimaiAdapter);
        loadData();
    }

    private void loadData() {
        RxUtils.loading(commonModel.waitList(uid,String.valueOf(UserManager.getUser().getUserId())))
                .subscribe(new ErrorHandleSubscriber<WaitList>(context.mErrorHandler) {
                    @Override
                    public void onNext(WaitList waitList) {
                        paimaiAdapter.setNewData(waitList.getData().getData());
                        textNum.setText("当前麦序" + waitList.getData().getTotal() + "人");
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

    @OnClick({R.id.btn_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_ok:
                RxUtils.loading(commonModel.delWait(String.valueOf(UserManager.getUser().getUserId())))
                        .subscribe(new ErrorHandleSubscriber<BaseBean>(context.mErrorHandler) {
                            @Override
                            public void onNext(BaseBean giftListBean) {
                                dismiss();
                            }
                        });
                break;
        }
    }

}
