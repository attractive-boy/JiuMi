package com.miniapp.talks.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.miniapp.talks.R;
import com.miniapp.talks.base.MyBaseAdapter;
import com.miniapp.talks.bean.CategorRoomBean;
import com.miniapp.talks.bean.GoodsList;
import com.miniapp.talks.bean.StartLoftBean;

/**
 * 充值
 */
@ActivityScope
public class ChargeAdapter extends MyBaseAdapter<GoodsList.DataBean.GoodsBean> {

    private Context context;
    public ChargeAdapter(Context context) {
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder VH;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_chager, null);
            VH = new ViewHolder(convertView);
            convertView.setTag(VH);
        } else {
            VH = (ViewHolder) convertView.getTag();
        }
        VH.textNum1.setText(list_adapter.get(position).getMizuan() + "");
        VH.textNum2.setText(list_adapter.get(position).getGive() + "米钻");
        VH.textNum3.setText(list_adapter.get(position).getPrice() + "元");
        VH.textRight.setText("返" + list_adapter.get(position).getRatio() + "%");

        if(list_adapter.get(position).isSelect){
            VH.rlBg.setSelected(true);
            VH.textNum1.setSelected(true);
            VH.textNum2.setSelected(true);
            VH.textNum3.setSelected(true);
            VH.textJia.setSelected(true);
            VH.textRight.setSelected(true);
        }else {
            VH.rlBg.setSelected(false);
            VH.textNum1.setSelected(false);
            VH.textNum2.setSelected(false);
            VH.textNum3.setSelected(false);
            VH.textJia.setSelected(false);
            VH.textRight.setSelected(false);
        }
        if(list_adapter.get(position).getGive() == 0){
            VH.textJia.setText("米钻");
            VH.textNum2.setVisibility(View.GONE);
            VH.textRight.setVisibility(View.GONE);
        } else {
            VH.textJia.setText("米钻+");
            VH.textNum2.setVisibility(View.VISIBLE);
            VH.textRight.setVisibility(View.VISIBLE);
        }
        return convertView;
    }


    public static class ViewHolder {
        RelativeLayout rlBg;
        TextView textNum1,textNum2,textNum3,textJia,textRight;

        public ViewHolder(View convertView) {
            rlBg = (RelativeLayout) convertView.findViewById(R.id.rlBg);
            textNum1 = (TextView) convertView.findViewById(R.id.textNum1);
            textNum2 = (TextView) convertView.findViewById(R.id.textNum2);
            textNum3 = (TextView) convertView.findViewById(R.id.textNum3);
            textJia = (TextView) convertView.findViewById(R.id.textJia);
            textRight = (TextView) convertView.findViewById(R.id.textRight);
        }
    }

}