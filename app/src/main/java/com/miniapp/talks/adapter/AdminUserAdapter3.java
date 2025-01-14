package com.miniapp.talks.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.glide.GlideArms;
import com.miniapp.talks.R;
import com.miniapp.talks.activity.room.SetAdminActivity;
import com.miniapp.talks.base.MyBaseAdapter;
import com.miniapp.talks.bean.SearchAdmin;

/**
 * 管理员
 */
@ActivityScope
public class AdminUserAdapter3 extends MyBaseAdapter<SearchAdmin.DataBean> {

    private Context context;

    public AdminUserAdapter3(Context context) {
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder VH;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_home_admin2, null);
            VH = new ViewHolder(convertView);
            convertView.setTag(VH);
        } else {
            VH = (ViewHolder) convertView.getTag();
        }
        VH.tv_title.setText(list_adapter.get(position).getNickname());
        VH.tv_userid.setText("ID：" + list_adapter.get(position).getId());
        if (!TextUtils.isEmpty(list_adapter.get(position).getHeadimgurl())) {
            GlideArms.with(context)
                    .load(list_adapter.get(position).getHeadimgurl())
                    .placeholder(R.mipmap.no_tou)
                    .error(R.mipmap.no_tou)
                    .circleCrop()
                    .into(VH.ci_head);
        }
        int is_admin = list_adapter.get(position).getIs_admin();
        VH.textCount.setText(is_admin == 1 ? "删除管理员" : "设置管理员");
        VH.textCount.setOnClickListener(v -> {
            if(is_admin == 1){
                if(context instanceof SetAdminActivity){
                    ((SetAdminActivity) context).remove_admin(String.valueOf(list_adapter.get(position).getId()),2);
                }
            }else {
                if(context instanceof SetAdminActivity){
                    ((SetAdminActivity) context).is_admin(String.valueOf(list_adapter.get(position).getId()),2);
                }
            }

        });
        return convertView;
    }


    public static class ViewHolder {
        TextView tv_title, tv_userid,textCount;
        ImageView ci_head;

        public ViewHolder(View convertView) {
            tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            tv_userid = (TextView) convertView.findViewById(R.id.tv_userid);
            textCount = (TextView) convertView.findViewById(R.id.textCount);
            ci_head = convertView.findViewById(R.id.ci_head);
        }
    }

}