package com.miniapp.talks.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gyf.immersionbar.ImmersionBar;
import com.gyf.immersionbar.components.ImmersionOwner;
import com.gyf.immersionbar.components.ImmersionProxy;
import com.jaeger.library.StatusBarUtil;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.miniapp.talks.R;
import com.miniapp.talks.activity.HelpActivity;
import com.miniapp.talks.activity.MainActivity;
import com.miniapp.talks.activity.SetActivity;
import com.miniapp.talks.activity.login.LoginActivity;
import com.miniapp.talks.activity.mine.MoneyActivity;
import com.miniapp.talks.activity.mine.MyProfitActivity;
import com.miniapp.talks.activity.mine.RealNameActivity;
import com.miniapp.talks.activity.my.GradeCenterActivity;
import com.miniapp.talks.activity.my.MemberCoreActivity;
import com.miniapp.talks.activity.my.MyFollowActivity;
import com.miniapp.talks.activity.my.MyPackageActivity;
import com.miniapp.talks.activity.my.MyPersonalCenterActivity;
import com.miniapp.talks.app.utils.RxUtils;
import com.miniapp.talks.app.view.CircularImage;
import com.miniapp.talks.base.MyBaseArmFragment;
import com.miniapp.talks.base.UserManager;
import com.miniapp.talks.bean.EnterRoom;
import com.miniapp.talks.bean.FirstEvent;
import com.miniapp.talks.bean.UserBean;
import com.miniapp.talks.di.CommonModule;
import com.miniapp.talks.di.DaggerCommonComponent;
import com.miniapp.talks.service.CommonModel;
import com.miniapp.talks.utils.Constant;
import com.miniapp.talks.utils.StatusBarUtils;
import com.miniapp.talks.view.CustomDialog;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

import static com.miniapp.talks.utils.Constant.CHONGZHISHUAXIN;
import static com.miniapp.talks.utils.Constant.FANHUIZHUYE;
import static com.miniapp.talks.utils.Constant.LOGIN;
import static com.miniapp.talks.utils.Constant.PACKFANHUI;
import static com.miniapp.talks.utils.Constant.XIUGAIGERENZILIAOCHENGGONG;
import static com.miniapp.talks.utils.Constant.XUANFUYINCANG;

/**
 * 作者:sgm
 * 描述: 我的
 */
public class MainCenterFragment extends MyBaseArmFragment implements ImmersionOwner {
    @BindView(R.id.iv_head)
    CircularImage ivHead;
    @BindView(R.id.iv_username)
    TextView ivUsername;
    @BindView(R.id.ll1)
    LinearLayout ll1;
    @BindView(R.id.im_myhome)
    ImageView imMyhome;
    @BindView(R.id.myhome)
    TextView myhome;
    @BindView(R.id.im_mywallet)
    ImageView imMywallet;
    @BindView(R.id.mywallet)
    TextView mywallet;
    @BindView(R.id.im_myshouyi)
    ImageView imMyshouyi;
    @BindView(R.id.myshouyi)
    TextView myshouyi;
    @BindView(R.id.im_mydengji)
    ImageView imMydengji;
    @BindView(R.id.mydengji)
    TextView mydengji;
    @BindView(R.id.im_myhelp)
    ImageView imMyhelp;
    @BindView(R.id.myhelp)
    TextView myhelp;
    @BindView(R.id.im_myset)
    ImageView imMyset;
    @BindView(R.id.myset)
    TextView myset;
    Unbinder unbinder;
    @BindView(R.id.text_vip)
    TextView textVip;
    @BindView(R.id.rlSet)
    RelativeLayout rlSet;
    CustomDialog mDialog;
    //    @BindView(R.id.iv_modifyusermsg)
//    ImageView ivModifyusermsg;
    @BindView(R.id.rl_myhome)
    RelativeLayout rlMyhome;
    @BindView(R.id.rl_help)
    RelativeLayout rlHelp;

