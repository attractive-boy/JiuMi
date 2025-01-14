package com.miniapp.talks.popup;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.jess.arms.http.imageloader.glide.GlideArms;
import com.miniapp.talks.R;
import com.miniapp.talks.app.view.CircularImage;
import com.miniapp.talks.service.CommonModel;
import com.miniapp.talks.utils.PwdEditText;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PwdWindow extends Dialog {
    @BindView(R.id.guan_btn)
    ImageView guanBtn;
    @BindView(R.id.head_image)
    CircularImage headImage;
    @BindView(R.id.pwd_text)
    PwdEditText pwdText;
    @BindView(R.id.error_tit)
    TextView errorTit;
    private Activity mContext;

    public CircularImage getHeadImage() {
        return headImage;
    }

    public PwdEditText getPwdText() {
        return pwdText;
    }

    public TextView getErrorTit() {
        return errorTit;
    }

    public PwdWindow(Activity context) {
        super(context);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pwd_window);
        ButterKnife.bind(this);
        setWidows();
    }

    private void setWidows() {
        Display display = getWindow().getWindowManager().getDefaultDisplay();

        WindowManager.LayoutParams lp = getWindow().getAttributes();

        lp.width = (int) (display.getWidth() - QMUIDisplayHelper.dp2px(mContext, 38));

        lp.alpha = 1.0f;

        lp.gravity = Gravity.CENTER;

        getWindow().setAttributes(lp);

        this.setCanceledOnTouchOutside(true);

        guanBtn.setOnClickListener(v -> {
            dismiss();
        });
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }
}
