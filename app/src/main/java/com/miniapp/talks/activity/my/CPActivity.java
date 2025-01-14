package com.miniapp.talks.activity.my;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.imageloader.glide.GlideArms;
import com.makeramen.roundedimageview.RoundedImageView;
import com.miniapp.talks.R;
import com.miniapp.talks.adapter.CPDetailsAdapter;
import com.miniapp.talks.app.utils.RxUtils;
import com.miniapp.talks.base.MyBaseArmActivity;
import com.miniapp.talks.bean.CPDetailsBean;
import com.miniapp.talks.bean.CommentBean;
import com.miniapp.talks.bean.FirstEvent;
import com.miniapp.talks.di.CommonModule;
import com.miniapp.talks.di.DaggerCommonComponent;
import com.miniapp.talks.popup.PuTongWindow;
import com.miniapp.talks.service.CommonModel;
import com.miniapp.talks.utils.MyUtil;
import com.miniapp.talks.view.MyGridView;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

import static com.miniapp.talks.utils.Constant.JIECHUCP;

public class CPActivity extends MyBaseArmActivity {
    @Inject
    CommonModel commonModel;

    @BindView(R.id.cp_relieve)
    TextView cpRelieve;
    @BindView(R.id.cp_time)
    TextView cpTime;
    @BindView(R.id.cp_da_bj)
    ImageView cpDaBj;
    @BindView(R.id.cp_rank)
    TextView cpRank;
    @BindView(R.id.cp_one_head)
    RoundedImageView cpOneHead;
    @BindView(R.id.cp_one_name)
    TextView cpOneName;
    @BindView(R.id.one_cp)
    LinearLayout oneCp;
    @BindView(R.id.cp_two_head)
    RoundedImageView cpTwoHead;
    @BindView(R.id.cp_two_name)
    TextView cpTwoName;
    @BindView(R.id.two_cp)
    LinearLayout twoCp;
    @BindView(R.id.cp_xindiantu_one)
    ImageView cpXindiantuOne;
    @BindView(R.id.cp_xindiantu_two)
    ImageView cpXindiantuTwo;
    @BindView(R.id.have_cp)
    RelativeLayout haveCp;
    @BindView(R.id.mygridview)
    MyGridView mygridview;
    @BindView(R.id.cp_bag)
    ImageView cpBag;
    @BindView(R.id.one)
    LinearLayout one;
    @BindView(R.id.xingrui_now_image)
    TextView xingruiNowImage;
    @BindView(R.id.xingrui_next_image)
    TextView xingruiNextImage;
    @BindView(R.id.progress_bar2)
    ProgressBar progressBar2;
    @BindView(R.id.dengji)
    TextView dengji;

    private String mCpId;//cp的id
    private String type;
    private CPDetailsAdapter mAdapter; //守护特权的适配器

    private TextView nameText, titText;
    private ImageView imageView;

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
        return R.layout.activity_cp;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setTitle("守护详情");
        mCpId = getIntent().getStringExtra("id");
        type = getIntent().getStringExtra("type");
        mAdapter = new CPDetailsAdapter(this);
        mygridview.setAdapter(mAdapter);
        getCP(mCpId);

