package com.miniapp.talks.popup;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.tu.loadingdialog.LoadingDailog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.miniapp.talks.R;
import com.miniapp.talks.activity.room.AdminHomeActivity;
import com.miniapp.talks.adapter.RoomUserListAdapter;
import com.miniapp.talks.app.utils.RxUtils;
import com.miniapp.talks.app.view.CircularImage;
import com.miniapp.talks.bean.MicUserBean;
import com.miniapp.talks.bean.RoomMultipleItem;
import com.miniapp.talks.bean.RoomUsersBean;
import com.miniapp.talks.service.CommonModel;
import com.miniapp.talks.utils.ToastUtil;
import com.miniapp.talks.view.ShapeTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

/**
 * 抱人上麦dialog
 */
public class SelectPeopleUpVideoDialog extends Dialog {


    @BindView(R.id.et_user_id)
    EditText mEtUserId;
    @BindView(R.id.img_delete)
    ImageView mImgDelete;
    @BindView(R.id.btn_confirm)
    ShapeTextView mBtnConfirm;
    @BindView(R.id.rcv_user)
    RecyclerView mRcvUser;
    @BindView(R.id.rcv_search)
    RecyclerView mRcvSearch;
    @BindView(R.id.tv_no_search_result)
    TextView mTvNoSearchResult;
    @BindView(R.id.layout_search)
    LinearLayout mLayoutSearch;
    private AdminHomeActivity mContext;

    OnOperationMicListener mOnOperationMicListener;

    RoomUserListAdapter mAdapter;

    List<RoomMultipleItem> mDataList = new ArrayList<>();

    private int mMicUpUser, mMicUpUserLine, mMicDownUser;

    int mMicPosition;

    CommonModel mCommonModel;

    String mUId;

    RxErrorHandler mRxErrorHandler;

    public SelectPeopleUpVideoDialog(@NonNull Activity context, int position, OnOperationMicListener listener) {
        super(context, R.style.myChooseDialog);
        mContext = (AdminHomeActivity) context;
        this.mMicPosition = position;
        this.mOnOperationMicListener = listener;
    }

    public void setUserCount(List<RoomMultipleItem> list, int micUpUser, int micUpUserLine, int micDownUser) {
        this.mDataList = list;
        this.mMicUpUser = micUpUser;
        this.mMicUpUserLine = micUpUserLine;
        this.mMicDownUser = micDownUser;
        mAdapter.setUserCount(mMicUpUser, mMicUpUserLine, mMicDownUser);
        mAdapter.setNewData(list);
    }

    public void setInfo(CommonModel commonModel, String uId, RxErrorHandler rxErrorHandler){
        mCommonModel = commonModel;
        mUId = uId;
        mRxErrorHandler = rxErrorHandler;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_choice_people_up_video);

        ButterKnife.bind(this);

        setWidows();

        LinearLayoutManager manager = new LinearLayoutManager(mContext);

        manager.setOrientation(RecyclerView.VERTICAL);

        mRcvUser.setHasFixedSize(true);

        mRcvUser.setLayoutManager(manager);

        mAdapter = new RoomUserListAdapter(mContext, mDataList);

        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                if (mDataList == null || mDataList.size() == 0) {
                    return;
                }

                RoomMultipleItem item = mDataList.get(position);

                if (item == null) {
                    return;
                }

                MicUserBean micUserBean = item.getData();

