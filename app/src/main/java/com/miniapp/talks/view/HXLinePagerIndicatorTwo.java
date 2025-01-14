package com.miniapp.talks.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;

public class HXLinePagerIndicatorTwo extends LinePagerIndicatorEx {
    public HXLinePagerIndicatorTwo(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        LinearGradient lg = new LinearGradient(getLineRect().left, getLineRect().top, getLineRect().right, getLineRect().bottom, new int[]{0xFFFFFFFF,0xFFFFFFFF}, null, LinearGradient.TileMode.CLAMP);
        getPaint().setShader(lg);
        canvas.drawRoundRect(getLineRect(), getRoundRadius(), getRoundRadius(), getPaint());
    }
}
