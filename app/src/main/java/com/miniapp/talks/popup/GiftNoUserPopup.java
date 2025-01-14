package com.miniapp.talks.popup;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.jess.arms.http.imageloader.glide.GlideArms;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.LogUtils;
import com.miniapp.talks.R;
import com.miniapp.talks.activity.ChargeActivity;
import com.miniapp.talks.activity.room.AdminHomeActivity;
import com.miniapp.talks.adapter.LiWuAdapter;
import com.miniapp.talks.adapter.LiWuBaoShiAdapter;
import com.miniapp.talks.adapter.LiWuBeiBaoAdapter;
import com.miniapp.talks.app.converter.ApiIOException;
import com.miniapp.talks.app.utils.RxUtils;
import com.miniapp.talks.base.MyBaseArmActivity;
import com.miniapp.talks.base.UserManager;
import com.miniapp.talks.bean.FirstEvent;
import com.miniapp.talks.bean.GiftListBean;
import com.miniapp.talks.bean.LoginData;
import com.miniapp.talks.bean.MessageBean;
import com.miniapp.talks.bean.MessageEvent;
import com.miniapp.talks.bean.Microphone;
import com.miniapp.talks.bean.SendGemResult;
import com.miniapp.talks.bean.SendGiftResult;
import com.miniapp.talks.bean.StateMessage;
import com.miniapp.talks.bean.User;
import com.miniapp.talks.service.CommonModel;
import com.miniapp.talks.utils.Arith;
import com.miniapp.talks.utils.Constant;
import com.miniapp.talks.utils.ToastUtil;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.khrystal.library.widget.ItemViewMode;

public class GiftNoUserPopup extends PopupWindow {


    @BindView(R.id.imgSong)
    ImageView imgSong;
    @BindView(R.id.liwu_name)
    TextView liwuName;
    @BindView(R.id.liwu)
    TextView liwu;
    @BindView(R.id.baoshi)
    TextView baoshi;
    @BindView(R.id.beibao)
    TextView beibao;
    @BindView(R.id.gift_recyclerview)
    RecyclerView giftRecyclerview;
    @BindView(R.id.mizuan)
    TextView mizuan;
    @BindView(R.id.liwushuliang)
    TextView liwushuliang;
    @BindView(R.id.zengsong)
    TextView zengsong;
    @BindView(R.id.img_next)
    ImageView imgNext;

    private boolean mIsChact = false;

    private MyBaseArmActivity context;
    private CommonModel commonModel;
    private ItemViewMode mItemViewMode;
    private LinearLayoutManager mLayoutManager;
    private GridLayoutManager mGlm;
    private List<Microphone.DataBean.MicrophoneBean> mMicrophone;
    private GiftListBean giftListBean;
    private Microphone.DataBean.MicrophoneBean microphoneBean;
    private String fromUserId;
    private String nickName;
    private int mPosition;
    private ImageView imgGift;

    LiWuAdapter mGiftAdapter;

    LiWuBaoShiAdapter mStoneGiftAdapter;

    LiWuBeiBaoAdapter mPackageGiftAdapter;

    private int mCurrentGiftType = 0;
    private String mId = "";//礼物id
    private String mLiWuName = "";//选中礼物的nick；

    public TextView getTextNum() {
        return liwushuliang;
    }

    int[] mLocation = new int[2];

    public GiftNoUserPopup(MyBaseArmActivity context,
                           String fromUserId, String nickName,
                           CommonModel commonModel, GiftListBean giftListBean,
                           Microphone.DataBean.MicrophoneBean microphoneBean, ImageView imgGift, String headerUrl) {
        super(context);
        this.context = context;
        this.mMicrophone = mMicrophone;
        this.commonModel = commonModel;
        this.giftListBean = giftListBean;
        this.microphoneBean = microphoneBean;
        this.fromUserId = fromUserId;
        this.nickName = nickName;
        this.imgGift = imgGift;

        View view = LayoutInflater.from(context).inflate(R.layout.gift_no_user_popup, null);
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
//        WindowManager.LayoutParams params = context.getWindow().getAttributes();
//        params.alpha = 1.0f;
//        context.getWindow().setAttributes(params);

        EventBus.getDefault().register(this);

        mizuan.setText(giftListBean.getData().getMizuan());
        liwu.setSelected(true);
        baoshi.setSelected(false);
        beibao.setSelected(false);


        GlideArms.with(context)
                .load(headerUrl)
                .placeholder(R.mipmap.no_tu)
                .error(R.mipmap.no_tu)
                .circleCrop()
                .into(imgSong);

        initBottomRecycler(context);
    }

