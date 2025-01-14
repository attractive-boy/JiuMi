package com.miniapp.talks.popup;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.IBinder;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.LogUtils;
import com.miniapp.talks.R;
import com.miniapp.talks.activity.ChargeActivity;
import com.miniapp.talks.activity.room.AdminHomeActivity;
import com.miniapp.talks.adapter.GiftAdapter;
import com.miniapp.talks.adapter.GiftUserAdapter;
import com.miniapp.talks.adapter.PackageGiftAdapter;
import com.miniapp.talks.adapter.StoneGiftAdapter;
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
import com.miniapp.talks.bean.StateMessage;
import com.miniapp.talks.service.CommonModel;
import com.miniapp.talks.utils.Arith;
import com.miniapp.talks.utils.Constant;
import com.miniapp.talks.utils.ToastUtil;
import com.miniapp.talks.view.MiniCircleRecyclerView;
import com.miniapp.talks.view.ShapeTextView;
import com.opensource.svgaplayer.SVGAImageView;
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
import me.khrystal.library.widget.CircleRecyclerView;
import me.khrystal.library.widget.CircularHorizontalMode;
import me.khrystal.library.widget.ItemViewMode;


/**
 * 礼物弹窗,popupwindow和dialog不能嵌套viewpager使用，此处是一个巨坑，草特大爷
 */
@SuppressLint("ValidFragment")
public class GiftWindow extends PopupWindow {

    @BindView(R.id.circleRecyclerUser)
    RecyclerView circleRecyclerUser;
    @BindView(R.id.circleRecycler2)
    RecyclerView circleRecycler;
    @BindView(R.id.textPriceName)
    TextView textPriceName;
    @BindView(R.id.textNum)
    TextView textNum;
    @BindView(R.id.btn_ok)
    ShapeTextView btnOk;
    @BindView(R.id.imgType)
    ImageView imgType;
    @BindView(R.id.textPrice)
    TextView textPrice;
    @BindView(R.id.btn_chongzhi)
    ShapeTextView btnChongzhi;
    @BindView(R.id.ll_input)
    LinearLayout llInput;
    @BindView(R.id.et_num)
    EditText etNum;
    @BindView(R.id.btn_yes)
    Button btnYes;
    @BindView(R.id.img_quan)
    SVGAImageView imgQuan;

    private String mId = "";//礼物id
    public TextView getTextNum() {
        return textNum;
    }

    private AdminHomeActivity context;
    private CommonModel commonModel;
    private ItemViewMode mItemViewMode;
    private LinearLayoutManager mLayoutManager;
    private List<Microphone.DataBean.MicrophoneBean> mMicrophone;
    private GiftListBean giftListBean;
    private Microphone.DataBean.MicrophoneBean microphoneBean;
    private GiftUserAdapter giftUserAdapter;
    private int mPosition;
    private ImageView imgGift;

    GiftAdapter mGiftAdapter;

    StoneGiftAdapter mStoneGiftAdapter;

    PackageGiftAdapter mPackageGiftAdapter;

    private int mCurrentGiftType = 0;

    public GiftWindow(AdminHomeActivity context,
                      List<Microphone.DataBean.MicrophoneBean> mMicrophone,
                      CommonModel commonModel, GiftListBean giftListBean,
                      Microphone.DataBean.MicrophoneBean microphoneBean,ImageView imgGift) {
        super(context);
        this.context = context;
        this.mMicrophone = mMicrophone;
        this.commonModel = commonModel;
        this.giftListBean = giftListBean;
        this.microphoneBean = microphoneBean;
        this.imgGift = imgGift;
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_music, null);
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

