package com.miniapp.talks.adapter;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.di.scope.ActivityScope;
import com.miniapp.talks.R;
import com.miniapp.talks.bean.ReportBean;

import java.util.ArrayList;

/**
 * 举报
 */
@ActivityScope
public class ReportMessageAdapter extends BaseQuickAdapter<ReportBean.DataBean, BaseViewHolder> {


    public ReportMessageAdapter() {
        super(R.layout.item_report_message, new ArrayList<>());
    }

    @Override
    protected void convert(BaseViewHolder helper, ReportBean.DataBean item) {

        helper.setText(R.id.textName, item.getName());

        TextView textName = helper.getView(R.id.textName);
        if(item.isSelct){
            textName.setSelected(true);
        }else {
            textName.setSelected(false);
        }
    }
}
