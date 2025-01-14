package com.miniapp.talks.popup;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.RelativeLayout;

import com.miniapp.talks.R;
import com.miniapp.talks.activity.room.AdminHomeActivity;
import com.miniapp.talks.adapter.BXAdapter;
import com.miniapp.talks.bean.FirstEvent;
import com.miniapp.talks.bean.OpenBoxBean;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;

import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.miniapp.talks.utils.Constant.XIANSHIWANBI;

//宝箱开后显示的那个页面
public class BoxGiftActivity extends Dialog {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.bj)
    RelativeLayout bj;
    @BindView(R.id.svga)
    SVGAImageView svgaImageView;
    private Activity mContext;
    private OpenBoxBean mOpenBoxBean;
    private BXAdapter mAdapter;

    public BoxGiftActivity(@NonNull Activity context, OpenBoxBean openBoxBean) {
        super(context, R.style.myChooseDialog);
        mContext = context;
        this.mOpenBoxBean = openBoxBean;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_box_gift);
        ButterKnife.bind(this);
        setWidows();
        setData();
        addLayoutAnimation(recyclerview);
    }

    //设置数据
    private void setData() {

        SVGAParser parser = new SVGAParser(mContext);
        parser.decodeFromAssets("light.svga", new SVGAParser.ParseCompletion() {
            @Override
            public void onComplete(@NotNull SVGAVideoEntity svgaVideoEntity) {
                svgaImageView.setVideoItem(svgaVideoEntity);
                svgaImageView.setLoops(0);
                svgaImageView.stepToFrame(1, true);
            }

            @Override
            public void onError() {

            }
        });

        if (0 < mOpenBoxBean.getData().getAwardList().size() && 5 > mOpenBoxBean.getData().getAwardList().size()) {
            bj.setBackgroundResource(R.mipmap.bx_yhtc);
        } else if (4 < mOpenBoxBean.getData().getAwardList().size() && 9 > mOpenBoxBean.getData().getAwardList().size()) {
            bj.setBackgroundResource(R.mipmap.bx_ehtc);
        } else {
            bj.setBackgroundResource(R.mipmap.bx_shtc);
        }

        mAdapter = new BXAdapter();
        GridLayoutManager GM = new GridLayoutManager(mContext, 4);
        recyclerview.setLayoutManager(GM);
        recyclerview.setAdapter(mAdapter);
        mAdapter.setNewData(mOpenBoxBean.getData().getAwardList());


    }

    private void setWidows() {

        Display display = getWindow().getWindowManager().getDefaultDisplay();

        WindowManager.LayoutParams lp = getWindow().getAttributes();

        lp.width = (int) (display.getWidth());

        lp.alpha = 1.0f;

        lp.gravity = Gravity.CENTER;

        getWindow().setAttributes(lp);

        this.setCanceledOnTouchOutside(true);

    }

    private void addLayoutAnimation(ViewGroup view) {

        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.item_animation_from_right);
        LayoutAnimationController layoutAnimationController = new LayoutAnimationController(animation);
        layoutAnimationController.setDelay(1f);
        layoutAnimationController.setOrder(LayoutAnimationController.ORDER_NORMAL);
        view.setLayoutAnimation(layoutAnimationController);
        view.setLayoutAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (mOpenBoxBean.getData().getIspointsfirst() == 1) {
                    BoxFirstOpenWindow bow = new BoxFirstOpenWindow(mContext);
                    bow.showAtLocation(recyclerview, Gravity.CENTER, 0, 0);
                }
                EventBus.getDefault().post(new FirstEvent("动画完成", "donghuawancheng"));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }


    @Override
    public void dismiss() {
        super.dismiss();
        EventBus.getDefault().post(new FirstEvent("显示完毕", XIANSHIWANBI));
    }
}