    @BindView(R.id.textCollection)
    TextView textCollection;
    @BindView(R.id.textFans)
    TextView textFans;
    @BindView(R.id.rlMoney)
    RelativeLayout rlMoney;
    @BindView(R.id.rlShouyi)
    RelativeLayout rlShouyi;
    @BindView(R.id.dengji)
    RelativeLayout dengji;
    @BindView(R.id.huiyuan)
    RelativeLayout huiyuan;
    @BindView(R.id.my_lv)
    TextView myLv;
    @BindView(R.id.mizuan)
    TextView mizuanNum;
    @BindView(R.id.layout_head_title)
    RelativeLayout layoutHeadTitle;

    Unbinder unbinder1;
    private String old_id = "";

    @Inject
    CommonModel commonModel;

    private UserBean mUserBean;

    private MainActivity mActivity;

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerCommonComponent.builder()
                .appComponent(appComponent)
                .commonModule(new CommonModule())
                .build()
                .inject(this);
    }

    @Override
    public View getLayoutView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return ArmsUtils.inflate(getActivity(), R.layout.fragment_center);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (MainActivity) context;
    }


    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

//        StatusBarUtil.setTranslucentForImageViewInFragment(getActivity(), 0, null);

        ll1.setVisibility(View.VISIBLE);
        textVip.setVisibility(View.VISIBLE);
        ivUsername.setText(UserManager.getUser().getNickname());

        loadUserData();
    }


    /**
     * 用户信息
     */
    private void loadUserData() {
        RxUtils.loading(commonModel.get_user_info(String.valueOf(UserManager.getUser().getUserId())), this)
                .subscribe(new ErrorHandleSubscriber<UserBean>(mErrorHandler) {
                    @Override
                    public void onNext(UserBean userBean) {
                        mUserBean = userBean;
                        loadImage(ivHead, userBean.getData().getHeadimgurl(),
                                R.mipmap.no_tou);
                        ivUsername.setText(userBean.getData().getNickname());
                        textVip.setText("ID:" + userBean.getData().getId());
                        textCollection.setText(userBean.getData().getFollows_num() + "");
                        textFans.setText(userBean.getData().getFans_num() + "");
                        myLv.setText("Lv. " + userBean.getData().getVip_level());
                        String mizuan = userBean.getData().getMizuan();
                        if (!TextUtils.isEmpty(mizuan)) {
                            if (mizuan.contains(".")) {
                                int index = mizuan.indexOf(".");
                                mizuan = mizuan.substring(0, index);
                            }
                        }
                        mizuanNum.setText(mizuan);
                    }
                });
    }

    @Override
    public void setData(@Nullable Object data) {

    }


    @OnClick({R.id.iv_head, R.id.rlShouyi, R.id.rlMoney,
            R.id.rl_help, R.id.iv_username, R.id.rlSet, R.id.rl_myhome, R.id.ll1, R.id.dengji, R.id.huiyuan, R.id.rl_my_package})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_myhome:
                if (mUserBean == null) {
                    return;
                }
                if (mUserBean.getData().getIs_idcard() == 0) {
                    ArmsUtils.startActivity(RealNameActivity.class);
                } else {
                    enterData(String.valueOf(UserManager.getUser().getUserId()), "", commonModel, 1, mUserBean.getData().getHeadimgurl());
                }
                break;
