package com.miniapp.talks.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;

public class HXLinePagerIndicatorThree extends LinePagerIndicatorEx {
    public HXLinePagerIndicatorThree(Context context) {
        super(context);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        LinearGradient lg = new LinearGradient(getLineRect().left, getLineRect().top, getLineRect().right, getLineRect().bottom, new int[]{0xFFFFDC17,0xFFFFDC17}, null, LinearGradient.TileMode.CLAMP);
        getPaint().setShader(lg);
        canvas.drawRoundRect(getLineRect(), getRoundRadius(), getRoundRadius(), getPaint());
    }
}
