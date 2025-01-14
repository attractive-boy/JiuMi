package com.miniapp.talks.activity.room;

import android.Manifest;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.LogUtils;
import com.lzx.starrysky.manager.MediaSessionConnection;
import com.lzx.starrysky.manager.MusicManager;
import com.lzx.starrysky.model.SongInfo;
import com.miniapp.talks.R;
import com.miniapp.talks.adapter.LocalMusciAdapter;
import com.miniapp.talks.base.MyBaseArmActivity;
import com.miniapp.talks.bean.FirstEvent;
import com.miniapp.talks.bean.LocalMusicInfo;
import com.miniapp.talks.di.CommonModule;
import com.miniapp.talks.di.DaggerCommonComponent;
import com.miniapp.talks.utils.Constant;
import com.miniapp.talks.view.ShapeTextView;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.greenrobot.eventbus.EventBus;
import org.litepal.LitePal;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 我的音乐，选择
 */
public class MyMusciActivity extends MyBaseArmActivity {

    @BindView(R.id.toolbar_back)
    RelativeLayout toolbarBack;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.rightTitle)
    TextView rightTitle;
    @BindView(R.id.rightConfirm)
    ShapeTextView rightConfirm;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

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
        return R.layout.activity_my_musci;
    }

    @SuppressLint("CheckResult")
    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        MediaSessionConnection.getInstance().connect();

        LocalMusciAdapter localMusciAdapter = new LocalMusciAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(localMusciAdapter);

        new RxPermissions(this)
                .request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(granted -> {
                    if (granted) { // Always true pre-M
                        List<SongInfo> list = MusicManager.getInstance().querySongInfoInLocal();
                        List<LocalMusicInfo> listMusic = new ArrayList<>();
                        LogUtils.debugInfo("===音乐列表" + JSON.toJSONString(list));
                        for (SongInfo ll : list) {
                            double round = 0.0;
                            try {
                                round = toFloat(Integer.valueOf(ll.getSize()) ,1024*1024);
                            }catch (Exception e){
                                round = 0.0;
                                e.printStackTrace();
                            }
                            if(String.valueOf(round).startsWith("0")){//小于1M的过滤掉
                                continue;
                            }
                            LocalMusicInfo localMusicInfo = new LocalMusicInfo();
//                            localMusicInfo.name = ll.getAlbumName();
                            localMusicInfo.name = ll.getSongName();
                            localMusicInfo.adminUser = ll.getArtist();
                            localMusicInfo.songUrl = ll.getSongUrl();
                            localMusicInfo.size = ll.getSize();
                            listMusic.add(localMusicInfo);
                        }
                        localMusciAdapter.setNewData(listMusic);
                    }
                });

        localMusciAdapter.setOnItemClickListener((adapter, view, position) -> {
            if (localMusciAdapter.getData().get(position).isSelect) {
                localMusciAdapter.getData().get(position).isSelect = false;
                localMusciAdapter.notifyItemChanged(position);
            } else {
                localMusciAdapter.getData().get(position).isSelect = true;
                localMusciAdapter.notifyItemChanged(position);
            }
        });

        setToolbarRightText("确定", v -> {
            try {
                List<LocalMusicInfo> listLocal = LitePal.findAll(LocalMusicInfo.class);
                List<String> listAdd = new ArrayList<>();
                for (LocalMusicInfo list : listLocal){
                    listAdd.add(list.name);
                }
                List<LocalMusicInfo> data = localMusciAdapter.getData();
                List<LocalMusicInfo> listSelect = new ArrayList<>();
                for (LocalMusicInfo list : data) {
                    if (list.isSelect && !listAdd.contains(list.name)) {
                        listSelect.add(list);
                        LocalMusicInfo localMusicInfo = new LocalMusicInfo();
                        localMusicInfo.name = list.name;
                        localMusicInfo.adminUser = list.adminUser;
                        localMusicInfo.songUrl = list.songUrl;
                        localMusicInfo.size = list.size;
                        localMusicInfo.save();
                    }
                }
                if (listSelect.size() == 0) {
                    toast("请选择音乐，该音乐已上传过！");
                } else {
                    EventBus.getDefault().post(new FirstEvent("指定发送", Constant.YINYUESHUAXIN));
                    finish();
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        },R.color.textcolor);
    }

    public  Double toFloat(int denominator,int numerator) {
        DecimalFormat df=new DecimalFormat("0.00");//设置保留位数
        return Double.valueOf(df.format((float)denominator/numerator));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MediaSessionConnection.getInstance().disconnect();
    }
}
