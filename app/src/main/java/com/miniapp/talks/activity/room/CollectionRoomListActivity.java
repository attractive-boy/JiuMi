package com.miniapp.talks.activity.room;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;

import com.jess.arms.di.component.AppComponent;
import com.miniapp.talks.R;
import com.miniapp.talks.adapter.CollectionRoomListOffAdapter;
import com.miniapp.talks.adapter.CollectionRoomListOnAdapter;
import com.miniapp.talks.app.utils.RxUtils;
import com.miniapp.talks.base.MyBaseArmActivity;
import com.miniapp.talks.base.UserManager;
import com.miniapp.talks.bean.BaseBean;
import com.miniapp.talks.bean.CollectionRoomListBean;
import com.miniapp.talks.di.CommonModule;
import com.miniapp.talks.di.DaggerCommonComponent;
import com.miniapp.talks.service.CommonModel;
import com.miniapp.talks.view.MyListView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

public class CollectionRoomListActivity extends MyBaseArmActivity {

    @Inject
    CommonModel commonModel;
    @BindView(R.id.liveing_list)
    MyListView liveingList;
    @BindView(R.id.no_liveing_list)
    MyListView noLiveingList;
    @BindView(R.id.liveing_text)
    TextView liveingText;
    @BindView(R.id.nolive_text)
    TextView noliveText;
    @BindView(R.id.sss)
    ScrollView sss;
    @BindView(R.id.no_data)
    LinearLayout noData;

    private CollectionRoomListOnAdapter onAdapter; //正在直播的适配器
    private CollectionRoomListOffAdapter offAdapter; //暂未开播的适配器

    private PopupWindow popupWindow, popupWindow1;
    private ImageView imageView, imageView1;


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
        return R.layout.activity_collection_room_list;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        onAdapter = new CollectionRoomListOnAdapter(this);
        liveingList.setAdapter(onAdapter);

        offAdapter = new CollectionRoomListOffAdapter(this);
        noLiveingList.setAdapter(offAdapter);

        getCollectionRoomList();
        liveingList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (popupWindow == null) {
                    View view1 = getLayoutInflater().inflate(R.layout.cancel_collection_item, null);
                    popupWindow = new PopupWindow(view1, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    imageView = view1.findViewById(R.id.cancel_collection);
                    popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    popupWindow.setFocusable(true);
                }
                popupWindow.showAsDropDown(view, 0, -20);
                imageView.setOnClickListener(v -> {
                    int uid = onAdapter.getList_adapter().get(position).getUid();
                    setCelceCollection(String.valueOf(uid), 1, position);
                });

                return true;
            }
        });
        noLiveingList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (popupWindow1 == null) {
                    View view1 = getLayoutInflater().inflate(R.layout.cancel_collection_item, null);
                    popupWindow1 = new PopupWindow(view1, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    imageView1 = view1.findViewById(R.id.cancel_collection);
                    popupWindow1.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    popupWindow1.setFocusable(true);
                }
                popupWindow1.showAsDropDown(view, 0, -20);
                imageView1.setOnClickListener(v -> {
                    int uid = offAdapter.getList_adapter().get(position).getUid();
                    setCelceCollection(String.valueOf(uid), 2, position);
                });
                return true;
            }
        });
        liveingList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                enterData(String.valueOf(onAdapter.getList_adapter().get(position).getUid()), "", commonModel, 1, onAdapter.getList_adapter().get(position).getRoom_cover());
            }
        });
        noLiveingList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                enterData(String.valueOf(offAdapter.getList_adapter().get(position).getUid()), "", commonModel, 1, offAdapter.getList_adapter().get(position).getRoom_cover());
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        setToolbarTitle("收藏的房间", true);
    }

    //获取收藏的房间列表
    private void getCollectionRoomList() {
        RxUtils.loading(commonModel.getCollectionRoomList(String.valueOf(UserManager.getUser().getUserId())), this)
                .subscribe(new ErrorHandleSubscriber<CollectionRoomListBean>(mErrorHandler) {
                    @Override
                    public void onNext(CollectionRoomListBean collectionRoomListBean) {

                        if (collectionRoomListBean.getData().getOn().size() == 0) {
                            liveingText.setVisibility(View.GONE);
                            liveingList.setVisibility(View.GONE);
                        } else {
                            onAdapter.getList_adapter().addAll(collectionRoomListBean.getData().getOn());
                            onAdapter.notifyDataSetChanged();
                        }
                        if (collectionRoomListBean.getData().getOff().size() == 0) {
                            noliveText.setVisibility(View.GONE);
                            noLiveingList.setVisibility(View.GONE);
                        } else {
                            offAdapter.getList_adapter().addAll(collectionRoomListBean.getData().getOff());
                            offAdapter.notifyDataSetChanged();
                        }
                        if (collectionRoomListBean.getData().getOn().size() == 0 && collectionRoomListBean.getData().getOff().size() == 0) {
                            sss.setVisibility(View.GONE);
                            noData.setVisibility(View.VISIBLE);
                        } else {
                            noData.setVisibility(View.GONE);
                            sss.setVisibility(View.VISIBLE);
                        }
                    }
                });
    }

    private void setCelceCollection(String uid, int a, int pos) {
        RxUtils.loading(commonModel.remove_mykeep(uid,
                String.valueOf(UserManager.getUser().getUserId())), this)
                .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                    @Override
                    public void onNext(BaseBean baseBean) {
                        toast(baseBean.getMessage());

                        if (a == 1) {
                            onAdapter.getList_adapter().remove(pos);
                            onAdapter.notifyDataSetChanged();
                            popupWindow.dismiss();
                        } else if (a == 2) {
                            offAdapter.getList_adapter().remove(pos);
                            offAdapter.notifyDataSetChanged();
                            popupWindow1.dismiss();
                        }
                    }
                });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
//        LogUtils.debugInfo("====onRestart");
        if (AdminHomeActivity.isStart && AdminHomeActivity.isTop) {
            startActivity(new Intent(this, AdminHomeActivity.class));
        }
    }
}