        EventBus.getDefault().register(this);

//        List<GiftListBean.DataBean.GiftsBean> giftsBeans = giftListBean.getData().getGifts();

//        List<GiftListBean.DataBean.MyWaresBean> myWaresBeanList = new ArrayList<>();
//
//        GiftListBean.DataBean.MyWaresBean myWaresBean = new GiftListBean.DataBean.MyWaresBean();
//        myWaresBean.setId("6");
//        myWaresBean.setName("iiiiii");
//        myWaresBean.setPrice("133");
//        myWaresBean.setImg("http:\\/\\/47.92.85.75\\/upload\\/gifts\\/png\\/gift_list_sgpp02@2x.png");
//        myWaresBean.setType(2);
//        myWaresBean.setShow_img("http:\\/\\/47.92.85.75\\/upload\\/gifts\\/png\\/gift_sgpp@2x.png");
//        myWaresBean.setShow_img2("http:\\/\\/47.92.85.75\\/upload\\/gifts\\/png\\/gift_sgpp@2x.png");
//        myWaresBeanList.add(myWaresBean);
//        myWaresBeanList.add(myWaresBean);
//        myWaresBeanList.add(myWaresBean);
//        myWaresBeanList.add(myWaresBean);
//        this.giftListBean.getData().setMy_wares(myWaresBeanList);