        mygridview.setOnItemClickListener((parent, view, position, id) -> {
            Dialog dialog = new Dialog(this, R.style.Transparent);
            dialog.setCanceledOnTouchOutside(true);
            dialog.setCancelable(true);
            Window window = dialog.getWindow();
            window.setGravity(Gravity.CENTER);
            window.setWindowAnimations(R.style.pop_anim);
            View view1 = View.inflate(this, R.layout.vip_center_window, null);
            nameText = view1.findViewById(R.id.popu_text);
            imageView = view1.findViewById(R.id.popu_image);
            titText = view1.findViewById(R.id.ohuo);
            window.setContentView(view1);
            window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);//设置横向全屏
            dialog.show();

            nameText.setText(mAdapter.getList_adapter().get(position).getName() + "(" + "LV" + mAdapter.getList_adapter().get(position).getLevel() + "开启" + ")");
            titText.setText(mAdapter.getList_adapter().get(position).getTitle());
            GlideArms.with(this)
                    .load(mAdapter.getList_adapter().get(position).getImg_1())
                    .placeholder(R.mipmap.no_tou)
                    .error(R.mipmap.no_tou)
                    .circleCrop()
                    .into(imageView);

        });
    }

    @OnClick(R.id.cp_relieve)
    public void onClick() {
        if (MyUtil.isFastClick()) {
            PuTongWindow puTongWindow = new PuTongWindow(this);
            puTongWindow.showAtLocation(mygridview, Gravity.CENTER, 0, 0);
            puTongWindow.getTitText().setText("确定要解除CP吗？\n(解除后相关数据都将不见了哦！)");
            puTongWindow.getCancel().setOnClickListener(v -> {
                puTongWindow.dismiss();
            });
            puTongWindow.getSure().setOnClickListener(v -> {
                puTongWindow.dismiss();
                removeCP(mCpId);
            });
        }
    }

    //获取CP详情
    private void getCP(String cpId) {
        RxUtils.loading(commonModel.cp_desc(cpId), this)
                .subscribe(new ErrorHandleSubscriber<CPDetailsBean>(mErrorHandler) {
                    @Override
                    public void onNext(CPDetailsBean cpDetailsBean) {
                        //CP守护时间
                        cpTime.setText("守护" + cpDetailsBean.getData().getCp().getDays() + "天");
                        //守护等级
                        cpRank.setText("LV" + cpDetailsBean.getData().getCp().getCp_level());
                        //第一个人的昵称
                        cpOneName.setText(cpDetailsBean.getData().getCp().getUser_nick());
                        //第一个人的头像
                        GlideArms.with(CPActivity.this)
                                .load(cpDetailsBean.getData().getCp().getUser_head())
                                .placeholder(R.mipmap.gender_zhuce_girl)
                                .error(R.mipmap.gender_zhuce_girl)
                                .circleCrop()
                                .into(cpOneHead);
                        //第二个人的昵称
                        cpTwoName.setText(cpDetailsBean.getData().getCp().getFrom_nick());
                        //第二个人的头像
                        GlideArms.with(CPActivity.this)
                                .load(cpDetailsBean.getData().getCp().getFrom_head())
                                .placeholder(R.mipmap.gender_zhuce_girl)
                                .error(R.mipmap.gender_zhuce_girl)
                                .circleCrop()
                                .into(cpTwoHead);

                        //两个闪电之间的图片
                        loadImage(cpBag, cpDetailsBean.getData().getCp().getBs_img(), 0);
                        xingruiNowImage.setText("LV" + cpDetailsBean.getData().getCp().getCp_level());
                        xingruiNextImage.setText("LV" + cpDetailsBean.getData().getCp().getNext_cp_level());
                        dengji.setText("守护值" + cpDetailsBean.getData().getCp().getExp() + "/" + cpDetailsBean.getData().getCp().getNext_cp_num());
                        progressBar2.setMax(cpDetailsBean.getData().getCp().getNext_cp_num());
                        progressBar2.setProgress(cpDetailsBean.getData().getCp().getExp());


                        //设置守护特权的数据源
                        mAdapter.getList_adapter().addAll(cpDetailsBean.getData().getAuth());
                        mAdapter.notifyDataSetChanged();
                    }
                });
    }

    //解除CP关系
    private void removeCP(String cpId) {
        RxUtils.loading(commonModel.remove_cp(cpId), this)
                .subscribe(new ErrorHandleSubscriber<CommentBean>(mErrorHandler) {
                    @Override
                    public void onNext(CommentBean commentBean) {
                        toast(commentBean.getMessage());
                        if (commentBean.getCode() == 1) {
                            EventBus.getDefault().post(new FirstEvent(type, JIECHUCP));
                            finish();
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        toast(t.getMessage());
                    }
                });
    }
}
