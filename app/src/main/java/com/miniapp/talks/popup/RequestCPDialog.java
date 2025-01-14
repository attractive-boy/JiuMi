package com.miniapp.talks.popup;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.miniapp.talks.R;
import com.miniapp.talks.app.view.CircularImage;
import com.miniapp.talks.base.UserManager;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 购买开宝箱的钥匙dialog
 */
public class RequestCPDialog extends Dialog {


    @BindView(R.id.img_head_left)
    CircularImage mImgHeadLeft;
    @BindView(R.id.img_head_right)
    CircularImage mImgHeadRight;
    @BindView(R.id.tv_who_want_cp)
    TextView mTvWhoWantCp;
    @BindView(R.id.tv_left)
    TextView mTvLeft;
    @BindView(R.id.tv_right)
    TextView mTvRight;
    private Activity mContext;

    View.OnClickListener mOnClickListener;

    String mUserId,mUserName,mUserHeadUrl;

    public RequestCPDialog(@NonNull Activity context,View.OnClickListener clickListener,String userId,String userName,String userHeadUrl) {
        super(context, R.style.myChooseDialog);
        mContext = context;
        this.mOnClickListener = clickListener;
        this.mUserId = userId;
        this.mUserName = userName;
        this.mUserHeadUrl = userHeadUrl;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_receive_cp_request);

        ButterKnife.bind(this);

        setWidows();

        ArmsUtils.obtainAppComponentFromContext(mContext)
                .imageLoader()
                .loadImage(mContext, ImageConfigImpl
                        .builder()
                        .url(mUserHeadUrl)
                        .placeholder(R.mipmap.no_tou)
                        .imageView(mImgHeadLeft)
                        .errorPic(R.mipmap.no_tou)
                        .build());
        mTvWhoWantCp.setText(mUserName+"想与你组成守护CP");

        ArmsUtils.obtainAppComponentFromContext(mContext)
                .imageLoader()
                .loadImage(mContext, ImageConfigImpl
                        .builder()
                        .url(UserManager.getUser().getHeadimgurl())
                        .placeholder(R.mipmap.no_tou)
                        .imageView(mImgHeadRight)
                        .errorPic(R.mipmap.no_tou)
                        .build());
    }

    private void setWidows() {

        Display display = getWindow().getWindowManager().getDefaultDisplay();

        WindowManager.LayoutParams lp = getWindow().getAttributes();

        lp.width = (int) (display.getWidth() - QMUIDisplayHelper.dpToPx(80));

        lp.alpha = 1.0f;

        lp.gravity = Gravity.CENTER;

        getWindow().setAttributes(lp);

        this.setCanceledOnTouchOutside(false);

        setCancelable(false);

    }


    @OnClick({R.id.tv_left, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_left:
                dismiss();
                if(mOnClickListener != null){
                    mOnClickListener.onClick(view);
                }
                break;
            case R.id.tv_right:
                dismiss();
                if(mOnClickListener != null){
                    mOnClickListener.onClick(view);
                }
                break;
        }
    }
}
