package com.miniapp.talks.Interface;

import com.miniapp.talks.bean.MyPackBean;
import com.miniapp.talks.bean.Rank;

import java.util.List;

public class MyPackBaoShiInter {
    public interface onListener {
        void OnListener(MyPackBean.DataBean bean);
    }

    public interface onListenerTwo {
        void OnListener(MyPackBean.DataBean bean, int tag, int type);
    }

    public interface onRankInter {
        void OnRankInter(int distance);
    }

    public interface NewData{
        void NewData(List<Rank.DataBean.TopBean> topBean ,int sign);
    }
}
