package com.miniapp.talks.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;

public class HXLinePagerIndicator extends LinePagerIndicatorEx  {
    public HXLinePagerIndicator(Context context) {
        super(context);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        LinearGradient lg = new LinearGradient(getLineRect().left, getLineRect().top, getLineRect().right, getLineRect().bottom, new int[]{0xFF6A83FF, 0xFFFBA8DC,0xFFA4E8FE}, null, LinearGradient.TileMode.CLAMP);
        getPaint().setShader(lg);
        canvas.drawRoundRect(getLineRect(), getRoundRadius(), getRoundRadius(), getPaint());
    }
}
