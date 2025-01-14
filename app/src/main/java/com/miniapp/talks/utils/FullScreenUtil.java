package com.miniapp.talks.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.miniapp.talks.R;
import com.miniapp.talks.adapter.ImgVPAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * 看大图
 */
public class FullScreenUtil {

    public static void showFullScreenDialog(Context context, final int pos, final List<String> imgList) {
        final Dialog dialog = new Dialog(context, R.style.big_pic_dialog);
        //设置是否允许Dialog可以被点击取消,也会阻止Back键
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        //获取Dialog窗体的根容器
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewGroup root = (ViewGroup) dialog.getWindow().getDecorView().findViewById(android.R.id.content);
        //设置窗口大小为屏幕大小
        WindowManager wm = (WindowManager) context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        Point screenSize = new Point();
        wm.getDefaultDisplay().getSize(screenSize);
        root.setLayoutParams(new LinearLayout.LayoutParams(screenSize.x, screenSize.y));
        //  获取自定义布局,并设置给Dialog
        View view = inflater.inflate(R.layout.pop_photo_vp, root, false);
        final ViewPager img_vp = view.findViewById(R.id.img_vp);
        final TextView img_num_iv = view.findViewById(R.id.img_num_iv);
        final ImageView img_down_iv = view.findViewById(R.id.img_down_iv);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        ImgVPAdapter vpAdapter = new ImgVPAdapter(context, imgList);
        img_vp.setAdapter(vpAdapter);
        img_vp.setCurrentItem(pos);
        img_num_iv.setText((pos + 1) + "/" + imgList.size());
        img_down_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //保存
               /* if (imgList.get(pos) != null) {
                    ImageSaveUtils.saveImg(XQDetailActivity.this, imgList.get(pos));
                }*/

            }
        });
        img_vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {
                img_num_iv.setText((position + 1) + "/" + imgList.size());
                img_down_iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                      /*  if(imgList.get(position)!=null){
                            ImageSaveUtils.saveImg(XQDetailActivity.this, imgList.get(position));
                        }
*/
                    }
                });
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        vpAdapter.setAllClickListener(new ImgVPAdapter.AllClickListener() {
            @Override
            public void allclick(int pos) {
                dialog.dismiss();
            }
        });

        dialog.setContentView(view);
        dialog.show();
    }

}