    private void initBottomRecycler(MyBaseArmActivity context) {
        mGiftAdapter = new LiWuAdapter();
        mGlm = new GridLayoutManager(context, 4);
        giftRecyclerview.setLayoutManager(mGlm);
        giftRecyclerview.setAdapter(mGiftAdapter);
        mGiftAdapter.setNewData(giftListBean.getData().getGifts());

        setGiftAdapterItemClick();
        mStoneGiftAdapter = new LiWuBaoShiAdapter();
        mGlm = new GridLayoutManager(context, 4);
        giftRecyclerview.setLayoutManager(mGlm);

        mPackageGiftAdapter = new LiWuBeiBaoAdapter();
        mGlm = new GridLayoutManager(context, 4);
        giftRecyclerview.setLayoutManager(mGlm);

        for (int a = 0; a < mGiftAdapter.getData().size(); a++) {
            if (mGiftAdapter.getData().get(a).getIs_check() == 1) {
                mLiWuName = mGiftAdapter.getData().get(a).getName();
                liwuName.setText(liwushuliang.getText().toString() + "个" + mGiftAdapter.getData().get(a).getName());
                break;
            }
        }
    }

    //设置礼物adapter的item点击事件
    private void setGiftAdapterItemClick() {
        mGiftAdapter.setOnItemClickListener((adapter, view, position) -> {
            List<GiftListBean.DataBean.GiftsBean> data = mGiftAdapter.getData();
            for (int a = 0; a < data.size(); a++) {
                data.get(a).setIs_check(0);
            }
            data.get(position).setIs_check(1);
            mGiftAdapter.setNewData(data);
            mPosition = position;
            mLiWuName = mGiftAdapter.getData().get(position).getName();
            liwuName.setText(liwushuliang.getText().toString() + "个" + mGiftAdapter.getData().get(position).getName());
        });
    }

    //设置宝石Adapter的item点击事件
    private void setBaoShiAdapterItemClick() {
        mStoneGiftAdapter.setOnItemClickListener((adapter, view, position) -> {
            List<GiftListBean.DataBean.BaoshiBean> data = mStoneGiftAdapter.getData();
            for (int a = 0; a < data.size(); a++) {
                data.get(a).setIs_check(0);
            }
            data.get(position).setIs_check(1);
            mStoneGiftAdapter.setNewData(data);
            mPosition = position;
            liwuName.setText(liwushuliang.getText().toString() + "个" + mStoneGiftAdapter.getData().get(position).getName());
        });
    }

    //设置背包Adapter的item点击事件
    private void setBeiBaoAdapterItemClick() {
        mPackageGiftAdapter.setOnItemClickListener((adapter, view, position) -> {
            List<GiftListBean.DataBean.MyWaresBean> data = mPackageGiftAdapter.getData();
            for (int a = 0; a < data.size(); a++) {
                data.get(a).setIs_check(0);
            }
            data.get(position).setIs_check(1);
            mPackageGiftAdapter.setNewData(data);
            mPosition = position;
            liwuName.setText(liwushuliang.getText().toString() + "个" + mPackageGiftAdapter.getData().get(position).getName());
        });
    }

    @OnClick({R.id.liwu, R.id.baoshi, R.id.beibao, R.id.liwushuliang, R.id.mizuan, R.id.zengsong})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.liwu:
                liwu.setSelected(true);
                baoshi.setSelected(false);
                beibao.setSelected(false);
                giftRecyclerview.setAdapter(mGiftAdapter);
                mGiftAdapter.setNewData(giftListBean.getData().getGifts());
                if (mCurrentGiftType == 0) {
                    return;
                }
                mCurrentGiftType = 0;
                for (int a = 0; a < mGiftAdapter.getData().size(); a++) {
                    if (mGiftAdapter.getData().get(a).getIs_check() == 1) {
                        mLiWuName = mGiftAdapter.getData().get(a).getName();
                        liwuName.setText(liwushuliang.getText().toString() + "个" + mGiftAdapter.getData().get(a).getName());
                        mPosition = a;
                        break;
                    }
                }
                break;
            case R.id.baoshi:
                baoshi.setSelected(true);
                liwu.setSelected(false);
                beibao.setSelected(false);
                giftRecyclerview.setAdapter(mStoneGiftAdapter);
                mStoneGiftAdapter.setNewData(giftListBean.getData().getBaoshi());
                setBaoShiAdapterItemClick();
                if (mCurrentGiftType == 1) {
                    return;
                }
                mCurrentGiftType = 1;

