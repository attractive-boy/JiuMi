package com.miniapp.talks.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jess.arms.http.imageloader.glide.GlideArms;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.LogUtils;
import com.miniapp.talks.R;
import com.miniapp.talks.bean.PushBean;
import com.orient.tea.barragephoto.adapter.BarrageAdapter;

public class DanMuViewHolder extends BarrageAdapter.BarrageViewHolder<PushBean> {

    private Context mContext;

    private ImageView imgGiftLeft;
//    private TextView textUSer1, textUSer2, textNumber, textGiftName, box, OkBtn, wuYongOne;

    RelativeLayout mLayoutGift,mLayoutGemStone;

    TextView mTvGift,mTvGemStone;

    public DanMuViewHolder(View itemView,Context context) {
        super(itemView);
        this.mContext = context;

        mLayoutGift = itemView.findViewById(R.id.layout_gift_notification);
        mTvGift = itemView.findViewById(R.id.tv_gift_info);

        mLayoutGemStone = itemView.findViewById(R.id.layout_gemstone_notification);
        mTvGemStone = itemView.findViewById(R.id.tv_gemstone_info);
        imgGiftLeft = itemView.findViewById(R.id.imgGift_left);
//        textUSer1 = itemView.findViewById(R.id.textUSer1);
//        textUSer2 = itemView.findViewById(R.id.textUSer2);
//        textNumber = itemView.findViewById(R.id.textNumber);
//        textGiftName = itemView.findViewById(R.id.textGiftName);
//        box = itemView.findViewById(R.id.box);
//        OkBtn = itemView.findViewById(R.id.ok_btn);
//        wuYongOne = itemView.findViewById(R.id.wuyong_one);
    }

