package com.miniapp.talks.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.miniapp.talks.R;
import com.miniapp.talks.app.utils.RxUtils;
import com.miniapp.talks.base.MyBaseArmActivity;
import com.miniapp.talks.base.UserManager;
import com.miniapp.talks.bean.BaseBean;
import com.miniapp.talks.bean.RecommenRoomBean;
import com.miniapp.talks.bean.SearchHis;
import com.miniapp.talks.di.CommonModule;
import com.miniapp.talks.di.DaggerCommonComponent;
import com.miniapp.talks.fragment.RecomFragment;
import com.miniapp.talks.service.CommonModel;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

/**
 * 搜索历史
 */
public class SearchHisActivity extends MyBaseArmActivity {

    @BindView(R.id.editName)
    EditText editName;
    @BindView(R.id.textCancel)
    TextView textCancel;
    @BindView(R.id.imgDelete)
    ImageView imgDelete;
    @BindView(R.id.flowlayoutTop)
    TagFlowLayout flowlayoutTop;
    @BindView(R.id.flowlayoutBottom)
    TagFlowLayout flowlayoutBottom;

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
        return R.layout.activity_search_his;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        loadHisData();
        editName.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                String trim = editName.getText().toString().trim();
                if(TextUtils.isEmpty(trim)){
                    toast("请填写搜索内容！");
                }else {
                    Intent intent = new Intent(this, SearchResultActivity.class);
                    intent.putExtra("name",trim);
                    ArmsUtils.startActivity(intent);
                }
            }
            //记得返回false
            return false;
        });

    }

    private void loadHisData() {
        RxUtils.loading(commonModel.searhList(UserManager.getUser().getUserId() + ""), this)
                .subscribe(new ErrorHandleSubscriber<SearchHis>(mErrorHandler) {
                    @Override
                    public void onNext(SearchHis searchHis) {
                        List<SearchHis.DataBean.HistorBean> histor = searchHis.getData().getHistor();
                        flowlayoutTop.setAdapter(new TagAdapter<SearchHis.DataBean.HistorBean>(histor) {
                            @Override
                            public View getView(FlowLayout parent, int position, SearchHis.DataBean.HistorBean historBean) {
                                TextView tv = (TextView) LayoutInflater.from(SearchHisActivity.this)
                                        .inflate(R.layout.text_search, flowlayoutTop, false);
                                tv.setText(historBean.getSearch());
                                return tv;
                            }
                        });

                        List<SearchHis.DataBean.HotBean> hot = searchHis.getData().getHot();
                        flowlayoutBottom.setAdapter(new TagAdapter<SearchHis.DataBean.HotBean>(hot) {
                            @Override
                            public View getView(FlowLayout parent, int position, SearchHis.DataBean.HotBean hotBean) {
                                TextView tv = (TextView) LayoutInflater.from(SearchHisActivity.this)
                                        .inflate(R.layout.text_search, flowlayoutBottom, false);
                                tv.setText(hotBean.getSearch());
                                return tv;
                            }
                        });

                        flowlayoutTop.setOnTagClickListener((view, position, parent) -> {
                            Intent intent = new Intent(SearchHisActivity.this, SearchResultActivity.class);
                            intent.putExtra("name",histor.get(position).getSearch());
                            ArmsUtils.startActivity(intent);
                            return true;
                        });

                        flowlayoutBottom.setOnTagClickListener((view, position, parent) -> {
                            Intent intent = new Intent(SearchHisActivity.this, SearchResultActivity.class);
                            intent.putExtra("name",hot.get(position).getSearch());
                            ArmsUtils.startActivity(intent);
                            return true;
                        });
                    }
                });
    }


    @OnClick({R.id.textCancel, R.id.imgDelete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.textCancel:
                finish();
                break;
            case R.id.imgDelete:
                RxUtils.loading(commonModel.cleanSarhList(UserManager.getUser().getUserId() + ""), this)
                        .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                            @Override
                            public void onNext(BaseBean baseBean) {
                                loadHisData();
                            }
                        });
                break;
        }
    }
}
