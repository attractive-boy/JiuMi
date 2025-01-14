package com.miniapp.talks.view.voiceripple;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;

import com.miniapp.talks.R;


public class PaintHelper {

    public static Paint getArcPaint(Context context,int color) {
        Paint paint = new Paint();
        paint.setColor(ContextCompat.getColor(context, R.color.bulue_46A6FD));
        paint.setStrokeWidth(20);
        paint.setAntiAlias(true);
        paint.setStrokeCap(Paint.Cap.SQUARE);
        paint.setStyle(Paint.Style.STROKE);
        return paint;
    }
    public static Paint getDefaultRipplePaint(Context context,int color) {
        Paint ripplePaint = new Paint();
        ripplePaint.setStyle(Paint.Style.FILL);
        ripplePaint.setColor(ContextCompat.getColor(context, color));
        ripplePaint.setAntiAlias(true);

        return ripplePaint;
    }

    public static  Paint getDefaultRippleBackgroundPaint(Context context,int color) {
        Paint rippleBackgroundPaint = new Paint();
        rippleBackgroundPaint.setStyle(Paint.Style.FILL);
        rippleBackgroundPaint.setColor((ContextCompat.getColor(context, color) & 0x00FFFFFF) | 0x40000000);
        rippleBackgroundPaint.setAntiAlias(true);

        return rippleBackgroundPaint;
    }

    public static  Paint getButtonPaint() {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        return paint;
    }
}
