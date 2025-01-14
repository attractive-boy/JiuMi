package com.miniapp.talks.popup;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jess.arms.utils.LogUtils;
import com.kongzue.dialog.interfaces.OnDialogButtonClickListener;
import com.kongzue.dialog.util.BaseDialog;
import com.kongzue.dialog.util.DialogSettings;
import com.kongzue.dialog.v3.MessageDialog;
import com.miniapp.talks.R;
import com.miniapp.talks.activity.room.AdminHomeActivity;
import com.miniapp.talks.adapter.BoxExchangeAdapter;
import com.miniapp.talks.app.utils.RxUtils;
import com.miniapp.talks.base.MyBaseArmActivity;
import com.miniapp.talks.bean.BoxExchangeBean;
import com.miniapp.talks.bean.CommentBean;
import com.miniapp.talks.bean.FirstEvent;
import com.miniapp.talks.service.CommonModel;
import com.miniapp.talks.utils.AToast;
import com.miniapp.talks.utils.BToast;
import com.miniapp.talks.utils.MyUtil;
import com.miniapp.talks.utils.SnapPageScrollListener;
import com.miniapp.talks.utils.ToastUtil;
import com.miniapp.talks.view.ShapeTextView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

import static com.miniapp.talks.utils.Constant.DUIHUAN;
import static com.miniapp.talks.utils.Constant.KAIQICPWEI;

//宝箱兑换弹窗
public class BoxExchangeWindow extends PopupWindow {
    private AdminHomeActivity mContext;
    private View mMenuView;
    private int pos;

    private LinearLayout quanbu;
    private TextView boxIntegral;
    private TextView boxNeedIntegral;
    private ImageView leftBtn;
    private ImageView rightBtn;
    private RecyclerView boxGoods;
    private ShapeTextView okBtn;
    private BoxExchangeAdapter mAdapter;
    private CommonModel commonModel;
    private RxErrorHandler mRxErrorHandler;
    private List<BoxExchangeBean.DataBean> mDataList;

    public BoxExchangeWindow(Activity context, CommonModel commonModel, RxErrorHandler rxErrorHandler, String money) {
        super(context);
        mContext = (AdminHomeActivity) context;
        this.commonModel = commonModel;
        this.mRxErrorHandler = rxErrorHandler;
        mMenuView = LayoutInflater.from(mContext).inflate(R.layout.box_exchange_window, null);
        boxIntegral = mMenuView.findViewById(R.id.box_integral);
        boxNeedIntegral = mMenuView.findViewById(R.id.box_need_integral);
        leftBtn = mMenuView.findViewById(R.id.box_left_btn);
        rightBtn = mMenuView.findViewById(R.id.box_right_btn);
        boxGoods = mMenuView.findViewById(R.id.box_goods);
        okBtn = mMenuView.findViewById(R.id.ok_btn);
        this.setContentView(mMenuView);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        ColorDrawable dw = new ColorDrawable(0x00000000);
        this.setBackgroundDrawable(dw);
        //设置透明
        WindowManager.LayoutParams params = context.getWindow().getAttributes();
        Display d = context.getWindowManager().getDefaultDisplay();
        params.alpha = 0.7f;
        this.setWidth(d.getWidth() - MyUtil.dip2px(context, 126));
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        context.getWindow().setAttributes(params);

        boxIntegral.setText(money);

        mAdapter = new BoxExchangeAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        boxGoods.setLayoutManager(linearLayoutManager);
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(boxGoods);
        boxGoods.setAdapter(mAdapter);
        setData();

        boxGoods.setOnScrollListener(new SnapPageScrollListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                pos = position;
                boxNeedIntegral.setText(String.valueOf(mAdapter.getData().get(position).getScore()));
            }
        });
        leftBtn.setOnClickListener(v -> {
            if (pos <= 0) {

            } else {
                pos--;
                boxGoods.smoothScrollToPosition(pos);
            }
        });
        rightBtn.setOnClickListener(v -> {
            if (pos > mDataList.size()) {

            } else {
                pos++;
                boxGoods.smoothScrollToPosition(pos);
            }
        });

        okBtn.setOnClickListener(v -> {
            MessageDialog.build(mContext)
                    .setStyle(DialogSettings.STYLE.STYLE_IOS)
                    .setTheme(DialogSettings.THEME.LIGHT)
                    .setTitle("")
                    .setMessage("兑换后将扣除相应积分，是否兑换")
                    .setOkButton("确定", new OnDialogButtonClickListener() {
                        @Override
                        public boolean onClick(BaseDialog baseDialog, View v) {
                            baseDialog.doDismiss();
                            actionAwardExchange(String.valueOf(mAdapter.getData().get(pos).getId()));
                            return false;
                        }

                    })
                    .setCancelButton("取消", new OnDialogButtonClickListener() {
                        @Override
                        public boolean onClick(BaseDialog baseDialog, View v) {
                            baseDialog.doDismiss();
                            return false;
                        }
                    })
                    .show();
        });
    }


    public void dismiss() {
        super.dismiss();
        MyBaseArmActivity myBaseArmActivity = mContext;
        WindowManager.LayoutParams params = myBaseArmActivity.getWindow().getAttributes();
        params.alpha = 1f;
        myBaseArmActivity.getWindow().setAttributes(params);
    }

    //获取礼物列表
    private void setData() {
        RxUtils.loading(commonModel.getAwardWaresList("xx"), mContext)
                .subscribe(new ErrorHandleSubscriber<BoxExchangeBean>(mContext.mErrorHandler) {
                    @Override
                    public void onNext(BoxExchangeBean boxExchangeBean) {
                        if (boxExchangeBean.getCode() == 1) {
                            mDataList = boxExchangeBean.getData();
                            mAdapter.setNewData(mDataList);
                        }
                    }
                });
    }

    //兑换
    private void actionAwardExchange(String waresId) {
        RxUtils.loading(commonModel.actionAwardExchange(waresId), mContext)
                .subscribe(new ErrorHandleSubscriber<CommentBean>(mContext.mErrorHandler) {
                    @Override
                    public void onNext(CommentBean commentBean) {
                        AToast.showText(mContext, commentBean.getMessage());
                        if (commentBean.getCode() == 1) {
                            dismiss();
                            EventBus.getDefault().post(new FirstEvent("兑换成功", DUIHUAN));
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        AToast.showText(mContext, t.getMessage());
                    }
                });
    }
}
