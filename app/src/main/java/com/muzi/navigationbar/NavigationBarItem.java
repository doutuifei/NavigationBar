package com.muzi.navigationbar;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;


/**
 * 作者: lipeng
 * 时间: 2019/6/14
 * 邮箱: lipeng@moyi365.com
 * 功能:
 */
public class NavigationBarItem extends android.support.v7.widget.AppCompatImageView implements NavigationBarStateChange {

    public NavigationBarItem(Context context) {
        super(context);
    }

    public NavigationBarItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NavigationBarItem(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public View getTargetView() {
        return this;
    }

    @Override
    public void onFall() {

    }

    @Override
    public void onTranslate() {

    }

    @Override
    public void onRise() {

    }

    @Override
    public void onSelect() {
        setImageResource(R.drawable.ic_shop);
    }

    @Override
    public void onUnSelect() {
        setImageResource(R.drawable.ic_shop_gray);
    }

}
