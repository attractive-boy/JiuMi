package com.miniapp.talks.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.LogUtils;
import com.miniapp.talks.R;
import com.miniapp.talks.activity.room.AdminHomeActivity;
import com.miniapp.talks.adapter.YinxiaoAdapter;
import com.miniapp.talks.base.MyBaseArmFragment;
import com.miniapp.talks.bean.CategorRoomBean;
import com.miniapp.talks.bean.MusicYinxiao;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.agora.rtc.RtcEngine;

/**
 * 作者:sgm
 * 描述:
 */
public class YinxiaoFragment extends MyBaseArmFragment {
    @BindView(R.id.myGrid)
    GridView myGrid;

    private MusicYinxiao musicYinxiao;
    private int id = 0;
    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public View getLayoutView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View scrollView = ArmsUtils.inflate(getActivity(), R.layout.fragment_yinxiao);
        return scrollView;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        id = getArguments().getInt("id");

        musicYinxiao = (MusicYinxiao) getArguments().getSerializable("musicYinxiao");
        LogUtils.debugInfo("sgm","====集合长度:" + musicYinxiao.getData().getYinxiao().size());
        if(musicYinxiao !=null){
            if(id == 0){
                LogUtils.debugInfo("sgm","====00000000");
                YinxiaoAdapter yinxiaoAdapter = new YinxiaoAdapter(getActivity(), mRtcEngine);
                myGrid.setAdapter(yinxiaoAdapter);
                yinxiaoAdapter.getList_adapter().clear();
                if(musicYinxiao.getData() != null && musicYinxiao.getData().getYinxiao() != null){

                    List<MusicYinxiao.DataBean.YinxiaoBean> list = musicYinxiao.getData().getYinxiao();

                    int size = list.size();

                    if(size>0){

                        List<MusicYinxiao.DataBean.YinxiaoBean> listYinxiao = yinxiaoAdapter.getList_adapter();

                        for(int i = 0;i<size;i++){

                            listYinxiao.add(list.get(i));


                        }


                    }

                }
                yinxiaoAdapter.notifyDataSetChanged();
            }else {
                LogUtils.debugInfo("sgm","====11111111111");
                YinxiaoAdapter yinxiaoAdapter = new YinxiaoAdapter(getActivity(), mRtcEngine);
                myGrid.setAdapter(yinxiaoAdapter);
                yinxiaoAdapter.getList_adapter().clear();
                List<MusicYinxiao.DataBean.YinxiaoBean> yinxiao = musicYinxiao.getData().getYinxiao();
                for (int i = 6;i<yinxiao.size();i++){
                    yinxiaoAdapter.getList_adapter().add(musicYinxiao.getData().getYinxiao().get(i));
                }
                yinxiaoAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void setData(@Nullable Object data) {

    }


    public static YinxiaoFragment getInstance(int tag, MusicYinxiao musicYinxiao) {
        YinxiaoFragment fragment = new YinxiaoFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", tag);
        bundle.putSerializable("musicYinxiao", musicYinxiao);
        fragment.setArguments(bundle);
        return fragment;
    }

    private RtcEngine mRtcEngine;
    public void setRt(RtcEngine mRtcEngine) {
        this.mRtcEngine = mRtcEngine;
    }
}
