package com.miniapp.talks.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.miniapp.talks.R;
import com.miniapp.talks.adapter.MyGiftFragmentAdapter;
import com.miniapp.talks.base.HeaderViewPagerFragment;
import com.miniapp.talks.bean.MyPersonalCebterBean;
import com.miniapp.talks.di.CommonModule;
import com.miniapp.talks.di.DaggerCommonComponent;
import com.miniapp.talks.service.CommonModel;
import com.miniapp.talks.view.MyGridView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 个人主页我的礼物页面
 */
public class MyGiftFragment extends HeaderViewPagerFragment {
    @Inject
    CommonModel commonModel;
    @BindView(R.id.gift_recyc)
    MyGridView giftRecyc;
    @BindView(R.id.no_data)
    TextView noData;

    private MyGiftFragmentAdapter mAdapter;

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
        View scrollView = ArmsUtils.inflate(getActivity(), R.layout.fragment_my_gift);
        return scrollView;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        mAdapter = new MyGiftFragmentAdapter(getActivity());
        giftRecyc.setAdapter(mAdapter);

        Bundle arguments = getArguments();
        if (arguments != null) {
            if (arguments.getParcelableArrayList("gifts") != null && arguments.getParcelableArrayList("gifts").size() != 0) {
                ArrayList<MyPersonalCebterBean.DataBean.GiftsBean> gifts = arguments.getParcelableArrayList("gifts");
                mAdapter.getList_adapter().addAll(gifts);
                mAdapter.notifyDataSetChanged();
                noData.setVisibility(View.GONE);
                giftRecyc.setVisibility(View.VISIBLE);
            } else {
                noData.setVisibility(View.VISIBLE);
                giftRecyc.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public View getScrollableView() {
        return giftRecyc;
    }
}
