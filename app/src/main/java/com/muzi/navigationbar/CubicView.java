package com.muzi.navigationbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;

/**
 * 作者: lipeng
 * 时间: 2019/6/17
 * 邮箱: lipeng@moyi365.com
 * 功能:
 */
public class CubicView extends View {

    private Paint paint;
    private Path path;
    private float height, width;
    private float strokeWidth = 5;

    private NavigationBarCubic navigationBarCubic;

    public CubicView(Context context) {
        super(context);
        init();
    }

    public CubicView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CubicView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        height = 80;
        width = 80;
        setMeasuredDimension((int) width * 2, (int) height);
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(strokeWidth);
        paint.setStyle(Paint.Style.STROKE);
        path = new Path();

        navigationBarCubic = new DefaultNavigationBarCubic();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        calPath();

        canvas.save();

        canvas.drawCircle(point1.x, point1.y, strokeWidth, paint);
        canvas.drawCircle(point2.x, point2.y, strokeWidth, paint);
        canvas.drawCircle(point3.x, point3.y, strokeWidth, paint);

        canvas.translate(point3.x, point3.y);

        canvas.drawCircle(point5.x, point5.y, strokeWidth, paint);
        canvas.drawCircle(point6.x, point6.y, strokeWidth, paint);
        canvas.drawCircle(point7.x, point7.y, strokeWidth, paint);

        canvas.restore();

        canvas.drawPath(path, paint);
    }

    private PointF point1;
    private PointF point2;
    private PointF point3;

    private PointF point5;
    private PointF point6;
    private PointF point7;

    private void calPath() {
        path.reset();

        point1 = navigationBarCubic.leftPoint1(width, height);
        point2 = navigationBarCubic.leftPoint2(width, height);
        point3 = navigationBarCubic.leftPoint3(width, height);

        path.rCubicTo(point1.x, point1.y,
                point2.x, point2.y,
                point3.x, point3.y);

        point5 = navigationBarCubic.rightPoint1(width, height);
        point6 = navigationBarCubic.rightPoint2(width, height);
        point7 = navigationBarCubic.rightPoint3(width, height);

        path.rCubicTo(point5.x, point5.y,
                point6.x, point6.y,
                point7.x, point7.y);
    }

}