                for (int a = 0; a < mStoneGiftAdapter.getData().size(); a++) {
                    if (mStoneGiftAdapter.getData().get(a).getIs_check() == 1) {
                        mLiWuName = mStoneGiftAdapter.getData().get(a).getName();
                        liwuName.setText(liwushuliang.getText().toString() + "个" + mStoneGiftAdapter.getData().get(a).getName());
                        mPosition = a;
                        break;
                    }
                }
                break;
            case R.id.beibao:
                beibao.setSelected(true);
                liwu.setSelected(false);
                baoshi.setSelected(false);
                giftRecyclerview.setAdapter(mPackageGiftAdapter);
                mPackageGiftAdapter.setNewData(giftListBean.getData().getMy_wares());
                setBeiBaoAdapterItemClick();
                if (mCurrentGiftType == 2) {
                    return;
                }
                mCurrentGiftType = 2;
                for (int a = 0; a < mPackageGiftAdapter.getData().size(); a++) {
                    if (mPackageGiftAdapter.getData().get(a).getIs_check() == 1) {
                        mLiWuName = mPackageGiftAdapter.getData().get(a).getName();
                        liwuName.setText(liwushuliang.getText().toString() + "个" + mPackageGiftAdapter.getData().get(a).getName());
                        mPosition = a;
                        break;
                    }
                }
                break;
            case R.id.liwushuliang:  //选择送礼物的数量

                liwushuliang.setSelected(true);

                createRotateAnimator(imgNext, 0, 180).start();

                Gift2NumberPopup gda = new Gift2NumberPopup(context);
                gda.getmMenuView().measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
                int popupWidth = gda.getmMenuView().getMeasuredWidth();
                int popupHeight = gda.getmMenuView().getMeasuredHeight();
                int[] location = new int[2];
                imgGift.getLocationOnScreen(location);
                gda.showAtLocation(imgGift, Gravity.NO_GRAVITY,
                        (location[0] + imgGift.getWidth() / 2) - popupWidth / 2 + 350, location[1] - popupHeight + 190);
                gda.getMyGrid().setOnItemClickListener((parent, view1, position, id) -> {
                    if (position == 0) {
                        LiWuShuLiangPopup liWuShuLiangPopup = new LiWuShuLiangPopup(context);
                        liWuShuLiangPopup.showAtLocation(imgGift, Gravity.BOTTOM, 0, 0);
                        liWuShuLiangPopup.getBtn_ok().setText("确定");
                        liWuShuLiangPopup.getBtn_ok().setOnClickListener(v -> {
                            String str = liWuShuLiangPopup.getEditMessage().getText().toString();
                            liwushuliang.setText("x" + str);
                            liwuName.setText(liwushuliang.getText().toString() + "个" + mLiWuName);
                            liWuShuLiangPopup.dismiss();
                        });

                        liWuShuLiangPopup.setOnDismissListener(new OnDismissListener() {
                            @Override
                            public void onDismiss() {
                                WindowManager.LayoutParams params = context.getWindow().getAttributes();
                                params.alpha = 1f;
                                context.getWindow().setAttributes(params);
                                liwushuliang.setSelected(false);
                                createRotateAnimator(imgNext, 180, 0).start();
                            }
                        });
                    } else {
                        liwushuliang.setText(gda.getGiftDiaAdapter().getList_adapter().get(position));
                        liwuName.setText(liwushuliang.getText().toString() + "个" + mLiWuName);
                    }
                    gda.dismiss();
                });

