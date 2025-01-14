package com.miniapp.talks.activity.mine;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jess.arms.di.component.AppComponent;
import com.miniapp.talks.R;
import com.miniapp.talks.app.utils.RxUtils;
import com.miniapp.talks.base.MyBaseArmActivity;
import com.miniapp.talks.base.UserManager;
import com.miniapp.talks.bean.BaseBean;
import com.miniapp.talks.bean.FirstEvent;
import com.miniapp.talks.bean.MoneyBean;
import com.miniapp.talks.di.CommonModule;
import com.miniapp.talks.di.DaggerCommonComponent;
import com.miniapp.talks.service.CommonModel;
import com.miniapp.talks.utils.Constant;
import com.miniapp.talks.view.ShapeTextView;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

/**
 * 提现
 */
public class CashMoneyActivity extends MyBaseArmActivity {

    @BindView(R.id.textName)
    TextView textName;
    @BindView(R.id.imgUser)
    ImageView imgUser;
    @BindView(R.id.textAll)
    TextView textAll;
    @BindView(R.id.editMoney)
    EditText editMoney;
    @BindView(R.id.btn_ok)
    ShapeTextView btnOk;

    @Inject
    CommonModel commonModel;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerCommonComponent.builder()
                .appComponent(appComponent)
                .commonModule(new CommonModule())
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_cash_money;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        loadData();
    }

    private void loadData() {
        RxUtils.loading(commonModel.my_store(String.valueOf(UserManager.getUser().getUserId())), this)
                .subscribe(new ErrorHandleSubscriber<MoneyBean>(mErrorHandler) {
                    @Override
                    public void onNext(MoneyBean moneyBean) {
                        if(!TextUtils.isEmpty(moneyBean.getData().get(0).getAli_nick_name())) {
                            textName.setText(moneyBean.getData().get(0).getAli_nick_name() + "");
                        }else {
                            textName.setText("");
                        }
                        loadImage(imgUser,moneyBean.getData().get(0).getAli_avatar(),R.mipmap.gender_zhuce_boy);
                        textAll.setText(moneyBean.getData().get(0).getMibi() + "元");
                    }
                });
    }

    @OnClick({R.id.btn_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_ok:
                String str = editMoney.getText().toString();
                if(TextUtils.isEmpty(str)){
                    toast("请输入提现金额");
                }else {
                    RxUtils.loading(commonModel.tixian(String.valueOf(UserManager.getUser().getUserId()), str), this)
                            .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                                @Override
                                public void onNext(BaseBean moneyBean) {
                                    toast("提现成功");
                                    EventBus.getDefault().post(new FirstEvent("指定发送", Constant.CHONGZHISHUAXIN));
                                    loadData();
                                }
                            });
                }
                break;
        }
    }

}
