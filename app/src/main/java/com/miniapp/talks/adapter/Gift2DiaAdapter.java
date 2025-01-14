package com.miniapp.talks.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.miniapp.talks.R;
import com.miniapp.talks.base.MyBaseAdapter;

public class Gift2DiaAdapter extends MyBaseAdapter<String> {
    private Context context;
    //    final int TYPE_1 = 0;
//    final int TYPE_2 = 1;
    private LayoutInflater inflater;
    private LinearLayout linearLayout = null;
//    private MoneyValueFilter mMvf = new MoneyValueFilter();

    public Gift2DiaAdapter(Context context) {
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder VH = null;
//        ViewHolderTwo VHT = null;
//        int type = getItemViewType(position);
//        mMvf.setMaxValue(9999.00);
        if (convertView == null) {
            inflater = LayoutInflater.from(context);
//            switch (type) {
//                case TYPE_1:
//                    convertView = inflater.inflate(R.layout.gift2_number_popu, parent, false);
//                    VHT = new ViewHolderTwo(convertView);
//                    convertView.setTag(VHT);
//                    break;
//                case TYPE_2:
            convertView = inflater.inflate(R.layout.item_room_dialog_gift, parent, false);
            VH = new ViewHolder(convertView);
            convertView.setTag(VH);
//                    break;
//                default:
//                    break;
//            }
        } else {
//            switch (type) {
//                case TYPE_1:
//                    VHT = (ViewHolderTwo) convertView.getTag();
//                    break;
//                case TYPE_2:
            VH = (ViewHolder) convertView.getTag();
//                    break;
//            }
        }

        // 设置资源
//        switch (type) {
//            case TYPE_1:
//                VHT.editText.setFilters(new InputFilter[]{mMvf});
//                VHT.editText.addTextChangedListener(new TextWatcher() {
//                    @Override
//                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//                    }
//
//                    @Override
//                    public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//                    }
//
//                    @Override
//                    public void afterTextChanged(Editable s) {
//                        if (onOneClick != null) {
//                            onOneClick.oneClick(s.toString());
//                        }
//                    }
//                });
//                break;
//            case TYPE_2:
        VH.text1.setText(list_adapter.get(position));
//                break;
//        }
        return convertView;
    }

    // 每个convert view都会调用此方法，获得当前所需要的view样式
//    @Override
//    public int getItemViewType(int position) {
//        int p = position;
//        if (p == 0) {
//            return TYPE_1;
//        } else {
//            return TYPE_2;
//        }
//    }

//    @Override
//    public int getViewTypeCount() {
//        return 2;
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return 0;
//    }

    public static class ViewHolder {
        TextView text1;

        public ViewHolder(View convertView) {
            text1 = (TextView) convertView.findViewById(R.id.text1);
        }
    }

//    public static class ViewHolderTwo {
//        EditText editText;
//
//        public ViewHolderTwo(View convertView) {
//            editText = convertView.findViewById(R.id.gift2_number);
//        }
//    }

    public void setOnOneClick(OnOneClick onOneClick) {
        this.onOneClick = onOneClick;
    }

    public interface OnOneClick {
        void oneClick(String string);
    }

    OnOneClick onOneClick;
}