//            case R.id.iv_modifyusermsg:
////                    ArmsUtils.startActivity(ChangeUserActivity.class);
////                    ArmsUtils.startActivity(UserMineStateActivity.class);
////                    ArmsUtils.startActivity(AdminHomeActivity.class);
//                mDialog = new CustomDialog(getActivity(), "您已被踢出房间", "60分钟后可重新进入该房间", "确定", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        mDialog.dismiss();
//                        Toast.makeText(getActivity(), "退出了--伤心", Toast.LENGTH_LONG).show();
//                    }
//                });
//                mDialog.setCanotBackPress();
//                mDialog.setCanceledOnTouchOutside(true);
//                mDialog.show();
//                break;
            case R.id.rl_help:
                ArmsUtils.startActivity(HelpActivity.class); //帮助和反馈
                break;
            case R.id.iv_head:
                Intent intent = new Intent(getActivity(), MyPersonalCenterActivity.class);
                intent.putExtra("sign", 0);
                intent.putExtra("id", "");
                ArmsUtils.startActivity(intent);
                break;
            case R.id.iv_username:
                ArmsUtils.startActivity(LoginActivity.class);
                break;
            case R.id.rlSet: //设置
                ArmsUtils.startActivity(SetActivity.class);
                break;

            case R.id.rlShouyi:
                Intent intent2 = new Intent(getActivity(), MyProfitActivity.class);
                ArmsUtils.startActivity(intent2);
                break;
            case R.id.rlMoney:  //我的钱包
                ArmsUtils.startActivity(MoneyActivity.class);
                break;
            case R.id.ll1:  //我的关注
                ArmsUtils.startActivity(MyFollowActivity.class);
                break;
            case R.id.dengji: //我的等级
                ArmsUtils.startActivity(GradeCenterActivity.class);
                break;
            case R.id.huiyuan:  //会员等级
                ArmsUtils.startActivity(MemberCoreActivity.class);
                break;
            case R.id.rl_my_package:  //我的背包
                if (mUserBean == null) {
                    return;
                }
                Intent intent1 = new Intent(getActivity(), MyPackageActivity.class);
                intent1.putExtra("url", mUserBean.getData().getHeadimgurl());
                ArmsUtils.startActivity(intent1);
                break;
        }
    }

    /**
     * ImmersionBar代理类
     */
    private ImmersionProxy mImmersionProxy = new ImmersionProxy(this);

    @Override
    public void onLazyBeforeView() {

    }

    @Override
    public void onLazyAfterView() {

    }

    @Override
    public void onVisible() {

    }

    @Override
    public void onInvisible() {

    }

    @Override
    public void initImmersionBar() {

        ImmersionBar.with(this).getTag("MainActivity").reset();

        ImmersionBar.with(this)
//                .transparentStatusBar()//透明状态栏，不写默认透明色
                .init();

//        ImmersionBar.with(this).statusBarColor(R.color.white)
//                .autoStatusBarDarkModeEnable(true, 0.2f) //自动状态栏字体变色，必须指定状态栏颜色才可以自动变色哦
//                .init();//设置状态栏白色

    }

    @Override
    public void onResume() {
        super.onResume();
        mImmersionProxy.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mImmersionProxy.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mImmersionProxy.onDestroy();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        mImmersionProxy.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImmersionProxy.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mImmersionProxy.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        mImmersionProxy.onHiddenChanged(hidden);
        if (!hidden && getActivity() != null) {
            loadUserData();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mImmersionProxy.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean immersionBarEnabled() {//是否用沉浸式
        return true;
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveMsg(FirstEvent event) {
        String tag = event.getTag();
        if (LOGIN.equals(tag)) {
            ll1.setVisibility(View.VISIBLE);
            textVip.setVisibility(View.VISIBLE);
            //做请求个人信息的操作
        } else if (FANHUIZHUYE.equals(tag)) {//显示悬浮窗
            EnterRoom enterRoom = event.getEnterRoom();
            old_id = String.valueOf(enterRoom.getRoom_info().get(0).getUid());
        } else if (XUANFUYINCANG.equals(tag)) {//显示悬浮窗
            old_id = "";
        } else if (Constant.RENZHENGCHENGGONG.equals(tag)) {
            showToast("认证成功！");
            mUserBean.getData().setIs_idcard(1);
        } else if ("send_gift".equals(tag)) {
            loadUserData();
        } else if (XIUGAIGERENZILIAOCHENGGONG.equals(tag)) {
            loadUserData();
        } else if (CHONGZHISHUAXIN.equals(tag)) {
            loadUserData();
        } else if (PACKFANHUI.equals(tag)) {
            loadUserData();
        }
    }
}
