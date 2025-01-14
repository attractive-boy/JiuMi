package com.miniapp.talks.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.miniapp.talks.R;
import com.miniapp.talks.adapter.MyMusciAdapter;
import com.miniapp.talks.base.MyBaseArmFragment;
import com.miniapp.talks.bean.FirstEvent;
import com.miniapp.talks.bean.LocalMusicInfo;
import com.miniapp.talks.di.CommonModule;
import com.miniapp.talks.di.DaggerCommonComponent;
import com.miniapp.talks.utils.Constant;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.litepal.LitePal;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.miniapp.talks.utils.Constant.YINYUESHUAXIN;

/**
 * 作者:sgm
 * 描述:我的音乐
 */
public class MyMusicFragment extends MyBaseArmFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.no_data_image)
    ImageView noDataImage;
    @BindView(R.id.no_data_text)
    TextView noDataText;
    @BindView(R.id.no_data)
    LinearLayout noData;

    private MyMusciAdapter localMusciAdapter;

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
        return ArmsUtils.inflate(getActivity(), R.layout.fragment_my_music);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        noDataImage.setImageResource(R.mipmap.no_music);
        noDataText.setText("还没有音乐哦，快去添加吧~");
        localMusciAdapter = new MyMusciAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(localMusciAdapter);
        try {
            List<LocalMusicInfo> listLocal = LitePal.findAll(LocalMusicInfo.class);
            if (listLocal.size() == 0 || listLocal == null) {
                noData.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            } else {
                noData.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                localMusciAdapter.setNewData(listLocal);
            }
        } catch (Exception e) {

        }
        localMusciAdapter.setOnItemClickListener((adapter, view, position) -> {
            boolean isStart = localMusciAdapter.getData().get(position).isStart;
            List<LocalMusicInfo> data = localMusciAdapter.getData();
            if (isStart) {
                localMusciAdapter.getData().get(position).isStart = false;
                localMusciAdapter.notifyItemChanged(position);
                EventBus.getDefault().post(new FirstEvent(position + "", Constant.YINYUEZANTING));
            } else {
                for (LocalMusicInfo list : data) {
                    list.isStart = false;
                }
                localMusciAdapter.getData().get(position).isStart = true;
                localMusciAdapter.notifyDataSetChanged();
                EventBus.getDefault().post(new FirstEvent(position + "", Constant.YINYUEBOFANG));
            }
        });
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveMsg(FirstEvent event) {
        String tag = event.getTag();
        if (YINYUESHUAXIN.equals(tag)) {
            recyclerView.setVisibility(View.VISIBLE);
            noData.setVisibility(View.GONE);
            List<LocalMusicInfo> listLocal = LitePal.findAll(LocalMusicInfo.class);
            localMusciAdapter.setNewData(listLocal);
        }
    }
}
