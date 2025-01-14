package com.miniapp.talks.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.http.imageloader.glide.GlideArms;
import com.miniapp.talks.R;
import com.miniapp.talks.bean.RoomMultipleItem;

import java.util.List;

public class RoomUserListAdapter extends BaseMultiItemQuickAdapter<RoomMultipleItem, BaseViewHolder> {

    private int mMicUpUser, mMicUpUserLine, mMicDownUser;

    Context mContext;

    List<RoomMultipleItem> mDataList;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public RoomUserListAdapter(Context context, List<RoomMultipleItem> data) {
        super(data);
        mContext = context;
        mDataList = data;
        //必须绑定type和layout的关系
        addItemType(RoomMultipleItem.TITLE_MIC_UP, R.layout.item_room_user_mic_title);
        addItemType(RoomMultipleItem.MIC_UP, R.layout.item_room_item);
        addItemType(RoomMultipleItem.TITLE_MIC_DOWN, R.layout.item_room_user_mic_title);
        addItemType(RoomMultipleItem.MIC_DOWN, R.layout.item_room_item);
    }

    public void setUserCount(int micUpUser, int micUpUserLine, int micDownUser) {
        this.mMicUpUser = micUpUser;
        this.mMicUpUserLine = micUpUserLine;
        this.mMicDownUser = micDownUser;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, RoomMultipleItem item) {

        switch (helper.getItemViewType()) {
            case RoomMultipleItem.TITLE_MIC_UP:
                helper.setText(R.id.tv_mul_title, "麦上用户" + mMicUpUserLine + "/" + mMicUpUser);
                break;
            case RoomMultipleItem.MIC_UP:

                ImageView imageView = helper.getView(R.id.img_head);

                GlideArms.with(mContext)
                        .load(item.getData().getHeadimgurl())
                        .placeholder(R.mipmap.no_tou)
                        .error(R.mipmap.no_tou)
                        .circleCrop()
                        .into(imageView);

                helper.setText(R.id.shape_tv_mic, "下麦");

                helper.setText(R.id.tv_user_name, item.getData().getNickname());

                helper.setText(R.id.tv_user_id, "ID:  "+item.getData().getId());

                break;
            case RoomMultipleItem.TITLE_MIC_DOWN:
                helper.setText(R.id.tv_mul_title, "麦下用户" + mMicDownUser);
                break;
            case RoomMultipleItem.MIC_DOWN:

                ImageView imageView1 = helper.getView(R.id.img_head);

                GlideArms.with(mContext)
                        .load(item.getData().getHeadimgurl())
                        .placeholder(R.mipmap.no_tou)
                        .error(R.mipmap.no_tou)
                        .circleCrop()
                        .into(imageView1);

                helper.setText(R.id.tv_user_name, item.getData().getNickname());

                helper.setText(R.id.tv_user_id, "ID:  "+item.getData().getId());

                helper.setText(R.id.shape_tv_mic, "上麦");

                break;
        }

        helper.addOnClickListener(R.id.shape_tv_mic);
    }
}
