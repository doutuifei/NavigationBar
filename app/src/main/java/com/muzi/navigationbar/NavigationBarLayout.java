package com.muzi.navigationbar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * 作者: lipeng
 * 时间: 2019/6/13
 * 邮箱: lipeng@moyi365.com
 * 功能:
 */
public class NavigationBarLayout extends ViewGroup {

    private Paint paint;
    private Path path;

    private float width;
    private float singleWidth; //单个导航项宽度
    private float height;
    private float singleHeight;
    private float arcRadius;//圆弧半径
    private float restSpace;//单个导航项减去圆弧直径后单边剩余宽度
    private float circleOffsetY;

    private int navCount = 5;
    private float moveTween = 0f;//移动补间
    private float padding; //间隙

    private boolean start = false;

    private long durationFall = 100;
    private long durationRise = 100;
    private long durationTranslation = 500;

    private List<Integer> iconList = new ArrayList<>();
    private List<Integer> iconGrayList = new ArrayList<>();


    public NavigationBarLayout(Context context) {
        super(context);
        init();
    }

    public NavigationBarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NavigationBarLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.FILL);
        path = new Path();

        iconList.add(R.drawable.ic_home);
        iconList.add(R.drawable.ic_shop);
        iconList.add(R.drawable.ic_search);
        iconList.add(R.drawable.ic_cart);
        iconList.add(R.drawable.ic_me);
        iconList.add(R.drawable.ic_me);
        iconList.add(R.drawable.ic_me);

        iconGrayList.add(R.drawable.ic_home_gray);
        iconGrayList.add(R.drawable.ic_shop_gray);
        iconGrayList.add(R.drawable.ic_search_gray);
        iconGrayList.add(R.drawable.ic_cart_gray);
        iconGrayList.add(R.drawable.ic_me_gray);
        iconGrayList.add(R.drawable.ic_me_gray);
        iconGrayList.add(R.drawable.ic_me_gray);

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                randomInt();
            }
        });
        initChilds();
    }

    private void initChilds() {
        int childCount = getChildCount();
        Log.d("NavigationBarLayout", "childCount:" + childCount);
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            boolean b = childView instanceof NavigationBarStateChange;
            Log.d("NavigationBarLayout", "b:" + b);
        }
    }

    private void randomInt() {
        Random random = new Random();
        int next = random.nextInt(navCount);
        if (next == moveTween) {
            randomInt();
            return;
        } else {
            initAnimator(next);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);

        singleWidth = width / navCount;
        singleHeight = height / 2f;
        arcRadius = singleHeight * 2 / 3;
        restSpace = (singleWidth - arcRadius * 2) / 2;
        initChilds();
        setMeasuredDimension((int) width, (int) height);
    }

    private void initPath() {

        path.reset();
        path.rMoveTo(moveTween * singleWidth, singleHeight);

        path.rCubicTo(restSpace + padding, 0,
                restSpace + padding / 2, arcRadius,
                singleWidth / 2, arcRadius);

        path.rCubicTo(arcRadius, 0,
                arcRadius - padding, -arcRadius,
                restSpace + arcRadius, -arcRadius);

        path.rLineTo(width - (moveTween + 1) * singleWidth, 0);

        path.rLineTo(0, singleHeight);

        path.rLineTo(-width, 0);

        path.rLineTo(0, -singleHeight);

        path.close();

    }


    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        _draw(canvas);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    private void _draw(Canvas canvas) {
        initPath();

        canvas.drawCircle(singleWidth * (moveTween + 0.5f), singleHeight + circleOffsetY, singleHeight / 2, paint);

        canvas.drawPath(path, paint);

//        drawLine(canvas);

        for (int i = 0; i < navCount; i++) {
            drawIcon(canvas, i);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


    }

    private void drawLine(Canvas canvas) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        for (int i = 1; i < navCount; i++) {
            float startX = i * singleWidth;
            float startY = singleHeight;
            float stopX = i * singleWidth;
            float stopY = height;
            canvas.drawLine(startX, startY, stopX, stopY, paint);
        }
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
    }

    private void drawIcon(Canvas canvas, int index) {
        int currentIndex = (int) moveTween;
        Bitmap bitmap;
        int bitmapWidth;
        int bitmapHeight;
        float left;
        float top;
        if (currentIndex == index) {
            bitmap = BitmapFactory.decodeResource(getResources(), iconList.get(index));
            bitmapWidth = bitmap.getWidth();
            bitmapHeight = bitmap.getHeight();
            left = singleWidth * (moveTween + 0.5f) - bitmapWidth * 0.5f;
            top = (int) (singleHeight + circleOffsetY - bitmapHeight * 0.5f);
        } else {
            bitmap = BitmapFactory.decodeResource(getResources(), iconGrayList.get(index));
            bitmapWidth = bitmap.getWidth();
            bitmapHeight = bitmap.getHeight();
            left = singleWidth * (index + 0.5f) - bitmapWidth * 0.5f;
            top = height - singleHeight * 0.5f - bitmapHeight * 0.5f;
        }
        if (bitmap != null) {
            canvas.drawBitmap(bitmap, left, top, paint);
        }
        bitmap.isRecycled();
    }

    private void initAnimator(final int next) {
        if (start) {
            return;
        }

        AnimatorSet animatorSet = new AnimatorSet();
        final ValueAnimator fallAnimator = ValueAnimator.ofFloat(0f, singleWidth);
        fallAnimator.setDuration(durationFall);
        fallAnimator.setInterpolator(new AccelerateInterpolator());
        fallAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                circleOffsetY = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        fallAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                fallAnimator.removeAllListeners();
            }
        });
        final ValueAnimator translationAnimator = ValueAnimator.ofFloat(moveTween, next * 1f);
        translationAnimator.setDuration(durationTranslation);
        translationAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        translationAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                moveTween = value;
                invalidate();
            }
        });
        translationAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                translationAnimator.removeAllListeners();
            }
        });

        final ValueAnimator riseAnimator = ValueAnimator.ofFloat(singleWidth, 0f);
        riseAnimator.setDuration(durationRise);
        riseAnimator.setInterpolator(new DecelerateInterpolator());
        riseAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                circleOffsetY = (float) animation.getAnimatedValue();
                invalidate();
            }
        });

        animatorSet.playSequentially(fallAnimator, translationAnimator, riseAnimator);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                start = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                start = false;
            }
        });
        animatorSet.start();
    }

}