package com.miniapp.talks.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.miniapp.talks.R;
import com.miniapp.talks.app.view.CircularImage;
import com.miniapp.talks.base.MyBaseAdapter;
import com.miniapp.talks.bean.LookCommentBean;
import com.miniapp.talks.utils.JudgeImageUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LookCommentAdapter extends MyBaseAdapter<LookCommentBean.DataBean.CommentsBean> {

    private Context context;

    public LookCommentAdapter(Context context) {
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LookCommentHolder LH;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.hot_comment_item, parent, false);
            LH = new LookCommentHolder(convertView);
            convertView.setTag(LH);
        } else {
            LH = (LookCommentHolder) convertView.getTag();
        }

        //头像
        if (!TextUtils.isEmpty(list_adapter.get(position).getHeadimgurl())) {
            ArmsUtils.obtainAppComponentFromContext(context)
                    .imageLoader()
                    .loadImage(context, ImageConfigImpl
                            .builder()
                            .url(list_adapter.get(position).getHeadimgurl())
                            .placeholder(R.mipmap.no_tou)
                            .imageView(LH.headImage)
                            .errorPic(R.mipmap.no_tou)
                            .build());
        }
        //昵称以及评论内容
        LH.nameText.setText(list_adapter.get(position).getNickname());
        if (TextUtils.isEmpty(list_adapter.get(position).getReply())) {
            LH.replyText.setVisibility(View.GONE);
            LH.replyNameText.setVisibility(View.GONE);
            LH.contentText.setText(list_adapter.get(position).getContent());
        } else {
            LH.replyText.setVisibility(View.VISIBLE);
            LH.replyNameText.setVisibility(View.VISIBLE);
            LH.replyNameText.setText(list_adapter.get(position).getReply());
            LH.contentText.setText("：" + list_adapter.get(position).getContent());
        }
        //时间
        LH.timeText.setText(list_adapter.get(position).getCreated_at());
        //点赞
        LH.fabulous.setText(list_adapter.get(position).getPraise() + "");
        //等级
        JudgeImageUtil.noZeroVIP(list_adapter.get(position).getVip_level(), LH.gradeImage);

        if (list_adapter.get(position).getIs_praise() == 1) {
            LH.fabulous.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.dongtai_hudong_yidianzan, 0, 0, 0);
        } else {
            LH.fabulous.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.dongtai_hudong_dianzan, 0, 0, 0);
        }

        //点击内容回调
        LH.neirong.setOnClickListener(v -> {
            if (onOneClick != null) {
                onOneClick.oneClick(position);
            }
        });

        //点赞回调
        LH.fabulous.setOnClickListener(v -> {
            if (onThreeClick != null) {
                onThreeClick.threeClick(position);
            }
        });

        LH.headImage.setOnClickListener(v -> {
            if (onTouXiang != null) {
                onTouXiang.touXiang(position);
            }
        });

        return convertView;
    }

    static class LookCommentHolder {
        @BindView(R.id.head_image)
        CircularImage headImage;
        @BindView(R.id.name_text)
        TextView nameText;
        @BindView(R.id.grade_image)
        ImageView gradeImage;
        @BindView(R.id.time_text)
        TextView timeText;
        @BindView(R.id.reply_text)
        TextView replyText;
        @BindView(R.id.reply_name_text)
        TextView replyNameText;
        @BindView(R.id.content_text)
        TextView contentText;
        @BindView(R.id.neirong)
        LinearLayout neirong;
        @BindView(R.id.fabulous)
        TextView fabulous;
        @BindView(R.id.line)
        View line;

        public LookCommentHolder(@NonNull View itemView) {
            ButterKnife.bind(this, itemView);
        }
    }

    public void setOnOneClick(OnOneClick onOneClick) {
        this.onOneClick = onOneClick;
    }

    public interface OnOneClick {
        void oneClick(int pos);
    }

    OnOneClick onOneClick;

    public interface OnThreeClick {
        void threeClick(int pos);
    }

    OnThreeClick onThreeClick;

    public void setOnThreeClick(OnThreeClick onThreeClick) {
        this.onThreeClick = onThreeClick;
    }

    public interface onTouXiang {
        void touXiang(int pos);
    }

    onTouXiang onTouXiang;

    public void setOnTouXiang(onTouXiang onTouXiang) {
        this.onTouXiang = onTouXiang;
    }
}