    @Override
    protected void onBind(PushBean pushBean) {
        if (pushBean != null) {
            if ("gift".equals(pushBean.type)) {
                mLayoutGemStone.setVisibility(View.GONE);
                mLayoutGift.setVisibility(View.VISIBLE);


                mTvGift.setTextColor(mContext.getResources().getColor(R.color.white));


                mTvGift.append(new SpannableString("惊现土豪~"));

                SpannableString clickString = new SpannableString(pushBean.getData().getFrom_name());

                clickString.setSpan(new ClickableSpan() {
                    @Override
                    public void onClick(View widget) {//点击事件

                    }

                    @Override
                    public void updateDrawState(TextPaint ds) {
                        super.updateDrawState(ds);
                        ds.setColor(Color.parseColor("#ffde00"));//设置颜色
                        ds.setUnderlineText(false);//去掉下划线
                    }
                }, 0, clickString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                mTvGift.append(clickString);

                mTvGift.append(new SpannableString("赠送给"));

                SpannableString clickString1 = new SpannableString(pushBean.getData().getUser_name()+pushBean.getData().getGift_name()+"x"+pushBean.getData().getNum());

                clickString1.setSpan(new ClickableSpan() {
                    @Override
                    public void onClick(View widget) {//点击事件

                    }

                    @Override
                    public void updateDrawState(TextPaint ds) {
                        super.updateDrawState(ds);
                        ds.setColor(Color.parseColor("#ffde00"));//设置颜色
                        ds.setUnderlineText(false);//去掉下划线
                    }
                }, 0, clickString1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                mTvGift.append(clickString1);

                mTvGift.setMovementMethod(LinkMovementMethod.getInstance());

                //设置超文本点击后的背景色为透明
                mTvGift.setHighlightColor(mContext.getResources().getColor(android.R.color.transparent));

//                mTvGift.setText("惊现土豪~"+pushBean.getData().getFrom_name()+"赠送给"+pushBean.getData().getUser_name()+pushBean.getData().getGift_name()+"x"+pushBean.getData().getNum());
//                wuYongOne.setText("惊现土豪~");
//                textUSer1.setText(pushBean.getData().getFrom_name());
//                textUSer2.setText(pushBean.getData().getUser_name());
////                    textNumber.setText(pushBean.getData().getNum() + "个");
//                box.setText("赠送给");
//                textGiftName.setText(pushBean.getData().getGift_name()+"x"+pushBean.getData().getNum());
                String imgUrls = pushBean.getData().getImg();
                LogUtils.debugInfo("收到开礼物通知了===imgUrls="+imgUrls);

                if(!TextUtils.isEmpty(imgUrls)){

                    imgGiftLeft.setVisibility(View.VISIBLE);

                    ArmsUtils.obtainAppComponentFromContext(mContext)
                            .imageLoader()
                            .loadImage(mContext, ImageConfigImpl
                                    .builder()
                                    .url(imgUrls)
//                                    .placeholder(R.mipmap.no_tu)
                                    .imageView(imgGiftLeft)
//                                    .errorPic(R.mipmap.no_tu)
                                    .build());

                } else {
                    imgGiftLeft.setVisibility(View.GONE);
                }

//                GlideArms
//                        .with(mContext)
//                        .load(imgUrls)
//                        .into(imgGiftLeft);
//                OkBtn.setVisibility(View.VISIBLE);
            } else if ("award".equals(pushBean.type)) {
                LogUtils.debugInfo("收到开礼物通知了====");
                mLayoutGemStone.setVisibility(View.VISIBLE);
                mLayoutGift.setVisibility(View.GONE);


                mTvGemStone.setTextColor(mContext.getResources().getColor(R.color.white));


                mTvGemStone.append(new SpannableString("哇哦~"));

                SpannableString clickString = new SpannableString(pushBean.getData().getUser_name());

                clickString.setSpan(new ClickableSpan() {
                    @Override
                    public void onClick(View widget) {//点击事件

                    }

                    @Override
                    public void updateDrawState(TextPaint ds) {
                        super.updateDrawState(ds);
                        ds.setColor(Color.parseColor("#ff3e6d"));//设置颜色
                        ds.setUnderlineText(false);//去掉下划线
                    }
                }, 0, clickString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                mTvGemStone.append(clickString);

                mTvGemStone.append(new SpannableString("在" + pushBean.getData().getBoxclass() + "开出了"));

                SpannableString clickString1 = new SpannableString(pushBean.getData().getGift_name());

                clickString1.setSpan(new ClickableSpan() {
                    @Override
                    public void onClick(View widget) {//点击事件

                    }

                    @Override
                    public void updateDrawState(TextPaint ds) {
                        super.updateDrawState(ds);
                        ds.setColor(Color.parseColor("#ff3e6d"));//设置颜色
                        ds.setUnderlineText(false);//去掉下划线
                    }
                }, 0, clickString1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                mTvGemStone.append(clickString1);

                mTvGemStone.setMovementMethod(LinkMovementMethod.getInstance());

                //设置超文本点击后的背景色为透明
                mTvGemStone.setHighlightColor(mContext.getResources().getColor(android.R.color.transparent));

//                mTvGemStone.setText("哇哦~"+pushBean.getData().getUser_name()+"在" + pushBean.getData().getBoxclass() + "开出了"+pushBean.getData().getGift_name());
//                    String text = "哇哦，"+pushBean.getData().getUser_name()+"在"+pushBean.getData().getBoxclass()+"开出了"+pushBean.getData().getGift_name()+"，真是太优秀了！";
//                wuYongOne.setText("哇哦~");
//                textUSer1.setText(pushBean.getData().getUser_name());
//                box.setText("在" + pushBean.getData().getBoxclass() + "开出了");
//                textUSer2.setText(pushBean.getData().getGift_name());
//                textNumber.setText("");
////                textNumber.setText("，真是太优秀了！");
//                GlideArms
//                        .with(mContext)
//                        .load(pushBean.getData().getImg())
//                        .into(imgGift);
//                OkBtn.setVisibility(View.GONE);


            }
        }
    }
}
