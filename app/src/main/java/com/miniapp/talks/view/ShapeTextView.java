package com.miniapp.talks.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

import com.miniapp.talks.R;


/**
 * Created by whl on 2017/4/28.
 */

public class ShapeTextView extends TextView {
    private final int SHAPE_RECTANGEL = 0;
    private final int SHAPE_OVAL = 1;

    private int shape;
    private int solidNormalColor;
    private int solidPressedColor;
    private float cornersRadius;
    private float cornersTopLeft;
    private float cornersTopRight;
    private float cornersBottomLeft;
    private float cornersBottomRight;

    //    渐变颜色属性
    private int gradientNormalStartColor;
    private int gradientNormalCenterColor;
    private int gradientNormalEndColor;

    private int gradientPressedStartColor;
    private int gradientPressedCenterColor;
    private int gradientPressedEndColor;

    private int gradientOrientation;

    private float strokeWidth;
    private int strokeColor;

    private int defaultColor = Color.parseColor("#00000000");
    private GradientDrawable.Orientation[] orientations;

    public ShapeTextView(Context context) {
        this(context, null);
    }

    public ShapeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ShapeTextView);
        shape = array.getInteger(R.styleable.ShapeTextView_shape, SHAPE_RECTANGEL);


        solidNormalColor = array.getColor(R.styleable.ShapeTextView_solidNormal, defaultColor);
        solidPressedColor = array.getColor(R.styleable.ShapeTextView_solidPressed, defaultColor);


        cornersRadius = array.getDimension(R.styleable.ShapeTextView_cornersRadius, 0);

        cornersTopLeft = array.getDimension(R.styleable.ShapeTextView_cornerTopLeft, 0);
        cornersTopRight = array.getDimension(R.styleable.ShapeTextView_cornerTopRight, 0);
        cornersBottomLeft = array.getDimension(R.styleable.ShapeTextView_cornerBottomLeft, 0);
        cornersBottomRight = array.getDimension(R.styleable.ShapeTextView_cornerBottomRight, 0);

        strokeWidth = array.getDimension(R.styleable.ShapeTextView_strokeWidth, 0);

        strokeColor = array.getColor(R.styleable.ShapeTextView_strokeColor, defaultColor);

        gradientNormalStartColor = array.getColor(R.styleable.ShapeTextView_gradientNormalStartColor, defaultColor);

        gradientNormalCenterColor = array.getColor(R.styleable.ShapeTextView_gradientNormalCenterColor, defaultColor);

        gradientNormalEndColor = array.getColor(R.styleable.ShapeTextView_gradientNormalEndColor, defaultColor);


        gradientPressedStartColor = array.getColor(R.styleable.ShapeTextView_gradientPressedStartColor, defaultColor);
        gradientPressedCenterColor = array.getColor(R.styleable.ShapeTextView_gradientPressedCenterColor, defaultColor);
        gradientPressedEndColor = array.getColor(R.styleable.ShapeTextView_gradientPressedEndColor, defaultColor);


        TypedArray orientationArray = context.obtainStyledAttributes(attrs, R.styleable.ShapeTextView);

        gradientOrientation = orientationArray.getInteger(R.styleable.ShapeTextView_gradientOrientation, 6);

        array.recycle();


        orientations = new GradientDrawable.Orientation[]{
                GradientDrawable.Orientation.TOP_BOTTOM,
                GradientDrawable.Orientation.TR_BL,
                GradientDrawable.Orientation.RIGHT_LEFT,
                GradientDrawable.Orientation.BR_TL,
                GradientDrawable.Orientation.BOTTOM_TOP,
                GradientDrawable.Orientation.BL_TR,
                GradientDrawable.Orientation.LEFT_RIGHT,
                GradientDrawable.Orientation.TL_BR
        };
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setShape();
    }

    private void setShape() {
        setGravity(Gravity.CENTER);
        setClickable(true);
        // normal state
        GradientDrawable drawableNormal = new GradientDrawable();
        // 设置Shape
        drawableNormal.setShape(shape);
        // 设置圆角半径
        drawableNormal.setCornerRadius(cornersRadius);
        // 圆角半径(每个圆角半径的值)
        if (cornersRadius == 0) {
            drawableNormal.setCornerRadii(new float[]{
                    cornersTopLeft, cornersTopLeft,
                    cornersTopRight, cornersTopRight,
                    cornersBottomRight, cornersBottomRight,
                    cornersBottomLeft, cornersBottomLeft});
        }
        //描边的宽度和颜色
        drawableNormal.setStroke((int) strokeWidth, strokeColor);
        //设置填充色
        if (solidNormalColor != defaultColor) {
            drawableNormal.setColor(solidNormalColor);
        } else {
//            设置渐变色
            int[] gradientColors;
            if (gradientNormalStartColor != defaultColor && gradientNormalEndColor != defaultColor) {
                gradientColors = new int[]{gradientNormalStartColor, gradientNormalEndColor};
                if (gradientNormalCenterColor != defaultColor) {
                    gradientColors = new int[]{gradientNormalStartColor, gradientNormalCenterColor, gradientNormalEndColor};
                }
                drawableNormal.setColors(gradientColors);


                drawableNormal.setOrientation(orientations[gradientOrientation]);
            } else {
                drawableNormal.setColor(solidNormalColor);
            }

        }


        // pressed state
        GradientDrawable drawablePressed = new GradientDrawable();
        drawablePressed.setShape(shape);
        drawablePressed.setCornerRadius(cornersRadius);
        if (cornersRadius == 0) {
            drawablePressed.setCornerRadii(new float[]{
                    cornersTopLeft, cornersTopLeft,
                    cornersTopRight, cornersTopRight,
                    cornersBottomRight, cornersBottomRight,
                    cornersBottomLeft, cornersBottomLeft});
        }

        drawablePressed.setStroke((int) strokeWidth, strokeColor);

        drawablePressed.setColor(solidPressedColor);

        // 设置背景选择器
        StateListDrawable stateListDrawable = new StateListDrawable();

        if (solidPressedColor != defaultColor) {
            stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, drawablePressed);
        }
        int[] gradientPressdColors;
        if (gradientPressedStartColor != defaultColor && gradientPressedEndColor != defaultColor) {
            gradientPressdColors = new int[]{gradientPressedStartColor, gradientPressedEndColor};
            if (gradientPressedCenterColor != defaultColor) {
                gradientPressdColors = new int[]{gradientPressedStartColor, gradientPressedCenterColor, gradientPressedEndColor};
            }

            drawablePressed.setColors(gradientPressdColors);
            drawablePressed.setOrientation(orientations[gradientOrientation]);
            stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, drawablePressed);

        }

        if (isEnabled()) {
            setAlpha(1.0f);
        } else {
            setAlpha(0.7f);
        }
        stateListDrawable.addState(new int[]{}, drawableNormal);

        setBackground(stateListDrawable);

    }
}