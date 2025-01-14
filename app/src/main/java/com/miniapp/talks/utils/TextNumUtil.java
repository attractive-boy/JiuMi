package com.miniapp.talks.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.jess.arms.utils.LogUtils;
import com.miniapp.talks.R;

import java.io.File;

public class TextNumUtil {
    public static void initTextNum(Context context,EditText editText, TextView textView) {


        editText.addTextChangedListener(new TextWatcher() {

            private CharSequence temp;
            private int selectionStart;
            private int selectionEnd;
            private int num;


            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                temp = charSequence;

            }

            @SuppressLint("ResourceAsColor")
            @Override
            public void afterTextChanged(Editable editable) {

                textView.setText(editable.length() + "");

                LogUtils.debugInfo("====EditText.getText.toString", editable.toString());
                if (temp.length() > editable.length()) {
                    textView.setText(editable.length() + "");

                    selectionStart = editText.getSelectionStart();
                    selectionEnd = editText.getSelectionEnd();
                    editText.setSelection(selectionEnd);//设置光标在最后

                }
                if (editable.length()==500){
                    textView.setTextColor(context.getResources().getColor(R.color.countcolor));
                }else {
                    textView.setTextColor(context.getResources().getColor(R.color.textcolor));
                }
            }
        });
    }
}
