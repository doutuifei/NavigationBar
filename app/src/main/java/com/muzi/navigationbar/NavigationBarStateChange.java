package com.muzi.navigationbar;

import android.view.View;

/**
 * 作者: lipeng
 * 时间: 2019/6/14
 * 邮箱: lipeng@moyi365.com
 * 功能:
 */
public interface NavigationBarStateChange {

    View getTargetView();

    void onFall();

    void onTranslate();

    void onRise();

    void onSelect();

    void onUnSelect();

}