                gda.setOnDismissListener(() -> {
                    liwushuliang.setSelected(false);
                    createRotateAnimator(imgNext, 180, 0).start();
                });
                break;
            case R.id.mizuan:  //充值
                Intent intent = new Intent(context, ChargeActivity.class);
                intent.putExtra("type", 1);
                ArmsUtils.startActivity(intent);
                if (context instanceof AdminHomeActivity) {
                    context.overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
                }
                break;
            case R.id.zengsong:
                switch (mCurrentGiftType) {
                    case 0://礼物
                        for (int a = 0; a < mGiftAdapter.getData().size(); a++) {
                            if (mGiftAdapter.getData().get(a).getIs_check() == 1) {
                                mId = mGiftAdapter.getData().get(a).getId();
                                break;
                            }
                        }
                        View currView = mGiftAdapter.getViewByPosition(giftRecyclerview, mPosition, R.id.item_img);
                        if (currView != null) {
                            currView.getLocationOnScreen(mLocation);
                        }
                        sendGift(false);
                        GiftListBean.DataBean.GiftsBean giftsBean = giftListBean.getData().getGifts().get(mPosition);
                        if (TextUtils.equals("2", giftsBean.getType())) {//有全屏特效
                            dismiss();
                        }
                        LogUtils.debugInfo("====单对单礼物ID", mId);
                        break;
                    case 1://宝石
                        for (int a = 0; a < mStoneGiftAdapter.getData().size(); a++) {
                            if (mStoneGiftAdapter.getData().get(a).getIs_check() == 1) {
                                mId = mStoneGiftAdapter.getData().get(a).getId();
                                break;
                            }
                        }
                        View viewStone = mStoneGiftAdapter.getViewByPosition(giftRecyclerview, mPosition, R.id.item_img);
                        if (viewStone != null) {
                            viewStone.getLocationOnScreen(mLocation);
                        }
                        sendGemStone();
                        dismiss();
                        LogUtils.debugInfo("====单对单宝石ID", mId);
                        break;
                    case 2://我的
                        for (int a = 0; a < mPackageGiftAdapter.getData().size(); a++) {
                            if (mPackageGiftAdapter.getData().get(a).getIs_check() == 1) {
                                mId = mPackageGiftAdapter.getData().get(a).getId();
                                break;
                            }
                        }
                        int type = giftListBean.getData().getMy_wares().get(mPosition).getWares_type();
                        if (type == 1) {//1宝石 2礼物 3 卡片
                            View viewPG = mPackageGiftAdapter.getViewByPosition(giftRecyclerview, mPosition, R.id.item_img);
                            if (viewPG != null) {
                                viewPG.getLocationOnScreen(mLocation);
                            }
                            sendGemStonePackage();
                            dismiss();
                        } else if (type == 2) {
                            View currView1 = mPackageGiftAdapter.getViewByPosition(giftRecyclerview, mPosition, R.id.item_img);
                            if (currView1 != null) {
                                currView1.getLocationOnScreen(mLocation);
                            }
                            sendGift(true);
                            GiftListBean.DataBean.MyWaresBean giftsBean1 = giftListBean.getData().getMy_wares().get(mPosition);
                            if (giftsBean1.getType() == 2) {//有全屏特效
                                dismiss();
                            }
                        } else if (type == 3) {
                            sendByk();
                            dismiss();
                        }

                        LogUtils.debugInfo("====单对单背包ID", mId);
                        break;
                }
                break;
        }
    }

    /**
     * 发送礼物
     */
    private void sendGift(boolean isMyPackage) {

        String numbers = liwushuliang.getText().toString().replace("x", "").trim();
        RxUtils.loading(commonModel.gift_queue_six(
                mId, microphoneBean.getUser_id(),
                fromUserId, numbers
        )).subscribe(new ErrorHandleSubscriber<SendGiftResult>(context.mErrorHandler) {
            @Override
            public void onNext(SendGiftResult baseBean) {
//                dismiss();
                LogUtils.debugInfo("====单对单送的礼物ID", mId);
                if (baseBean == null || baseBean.getData() == null) {
                    return;
                }
                ToastUtil.showToast(context, "发送成功");

                LoginData loginData = UserManager.getUser();
                MessageBean messageBean = new MessageBean();
                messageBean.setUser_id(String.valueOf(loginData.getUserId()));
                messageBean.setNickName(loginData.getNickname());
//                messageBean.show_img = giftListBean.getData().getGifts().get(mPosition).getShow_img();
//                messageBean.show_gif_img = giftListBean.getData().getGifts().get(mPosition).getShow_img2();
//                messageBean.type = giftListBean.getData().getGifts().get(mPosition).getType();
//                messageBean.giftNum = textNum.getText().toString().replace("x","").trim();
//                messageBean.e_name = giftListBean.getData().getGifts().get(mPosition).e_name;
//                messageBean.setMessageType("4");

                if (!isMyPackage) {
                    View view = mGiftAdapter.getViewByPosition(giftRecyclerview, mPosition, R.id.item_img);
                    if (view != null) {
                        view.getLocationOnScreen(messageBean.location);
                        if (messageBean.location[0] == 0 || messageBean.location[1] == 0) {
                            messageBean.location = mLocation;
                        }
                    } else {
                        int width = QMUIDisplayHelper.getScreenWidth(context);
                        int heigth = QMUIDisplayHelper.getScreenHeight(context);
                        messageBean.location[0] = width / 2;
                        messageBean.location[1] = heigth - 100;
                    }

                    GiftListBean.DataBean.GiftsBean giftsBean = giftListBean.getData().getGifts().get(mPosition);
                    messageBean.show_img = giftsBean.getShow_img();
                    messageBean.show_gif_img = giftsBean.getShow_img2();
                    messageBean.type = giftsBean.getType();
                    messageBean.giftNum = liwushuliang.getText().toString().replace("x", "").trim();
                    messageBean.e_name = giftsBean.e_name;
                    if (!TextUtils.equals("2", giftsBean.getType())) {//没有全屏特效情况下，更新米钻,有特效已经关闭弹框了，不用更新

                        getGiftList();

                    } else {
                        int width = QMUIDisplayHelper.getScreenWidth(context);
                        int heigth = QMUIDisplayHelper.getScreenHeight(context);
                        messageBean.location[0] = width / 2;
                        messageBean.location[1] = heigth - 100;
                    }
                } else {
                    if (giftListBean.getData().getMy_wares().size() == 0) {
                        dismiss();
                        return;
                    }
                    View view = mPackageGiftAdapter.getViewByPosition(giftRecyclerview, mPosition, R.id.item_img);
                    if (view != null) {
                        view.getLocationOnScreen(messageBean.location);
                        if (messageBean.location[0] == 0 || messageBean.location[1] == 0) {
                            messageBean.location = mLocation;
                        }
                    } else {
                        int width = QMUIDisplayHelper.getScreenWidth(context);
                        int heigth = QMUIDisplayHelper.getScreenHeight(context);
                        messageBean.location[0] = width / 2;
                        messageBean.location[1] = heigth - 100;
                    }

                    GiftListBean.DataBean.MyWaresBean myWaresBean = giftListBean.getData().getMy_wares().get(mPosition);
                    messageBean.show_img = myWaresBean.getShow_img();
                    messageBean.show_gif_img = myWaresBean.getShow_img2();
                    messageBean.type = myWaresBean.getType() + "";
                    messageBean.giftNum = liwushuliang.getText().toString().replace("x", "").trim();
                    if (myWaresBean.getType() != 2) {//我的背包里面的礼物数量要减少，没有全屏特效情况下
                        int number = myWaresBean.getNum();

                        int total = Arith.strToInt(numbers);

                        number -= total;

                        if (number <= 0) {
                            giftListBean.getData().getMy_wares().remove(mPosition);
                            if (giftListBean.getData().getMy_wares().size() == 0) {
                                dismiss();
                            } else {
                                mPackageGiftAdapter.notifyDataSetChanged();
                            }
//                                dismiss();
                        }
                        myWaresBean.setNum(number);
                        myWaresBean.setPrice("x" + number);
                        mPackageGiftAdapter.notifyDataSetChanged();


                    } else {
                        int width = QMUIDisplayHelper.getScreenWidth(context);
                        int heigth = QMUIDisplayHelper.getScreenHeight(context);
                        messageBean.location[0] = width / 2;
                        messageBean.location[1] = heigth - 100;
                    }
//                    messageBean.e_name = giftListBean.getData().getMy_wares().get(mPosition).gete;
                }

                messageBean.setMessageType("4");

                List<User> sendDataList = baseBean.getData().getUsers();
                if (sendDataList.size() != 1) {
                    return;
                }

                List<MessageBean.Data> dataList = new ArrayList<>();

//                for (int i = 0;i<listNew.size();i++){
//                    Microphone.DataBean.MicrophoneBean items = listNew.get(i);
//                    MessageBean.Data data = new MessageBean.Data();
//                    data.nickname = items.getNickname();
//                    data.userId = items.getUser_id();
//                    data.toNick_color = sendDataList.get(i).getNick_color();
//                    dataList.add(data);
//                }

                MessageBean.Data data = new MessageBean.Data();
                data.nickname = nickName;
                data.userId = fromUserId;
                data.toNick_color = sendDataList.get(0).getNick_color();
                dataList.add(data);

                messageBean.userInfo = dataList;
                messageBean.pushUser = baseBean.getData().getPush();
                EventBus.getDefault().post(new FirstEvent(messageBean, Constant.FASONGMAIXULIWU));
            }

            @Override
            public void onError(Throwable t) {
                super.onError(t);
//                dismiss();

                try{
                    ApiIOException apiIOException = (ApiIOException) t;
                    if(apiIOException.code.equals("5")){
                        return;
                    } else {
                        new MaterialDialog.Builder(context)
                                .title("您的米钻余额不足，请马上去充值？")
                                .content("")
                                .positiveText("充值")
                                .negativeText("取消")
                                .onPositive((dialog, which) -> {
                                    Intent intent = new Intent(context, ChargeActivity.class);
                                    intent.putExtra("type", 1);
                                    ArmsUtils.startActivity(intent);
                                    if (context instanceof AdminHomeActivity) {
                                        context.overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
                                    }
                                })
                                .show();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
    }


    /**
     * 发送宝石
     */
    private void sendGemStone() {

        RxUtils.loading(commonModel.send_baoshi(
                mId, microphoneBean.getUser_id(),
                String.valueOf(UserManager.getUser().getToken()),
                fromUserId, liwushuliang.getText().toString().replace("x", "").trim()
        )).subscribe(new ErrorHandleSubscriber<SendGemResult>(context.mErrorHandler) {
            @Override
            public void onNext(SendGemResult baseBean) {
//                dismiss();
                ToastUtil.showToast(context, "发送成功");
                if (baseBean == null || baseBean.getData() == null || baseBean.getData().size() == 0) {
                    return;
                }

                LoginData loginData = UserManager.getUser();

                MessageBean messageBean = new MessageBean();
                View view = mStoneGiftAdapter.getViewByPosition(giftRecyclerview, mPosition, R.id.item_img);
                if (view != null) {
                    view.getLocationOnScreen(messageBean.location);
                    if (messageBean.location[0] == 0 || messageBean.location[1] == 0) {
                        messageBean.location = mLocation;
                    }
                } else {
                    int width = QMUIDisplayHelper.getScreenWidth(context);
                    int heigth = QMUIDisplayHelper.getScreenHeight(context);
                    messageBean.location[0] = width / 2;
                    messageBean.location[1] = heigth - 100;
                }

                messageBean.setUser_id(String.valueOf(loginData.getUserId()));
                messageBean.setNickName(loginData.getNickname());
                messageBean.show_img = giftListBean.getData().getBaoshi().get(mPosition).getShow_img();
                messageBean.show_gif_img = giftListBean.getData().getBaoshi().get(mPosition).getShow_img2();
                messageBean.type = giftListBean.getData().getBaoshi().get(mPosition).getType() + "";
                messageBean.giftNum = liwushuliang.getText().toString().replace("x", "").trim();
//                messageBean.e_name = giftListBean.getData().getBaoshi().get(mPosition).e_name;
                messageBean.setMessageType("4");

                List<MessageBean.Data> dataList = new ArrayList<>();

                List<SendGemResult.DataBean> sendDataList = baseBean.getData();
                if (sendDataList.size() != 1) {
                    return;
                }
//                for (int i = 0;i<listNew.size();i++){
//                    Microphone.DataBean.MicrophoneBean items = listNew.get(i);
//                    MessageBean.Data data = new MessageBean.Data();
//                    data.nickname = items.getNickname();
//                    data.userId = items.getUser_id();
//                    data.toNick_color = sendDataList.get(i).getNick_color();
//                    dataList.add(data);
//                }
//                messageBean.userInfo = dataList;

                MessageBean.Data data = new MessageBean.Data();
                data.nickname = nickName;
                data.userId = fromUserId;
                data.toNick_color = sendDataList.get(0).getNick_color();
                dataList.add(data);

                messageBean.userInfo = dataList;


                MessageEvent messageEvent = new MessageEvent();
                messageEvent.setStateMessage(StateMessage.SEND_GEMSTONE);
                Object[] obj = new Object[2];
                obj[0] = messageBean;
                obj[1] = baseBean;
                messageEvent.setObject(obj);
                EventBus.getDefault().post(messageEvent);
            }

            @Override
            public void onError(Throwable t) {
                super.onError(t);
                t.printStackTrace();
//                            ApiIOException apiIOException = (ApiIOException) t;
//                            String code = apiIOException.code;
//                dismiss();
            }
        });
    }

    /**
     * 发送宝石,我的背包
     */
    private void sendGemStonePackage() {

        RxUtils.loading(commonModel.send_baoshi(
                mId, microphoneBean.getUser_id(),
                String.valueOf(UserManager.getUser().getToken()),
                fromUserId, liwushuliang.getText().toString().replace("x", "").trim()
        )).subscribe(new ErrorHandleSubscriber<SendGemResult>(context.mErrorHandler) {
            @Override
            public void onNext(SendGemResult baseBean) {
//                dismiss();
                ToastUtil.showToast(context, "发送成功");
                if (baseBean == null || baseBean.getData() == null || baseBean.getData().size() == 0) {
                    return;
                }

                LoginData loginData = UserManager.getUser();
                MessageBean messageBean = new MessageBean();
                View view = mPackageGiftAdapter.getViewByPosition(giftRecyclerview, mPosition, R.id.item_img);
                if (view != null) {
                    view.getLocationOnScreen(messageBean.location);
                    if (messageBean.location[0] == 0 || messageBean.location[1] == 0) {
                        messageBean.location = mLocation;
                    }
                } else {
                    int width = QMUIDisplayHelper.getScreenWidth(context);
                    int heigth = QMUIDisplayHelper.getScreenHeight(context);
                    messageBean.location[0] = width / 2;
                    messageBean.location[1] = heigth - 100;
                }

                messageBean.setUser_id(String.valueOf(loginData.getUserId()));
                messageBean.setNickName(loginData.getNickname());
                messageBean.show_img = giftListBean.getData().getMy_wares().get(mPosition).getShow_img();
                messageBean.show_gif_img = giftListBean.getData().getMy_wares().get(mPosition).getShow_img2();
                messageBean.type = giftListBean.getData().getMy_wares().get(mPosition).getType() + "";
                messageBean.giftNum = liwushuliang.getText().toString().replace("x", "").trim();
//                messageBean.e_name = giftListBean.getData().getBaoshi().get(mPosition).e_name;
                messageBean.setMessageType("4");

                List<MessageBean.Data> dataList = new ArrayList<>();

                List<SendGemResult.DataBean> sendDataList = baseBean.getData();
                if (sendDataList.size() != 1) {
                    return;
                }
//                for (int i = 0;i<listNew.size();i++){
//                    Microphone.DataBean.MicrophoneBean items = listNew.get(i);
//                    MessageBean.Data data = new MessageBean.Data();
//                    data.nickname = items.getNickname();
//                    data.userId = items.getUser_id();
//                    data.toNick_color = sendDataList.get(i).getNick_color();
//                    dataList.add(data);
//                }
//                messageBean.userInfo = dataList;

                MessageBean.Data data = new MessageBean.Data();
                data.nickname = nickName;
                data.userId = fromUserId;
                data.toNick_color = sendDataList.get(0).getNick_color();
                dataList.add(data);

                messageBean.userInfo = dataList;

                MessageEvent messageEvent = new MessageEvent();
                messageEvent.setStateMessage(StateMessage.SEND_GEMSTONE);
                Object[] obj = new Object[2];
                obj[0] = messageBean;
                obj[1] = baseBean;
                messageEvent.setObject(obj);
                EventBus.getDefault().post(messageEvent);
            }

            @Override
            public void onError(Throwable t) {
                super.onError(t);
                t.printStackTrace();
//                            ApiIOException apiIOException = (ApiIOException) t;
//                            String code = apiIOException.code;
//                dismiss();
            }
        });
    }

    /**
     * 发送爆音卡
     */
    private void sendByk() {

        RxUtils.loading(commonModel.send_byk(
                microphoneBean.getUser_id(),
                String.valueOf(UserManager.getUser().getToken()),
                fromUserId, liwushuliang.getText().toString().replace("x", "").trim()
        )).subscribe(new ErrorHandleSubscriber<SendGemResult>(context.mErrorHandler) {
            @Override
            public void onNext(SendGemResult baseBean) {
//                dismiss();
                ToastUtil.showToast(context, "发送成功");
                if (baseBean == null || baseBean.getData() == null || baseBean.getData().size() == 0) {
                    return;
                }

                LoginData loginData = UserManager.getUser();

                MessageBean messageBean = new MessageBean();
                messageBean.setUser_id(String.valueOf(loginData.getUserId()));
                messageBean.setNickName(loginData.getNickname());
                messageBean.show_img = giftListBean.getData().getMy_wares().get(mPosition).getShow_img();
//                messageBean.show_gif_img = giftListBean.getData().getBaoshi().get(mPosition).getShow_img2();
                messageBean.type = giftListBean.getData().getMy_wares().get(mPosition).getType() + "";
                messageBean.giftNum = liwushuliang.getText().toString().replace("x", "").trim();
//                messageBean.e_name = giftListBean.getData().getBaoshi().get(mPosition).e_name;
                messageBean.setMessageType("4");

                List<MessageBean.Data> dataList = new ArrayList<>();

                List<SendGemResult.DataBean> sendDataList = baseBean.getData();
                if (sendDataList.size() != 1) {
                    return;
                }
//                for (int i = 0;i<listNew.size();i++){
//                    Microphone.DataBean.MicrophoneBean items = listNew.get(i);
//                    MessageBean.Data data = new MessageBean.Data();
//                    data.nickname = items.getNickname();
//                    data.userId = items.getUser_id();
//                    data.toNick_color = sendDataList.get(i).getNick_color();
//                    dataList.add(data);
//                }
//                messageBean.userInfo = dataList;

                MessageBean.Data data = new MessageBean.Data();
                data.nickname = nickName;
                data.userId = fromUserId;
                data.toNick_color = sendDataList.get(0).getNick_color();
                dataList.add(data);

                messageBean.userInfo = dataList;
                MessageEvent messageEvent = new MessageEvent();
                messageEvent.setStateMessage(StateMessage.SEND_GEMSTONE);
                Object[] obj = new Object[2];
                obj[0] = messageBean;
                obj[1] = baseBean;
                messageEvent.setObject(obj);
                EventBus.getDefault().post(messageEvent);
            }

            @Override
            public void onError(Throwable t) {
                super.onError(t);
                t.printStackTrace();
//                            ApiIOException apiIOException = (ApiIOException) t;
//                            String code = apiIOException.code;
//                dismiss();
            }
        });
    }

    //获取礼物列表
    private void getGiftList() {
        RxUtils.loading(commonModel.gift_list(String.valueOf(UserManager.getUser().getUserId())))
                .subscribe(new ErrorHandleSubscriber<GiftListBean>(context.mErrorHandler) {
                    @Override
                    public void onNext(GiftListBean giftListBean) {

                        if (giftListBean != null) {
                            mizuan.setText(giftListBean.getData().getMizuan());
                        }
                    }
                });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveMsg(MessageEvent event) {

        StateMessage stateMessage = event.getStateMessage();

        if (stateMessage.getState() == StateMessage.CLOSE_GIFT_WINDOW.getState()) {//关闭
            dismiss();
        }

    }

    public ObjectAnimator createRotateAnimator(final View target, final float from, final float to) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(target, "rotation", from, to);
        animator.setDuration(300);
        animator.setInterpolator(new LinearInterpolator());
        return animator;
    }

    @Override
    public void dismiss() {
        super.dismiss();
        MyBaseArmActivity myBaseArmActivity = (MyBaseArmActivity) context;
        WindowManager.LayoutParams params = myBaseArmActivity.getWindow().getAttributes();
        params.alpha = 1f;
        myBaseArmActivity.getWindow().setAttributes(params);
        EventBus.getDefault().unregister(this);
    }
}