        initUser(context);
        initBottomRecycler(context);
    }


    private void initUser(AdminHomeActivity context) {
//        circleRecyclerUser.setNestedScrollingEnabled(false);
//        mItemViewMode = new CircularHorizontalMode(100,
//                1.0f / 180.0f * (float) Math.PI, 0.000f, 0.15f);
//        mLayoutManager = new LinearLayoutManager(context,
//                LinearLayoutManager.HORIZONTAL, false) {
//            @Override
//            public boolean canScrollHorizontally() {
//                return false;
//            }
//        };
        mItemViewMode = new CircularHorizontalMode();
        mLayoutManager = new LinearLayoutManager(context,
                LinearLayoutManager.HORIZONTAL, false);
        circleRecyclerUser.setLayoutManager(mLayoutManager);
        List<Microphone.DataBean.MicrophoneBean> listNew = new ArrayList<>();
        listNew.add(microphoneBean);
        listNew.add(mMicrophone.get(mMicrophone.size()-1));

        for (int i = 0; i < mMicrophone.size() - 1; i++) {
            mMicrophone.get(i).isSelect = false;
            listNew.add(mMicrophone.get(i));
        }
        giftUserAdapter = new GiftUserAdapter(context, listNew);
//        circleRecyclerUser.setOnI
        circleRecyclerUser.setAdapter(giftUserAdapter);
    }


    private void initBottomRecycler(AdminHomeActivity context) {
        textPrice.setText(giftListBean.getData().getMizuan());
        mItemViewMode = new CircularHorizontalMode();
        mLayoutManager = new GridLayoutManager(context,
                4,GridLayoutManager.VERTICAL, false);
        circleRecycler.setLayoutManager(mLayoutManager);


        initAdapters();

//        mGiftAdapter = new GiftAdapter(context, circleRecycler, giftListBean.getData().getGifts());

        setGiftAdapterItemClick();

        circleRecycler.setAdapter(mGiftAdapter);
    }

    private void initAdapters() {

        if(giftListBean.getData().getGifts() ==null){
            giftListBean.getData().setGifts(new ArrayList<>());
        }
        mGiftAdapter = new GiftAdapter(context, giftListBean.getData().getGifts(),giftListBean.getData().getGifts().size()<=4?false:true);

        if(giftListBean.getData().getBaoshi() == null){
            giftListBean.getData().setBaoshi(new ArrayList<>());
        }
        mStoneGiftAdapter = new StoneGiftAdapter(context, giftListBean.getData().getBaoshi(),giftListBean.getData().getBaoshi().size()<=4?false:true);

        boolean needLoop = true;

        if(giftListBean.getData().getMy_wares() == null){
            giftListBean.getData().setMy_wares(new ArrayList<>());
        }

        if(giftListBean.getData().getMy_wares().size()<=4){
            needLoop = false;
        }

        mPackageGiftAdapter = new PackageGiftAdapter(context, giftListBean.getData().getMy_wares(),needLoop);

    }

    //设置礼物adapter的item点击事件
    private void setGiftAdapterItemClick(){

        mGiftAdapter.setOnItemClickListener(position -> {
            mPosition=position;
            mId = String.valueOf(giftListBean.getData().getGifts().get(mPosition).getId());
        });
    }
    //设置宝石adapter的item点击事件
    private void setStoneAdapterItemClick(){

        mStoneGiftAdapter.setOnItemClickListener(position -> {
            mPosition=position;
            mId = String.valueOf(giftListBean.getData().getBaoshi().get(mPosition).getId());
        });
    }

    //设置背包adapter的item点击事件
    private void setPackageAdapterItemClick(){

        mPackageGiftAdapter.setOnItemClickListener(position -> {
            mPosition=position;
            mId = String.valueOf(giftListBean.getData().getMy_wares().get(mPosition).getId());
        });
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

    //获取礼物列表
    private void getGiftList(){
        RxUtils.loading(commonModel.gift_list(String.valueOf(UserManager.getUser().getUserId())))
                .subscribe(new ErrorHandleSubscriber<GiftListBean>(context.mErrorHandler) {
                    @Override
                    public void onNext(GiftListBean giftListBean) {

                        if(giftListBean != null){
                            textPrice.setText(giftListBean.getData().getMizuan());
                        }
                    }
                });
    }

    /**
     * 发送礼物
     * @param substring
     * @param listNew
     */
    private void sendGift(String substring,List<Microphone.DataBean.MicrophoneBean> listNew,boolean isMyPackage){

        String numbers = textNum.getText().toString().replace("x","").trim();

        RxUtils.loading(commonModel.gift_queue(
                mId, microphoneBean.getUser_id(),
                String.valueOf(UserManager.getUser().getUserId()),
                substring,numbers
        )).subscribe(new ErrorHandleSubscriber<SendGemResult>(context.mErrorHandler) {
            @Override
            public void onNext(SendGemResult baseBean) {
//                dismiss();
                if(baseBean == null || baseBean.getData() == null || baseBean.getData().size() ==0){
                    return;
                }
                ToastUtil.showToast(context,"发送成功");

                LoginData loginData = UserManager.getUser();

                MessageBean messageBean = new MessageBean();
                imgQuan.getLocationOnScreen(messageBean.location);
                messageBean.setUser_id(String.valueOf(loginData.getUserId()));
                messageBean.setNickName(loginData.getNickname());
                if(!isMyPackage){
                    GiftListBean.DataBean.GiftsBean giftsBean = giftListBean.getData().getGifts().get(mPosition);
                    messageBean.show_img = giftsBean.getShow_img();
                    messageBean.show_gif_img = giftsBean.getShow_img2();
                    messageBean.type = giftsBean.getType();
                    messageBean.giftNum = textNum.getText().toString().replace("x","").trim();
                    messageBean.e_name = giftsBean.e_name;
                    if(!TextUtils.equals("2",giftsBean.getType())){//没有全屏特效情况下，更新米钻,有特效已经关闭弹框了，不用更新

                        getGiftList();

                    }
                } else {

                    if(giftListBean.getData().getMy_wares().size() == 0){
                        dismiss();
                        return;
                    }
                    GiftListBean.DataBean.MyWaresBean myWaresBean = giftListBean.getData().getMy_wares().get(mPosition);
                    messageBean.show_img = myWaresBean.getShow_img();
                    messageBean.show_gif_img = myWaresBean.getShow_img2();
                    messageBean.type = myWaresBean.getType()+"";
                    messageBean.giftNum = textNum.getText().toString().replace("x","").trim();
                    if(myWaresBean.getType() != 2){//我的背包里面的礼物数量要减少，没有全屏特效情况下
                        int number = myWaresBean.getNum();

                        String[] sendUserIds = substring.split(",");//送给了几个人，人数*数量

                        if(sendUserIds.length>0){

                            int total = sendUserIds.length * Arith.strToInt(numbers);

                            number-=total;

                            if(number<=0){
                                giftListBean.getData().getMy_wares().remove(mPosition);
                                if(giftListBean.getData().getMy_wares().size() == 0){
                                    dismiss();
                                } else {
                                    mPackageGiftAdapter.notifyDataSetChanged();
                                }
//                                dismiss();
                            }
                            myWaresBean.setNum(number);
                            myWaresBean.setPrice("x" + number);
                            mPackageGiftAdapter.notifyDataSetChanged();

                        }

                    }
//                    messageBean.e_name = giftListBean.getData().getMy_wares().get(mPosition).gete;
                }
                messageBean.setMessageType("4");

                List<SendGemResult.DataBean> sendDataList = baseBean.getData();
                if(sendDataList.size() != listNew.size()){
                    return;
                }

                List<MessageBean.Data> dataList = new ArrayList<>();

                for (int i = 0;i<listNew.size();i++){
                    Microphone.DataBean.MicrophoneBean items = listNew.get(i);
                    MessageBean.Data data = new MessageBean.Data();
                    data.nickname = items.getNickname();
                    data.userId = items.getUser_id();
                    data.toNick_color = sendDataList.get(i).getNick_color();
                    dataList.add(data);
                }
                messageBean.userInfo = dataList;
                EventBus.getDefault().post(new FirstEvent(messageBean, Constant.FASONGMAIXULIWU));
                LogUtils.debugInfo("发送广播了============-=-=-=-=-=-=-=-=-=-=-=-=");
            }

            @Override
            public void onError(Throwable t) {
                super.onError(t);
//                            ApiIOException apiIOException = (ApiIOException) t;
//                            String code = apiIOException.code;
//                dismiss();
            }
        });
    }
    /**
     * 发送宝石,我的背包
     * @param substring
     * @param listNew
     */
    private void sendGemStonePackage(String substring,List<Microphone.DataBean.MicrophoneBean> listNew){

        RxUtils.loading(commonModel.send_baoshi(
                mId, microphoneBean.getUser_id(),
                String.valueOf(UserManager.getUser().getToken()),
                substring,textNum.getText().toString().replace("x","").trim()
        )).subscribe(new ErrorHandleSubscriber<SendGemResult>(context.mErrorHandler) {
            @Override
            public void onNext(SendGemResult baseBean) {
//                dismiss();
                ToastUtil.showToast(context,"发送成功");
                if(baseBean == null || baseBean.getData() == null || baseBean.getData().size() ==0){
                    return;
                }

                LoginData loginData = UserManager.getUser();

                MessageBean messageBean = new MessageBean();
                imgQuan.getLocationOnScreen(messageBean.location);
                messageBean.setUser_id(String.valueOf(loginData.getUserId()));
                messageBean.setNickName(loginData.getNickname());
                messageBean.show_img = giftListBean.getData().getMy_wares().get(mPosition).getShow_img();
//                messageBean.show_gif_img = giftListBean.getData().getBaoshi().get(mPosition).getShow_img2();
                messageBean.type = giftListBean.getData().getMy_wares().get(mPosition).getType()+"";
                messageBean.giftNum = textNum.getText().toString().replace("x","").trim();
//                messageBean.e_name = giftListBean.getData().getBaoshi().get(mPosition).e_name;
                messageBean.setMessageType("4");
                List<MessageBean.Data> dataList = new ArrayList<>();

                List<SendGemResult.DataBean> sendDataList = baseBean.getData();
                if(sendDataList.size() != listNew.size()){
                    return;
                }
                for (int i = 0;i<listNew.size();i++){
                    Microphone.DataBean.MicrophoneBean items = listNew.get(i);
                    MessageBean.Data data = new MessageBean.Data();
                    data.nickname = items.getNickname();
                    data.userId = items.getUser_id();
                    data.toNick_color = sendDataList.get(i).getNick_color();
                    dataList.add(data);
                }
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
     * 发送宝石
     * @param substring
     * @param listNew
     */
    private void sendGemStone(String substring,List<Microphone.DataBean.MicrophoneBean> listNew){

        RxUtils.loading(commonModel.send_baoshi(
                mId, microphoneBean.getUser_id(),
                String.valueOf(UserManager.getUser().getToken()),
                substring,textNum.getText().toString().replace("x","").trim()
        )).subscribe(new ErrorHandleSubscriber<SendGemResult>(context.mErrorHandler) {
            @Override
            public void onNext(SendGemResult baseBean) {
//                dismiss();
                ToastUtil.showToast(context,"发送成功");
                if(baseBean == null || baseBean.getData() == null || baseBean.getData().size() ==0){
                    return;
                }

                LoginData loginData = UserManager.getUser();

                MessageBean messageBean = new MessageBean();
                imgQuan.getLocationOnScreen(messageBean.location);
                messageBean.setUser_id(String.valueOf(loginData.getUserId()));
                messageBean.setNickName(loginData.getNickname());
                messageBean.show_img = giftListBean.getData().getBaoshi().get(mPosition).getShow_img();
//                messageBean.show_gif_img = giftListBean.getData().getBaoshi().get(mPosition).getShow_img2();
                messageBean.type = giftListBean.getData().getBaoshi().get(mPosition).getType()+"";
                messageBean.giftNum = textNum.getText().toString().replace("x","").trim();
//                messageBean.e_name = giftListBean.getData().getBaoshi().get(mPosition).e_name;
                messageBean.setMessageType("4");

                List<MessageBean.Data> dataList = new ArrayList<>();

                List<SendGemResult.DataBean> sendDataList = baseBean.getData();
                if(sendDataList.size() != listNew.size()){
                    return;
                }
                for (int i = 0;i<listNew.size();i++){
                    Microphone.DataBean.MicrophoneBean items = listNew.get(i);
                    MessageBean.Data data = new MessageBean.Data();
                    data.nickname = items.getNickname();
                    data.userId = items.getUser_id();
                    data.toNick_color = sendDataList.get(i).getNick_color();
                    dataList.add(data);
                }
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
     * @param substring
     * @param listNew
     */
    private void sendByk(String substring,List<Microphone.DataBean.MicrophoneBean> listNew){

        RxUtils.loading(commonModel.send_byk(
                microphoneBean.getUser_id(),
                String.valueOf(UserManager.getUser().getToken()),
                substring,textNum.getText().toString().replace("x","").trim()
        )).subscribe(new ErrorHandleSubscriber<SendGemResult>(context.mErrorHandler) {
            @Override
            public void onNext(SendGemResult baseBean) {
//                dismiss();
                ToastUtil.showToast(context,"发送成功");
                if(baseBean == null || baseBean.getData() == null || baseBean.getData().size() ==0){
                    return;
                }

                LoginData loginData = UserManager.getUser();

                MessageBean messageBean = new MessageBean();
                messageBean.setUser_id(String.valueOf(loginData.getUserId()));
                messageBean.setNickName(loginData.getNickname());
                messageBean.show_img = giftListBean.getData().getMy_wares().get(mPosition).getShow_img();
//                messageBean.show_gif_img = giftListBean.getData().getBaoshi().get(mPosition).getShow_img2();
                messageBean.type = giftListBean.getData().getMy_wares().get(mPosition).getType()+"";
                messageBean.giftNum = textNum.getText().toString().replace("x","").trim();
//                messageBean.e_name = giftListBean.getData().getBaoshi().get(mPosition).e_name;
                messageBean.setMessageType("4");

                List<MessageBean.Data> dataList = new ArrayList<>();

                List<SendGemResult.DataBean> sendDataList = baseBean.getData();
                if(sendDataList.size() != listNew.size()){
                    return;
                }
                for (int i = 0;i<listNew.size();i++){
                    Microphone.DataBean.MicrophoneBean items = listNew.get(i);
                    MessageBean.Data data = new MessageBean.Data();
                    data.nickname = items.getNickname();
                    data.userId = items.getUser_id();
                    data.toNick_color = sendDataList.get(i).getNick_color();
                    dataList.add(data);
                }
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

    // 隐藏软键盘
    private void hideSoftInput() {
        IBinder token = etNum.getWindowToken();
        if (token != null) {
            InputMethodManager manager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(
                    token,
                    InputMethodManager.HIDE_NOT_ALWAYS
            );
        }
    }

    @OnClick({R.id.btn_ok, R.id.imgType, R.id.textPrice, R.id.btn_chongzhi, R.id.textNum,R.id.textPriceName,R.id.btn_yes})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_yes:
                if(TextUtils.isEmpty(etNum.getText().toString().trim())){
                    return;
                }
                textNum.setText("x"+etNum.getText().toString().trim());
                etNum.setText("");
                llInput.setVisibility(View.GONE);
                hideSoftInput();
                break;
            case R.id.btn_ok:

                List<Microphone.DataBean.MicrophoneBean> data2 = giftUserAdapter.getData();
                List<Microphone.DataBean.MicrophoneBean> listNew = new ArrayList<>();
                StringBuffer sb = new StringBuffer();
                for (Microphone.DataBean.MicrophoneBean list : data2){
                    if(!TextUtils.isEmpty(list.getHeadimgurl()) && list.isSelect) {
                        sb.append(list.getUser_id() + ",");
                        listNew.add(list);
                    }
                }

                if(TextUtils.isEmpty(sb.toString())){
                    ToastUtil.showToast(context,"请选择要送的麦位或者用户！");
                    return;
                }

                String substring = sb.toString().substring(0, sb.toString().length() - 1);

                switch (mCurrentGiftType){
                    case 0://礼物
                        if(mPosition>=giftListBean.getData().getGifts().size()){
                            return;
                        }
                        sendGift(substring,listNew,false);
                        GiftListBean.DataBean.GiftsBean giftsBean = giftListBean.getData().getGifts().get(mPosition);
                        if(TextUtils.equals("2",giftsBean.getType())){//有全屏特效
                            dismiss();
                        }
                        break;
                    case 1://宝石
                        sendGemStone(substring,listNew);
                        dismiss();
                        break;
                    case 2://我的
                        if(mPosition>=giftListBean.getData().getMy_wares().size()){
                            return;
                        }
                        int type = giftListBean.getData().getMy_wares().get(mPosition).getWares_type();

                        if(type==1){//1宝石 2礼物 3 卡片
                            sendGemStonePackage(substring,listNew);
                            dismiss();
                        } else if(type == 2){
                            sendGift(substring,listNew,true);
                            GiftListBean.DataBean.MyWaresBean giftsBean1 = giftListBean.getData().getMy_wares().get(mPosition);
                            if(giftsBean1.getType()==2){//有全屏特效
                                dismiss();
                            }
                        } else if(type == 3){
                            sendByk(substring,listNew);
                            dismiss();
                        }
                        break;
                }
//                dismiss();

                break;
            case R.id.imgType:
                List<Microphone.DataBean.MicrophoneBean> data = giftUserAdapter.getData();
                if(imgType.isSelected()){
                    imgType.setSelected(false);
                    for (Microphone.DataBean.MicrophoneBean list : data){
                        list.isSelect = false;
                        giftUserAdapter.notifyDataSetChanged();
                    }
                }else {
                    imgType.setSelected(true);
                    for (Microphone.DataBean.MicrophoneBean list : data){
                        if(!TextUtils.isEmpty(list.getHeadimgurl())) {
                            if(list.getUser_id()
                                    .equals(UserManager.getUser().getUserId() + "")){
                                list.isSelect = false;
                            }else {
                                list.isSelect = true;
                            }
                        }
                        giftUserAdapter.notifyDataSetChanged();
                    }
                }
                break;
            case R.id.btn_chongzhi:
                Intent intent = new Intent(context, ChargeActivity.class);
                intent.putExtra("type",1);
                ArmsUtils.startActivity(intent);
                if(context instanceof AdminHomeActivity) {
                    context.overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
                }
                break;
            case R.id.textNum:
                GiftNumber2Window giftNumberWindow = new GiftNumber2Window(context);
                giftNumberWindow.getmMenuView().measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
                int popupWidth = giftNumberWindow.getmMenuView().getMeasuredWidth();
                int popupHeight = giftNumberWindow.getmMenuView().getMeasuredHeight();
                int[] location = new int[2];
                imgGift.getLocationOnScreen(location);
                giftNumberWindow.showAtLocation(imgGift, Gravity.NO_GRAVITY,
                        (location[0] + imgGift.getWidth() / 2) - popupWidth / 2 + 140, location[1] - popupHeight);
                giftNumberWindow.getMyGrid().setOnItemClickListener((parent, view1, position, id) -> {
                    giftNumberWindow.dismiss();
                    if(position==0){
                        llInput.setVisibility(View.VISIBLE);
                    }else{
                        textNum.setText(giftNumberWindow.getGiftDiaAdapter().getList_adapter().get(position));
                    }

                });
                break;
            case R.id.textPriceName://选择礼物，宝石或者我的
                SelectGiftTypeWindow selectGiftTypeWindow = new SelectGiftTypeWindow(context);
                selectGiftTypeWindow.getmMenuView().measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
                int popupWidths = selectGiftTypeWindow.getmMenuView().getMeasuredWidth();
//                int popupHeights = selectGiftTypeWindow.getmMenuView().getMeasuredHeight();
                int popupHeights = QMUIDisplayHelper.dpToPx(110);
                int[] locations = new int[2];
                imgGift.getLocationOnScreen(locations);
                selectGiftTypeWindow.showAtLocation(imgGift, Gravity.NO_GRAVITY,
                        (locations[0] + imgGift.getWidth() / 2) - popupWidths, locations[1] - popupHeights);
                selectGiftTypeWindow.getMyGrid().setOnItemClickListener((parent, view1, position, id) -> {
                    selectGiftTypeWindow.dismiss();

                    switch (position){
                        case 0://礼物
                            if(mCurrentGiftType == 0){
                                return;
                            }
                            mCurrentGiftType = 0;
                            textPriceName.setText(selectGiftTypeWindow.getGiftDiaAdapter().getList_adapter().get(position));
                            circleRecycler.setAdapter(mGiftAdapter);
                            setGiftAdapterItemClick();
                            break;
                        case 1://宝石

                            if(giftListBean == null || giftListBean.getData() == null || giftListBean.getData().getBaoshi() == null || giftListBean.getData().getBaoshi().size() == 0){
                                ToastUtil.showToast(context,"暂无宝石");
                                return;
                            }
                            if(mCurrentGiftType == 1){
                                return;
                            }
                            mCurrentGiftType = 1;
                            textPriceName.setText(selectGiftTypeWindow.getGiftDiaAdapter().getList_adapter().get(position));
                            giftListBean.getData().getBaoshi().size();

                            circleRecycler.setAdapter(mStoneGiftAdapter);
                            setStoneAdapterItemClick();

                            break;
                        case 2://我的
                            if(giftListBean == null || giftListBean.getData() == null || giftListBean.getData().getMy_wares() == null || giftListBean.getData().getMy_wares().size() == 0){
                                ToastUtil.showToast(context,"我的背包空空如也~");
                                return;
                            }
                            if(mCurrentGiftType == 2){
                                return;
                            }
                            mCurrentGiftType = 2;
                            textPriceName.setText(selectGiftTypeWindow.getGiftDiaAdapter().getList_adapter().get(position));

                            circleRecycler.setAdapter(mPackageGiftAdapter);
                            setPackageAdapterItemClick();
                            break;

                    }
                });
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveMsg(MessageEvent event) {

        StateMessage stateMessage = event.getStateMessage();

        if (stateMessage.getState() == StateMessage.CLOSE_GIFT_WINDOW.getState()) {//关闭
            dismiss();
        }

    }
}
