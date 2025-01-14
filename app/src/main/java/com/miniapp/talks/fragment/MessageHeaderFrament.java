package com.miniapp.talks.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jess.arms.http.imageloader.glide.GlideArms;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.miniapp.talks.R;
import com.miniapp.talks.activity.message.MessageOfficeActivity;
import com.miniapp.talks.bean.MiniOfficBean;

import java.util.List;

import io.rong.imkit.RongExtension;
import io.rong.imkit.fragment.ConversationListFragment;

/**
 * 作者:sgm
 * 描述:为消息列表添加头布局
 */
public class MessageHeaderFrament extends ConversationListFragment {
    private MiniOfficBean miniOfficBean;
    private TextView textNum;
    private TextView textTime;
    private TextView tv_userid;
    private TextView tv_title;
    private ImageView ci_head;
    private LinearLayout llRight;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        miniOfficBean = (MiniOfficBean) getArguments().getSerializable("miniOfficBean");
    }

//    @Override
//    protected List<View> onAddHeaderView() {
//        List<View> headerViews = super.onAddHeaderView();
//        View inflate = ArmsUtils.inflate(getActivity(), R.layout.item_message_header);
//        ci_head = inflate.findViewById(R.id.ci_head);
//        tv_title = inflate.findViewById(R.id.tv_title);
//        tv_userid = inflate.findViewById(R.id.tv_userid);
//        textTime = inflate.findViewById(R.id.textTime);
//        textNum = inflate.findViewById(R.id.textNum);
//        llRight = inflate.findViewById(R.id.llRight);
//        GlideArms.with(getActivity())
//                .load(miniOfficBean.getData().getImg())
//                .placeholder(R.mipmap.default_home)
//                .error(R.mipmap.default_home)
//                .circleCrop()
//                .into(ci_head);
//        tv_title.setText(miniOfficBean.getData().getName());
//        tv_userid.setText(miniOfficBean.getData().getTitle());
//        int unread = miniOfficBean.getData().getUnread();
//        if(unread > 0){
//            llRight.setVisibility(View.VISIBLE);
//            textTime.setText(miniOfficBean.getData().getCreated_at());
//            textNum.setText(miniOfficBean.getData().getUnread()+ "");
//        }else {
//            llRight.setVisibility(View.GONE);
//        }
//        inflate.setOnClickListener(v -> {
//            ArmsUtils.startActivity(MessageOfficeActivity.class);
//        });
//        headerViews.add(inflate);
//        return headerViews;
//    }

//    public void refresh(MiniOfficBean miniOfficBean) {
//        int unread = miniOfficBean.getData().getUnread();
//        if(unread > 0){
//            llRight.setVisibility(View.VISIBLE);
//            textTime.setText(miniOfficBean.getData().getCreated_at());
//            textNum.setText(miniOfficBean.getData().getUnread()+ "");
//        }else {
//            llRight.setVisibility(View.GONE);
//        }
//    }
}