                if (micUserBean == null || TextUtils.isEmpty(micUserBean.getId())) {
                    return;
                }
                switch (view.getId()) {
                    case R.id.shape_tv_mic:

                        if (mOnOperationMicListener == null) {
                            return;
                        }

                        int micStatus = micUserBean.getIs_mic();

                        if (micStatus == 1) {//1,在麦上，0 麦下
                            mOnOperationMicListener.toDownMic(micUserBean.getId());
                            dismiss();
                        } else {
                            mOnOperationMicListener.toUpMic(mMicPosition, micUserBean.getId());
                            dismiss();
                        }
                        break;
                }
            }
        });

        mRcvUser.setAdapter(mAdapter);

        mEtUserId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String text = editable.toString();
                if (TextUtils.isEmpty(text)) {
                    mImgDelete.setVisibility(View.GONE);
                    clearSearch();
                } else {
                    mImgDelete.setVisibility(View.VISIBLE);
                }

            }
        });

    }

    private void clearSearch() {

        mLayoutSearch.setVisibility(View.GONE);

        mRcvUser.setVisibility(View.VISIBLE);

    }

    private void setWidows() {

        Display display = getWindow().getWindowManager().getDefaultDisplay();

        WindowManager.LayoutParams lp = getWindow().getAttributes();

        lp.width = (int) (display.getWidth());

        lp.alpha = 1.0f;

        lp.gravity = Gravity.BOTTOM;

        getWindow().setAttributes(lp);

        this.setCanceledOnTouchOutside(true);

    }

    @OnClick({R.id.img_delete, R.id.btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_delete:
                mEtUserId.setText("");
                clearSearch();
                break;
            case R.id.btn_confirm:
                String text = mEtUserId.getText().toString().trim();
                if(TextUtils.isEmpty(text)){
                    ToastUtil.showToast(mContext,"请输入用户ID");
                    return;
                }

                LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(mContext)
                        .setMessage("加载中...")
                        .setCancelable(true)
                        .setCancelOutside(true);
                LoadingDailog dialog = loadBuilder.create();
                dialog.show();
                RxUtils.loading(mCommonModel.getRoomUsers(mUId,
                        text), mContext)
                        .subscribe(new ErrorHandleSubscriber<RoomUsersBean>(mRxErrorHandler) {
                            @Override
                            public void onNext(RoomUsersBean roomUsersBean) {
                                dialog.dismiss();
                                if(roomUsersBean != null){

                                    RoomUsersBean.DataBean dataBean = roomUsersBean.getData();

                                    if(dataBean != null){

                                        List<RoomMultipleItem> roomMultipleItemList = new ArrayList<>();

                                        List<MicUserBean> micUserBeanList = dataBean.getMic_user();//麦上

                                        List<MicUserBean> roomUserBeanList = dataBean.getRoom_user();//麦下

                                        List<MicUserBean> seaUserBeanList = dataBean.getSea_user();//搜索

                                        mLayoutSearch.setVisibility(View.VISIBLE);

                                        mRcvUser.setVisibility(View.GONE);

                                        if(seaUserBeanList == null || seaUserBeanList.size() == 0){
                                            mTvNoSearchResult.setVisibility(View.VISIBLE);
                                            mRcvSearch.setVisibility(View.GONE);
                                        } else {

                                            mTvNoSearchResult.setVisibility(View.GONE);

                                            mRcvSearch.setVisibility(View.VISIBLE);

                                            LinearLayoutManager manager = new LinearLayoutManager(mContext);

                                            manager.setOrientation(LinearLayoutManager.VERTICAL);

                                            mRcvSearch.setLayoutManager(manager);

                                            RoomMultipleItem roomMultipleItem;
                                            //麦上
                                            MicUserBean micUserBean;
                                            for (int i = 0; i < seaUserBeanList.size(); i++) {

                                                micUserBean = seaUserBeanList.get(i);

                                                if(micUserBean.getIs_mic() == 1){
                                                    roomMultipleItem = new RoomMultipleItem(RoomMultipleItem.MIC_UP, micUserBean);
                                                } else {
                                                    roomMultipleItem = new RoomMultipleItem(RoomMultipleItem.MIC_DOWN, micUserBean);
                                                }

                                                roomMultipleItemList.add(roomMultipleItem);
                                            }
                                            RoomUserListAdapter adapter = new RoomUserListAdapter(mContext,roomMultipleItemList);
                                            mRcvSearch.setAdapter(adapter);
                                            adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                                                @Override
                                                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                                    switch (view.getId()) {
                                                        case R.id.shape_tv_mic:

                                                            if (mOnOperationMicListener == null) {
                                                                return;
                                                            }

                                                            MicUserBean bean= roomMultipleItemList.get(position).getData();

                                                            int micStatus = bean.getIs_mic();

                                                            if (micStatus == 1) {//1,在麦上，0 麦下
                                                                mOnOperationMicListener.toDownMic(bean.getId());
                                                                dismiss();
                                                            } else {
                                                                mOnOperationMicListener.toUpMic(mMicPosition, bean.getId());
                                                                dismiss();
                                                            }
                                                            break;
                                                    }
                                                }
                                            });
                                        }


                                    }
                                }
                            }

                            @Override
                            public void onError(Throwable t) {
                                super.onError(t);
                                dialog.dismiss();
                            }
                        });
                break;
        }
    }

    public interface OnOperationMicListener {
        void toUpMic(int position, String userId);

        void toDownMic(String userId);
    }

}
