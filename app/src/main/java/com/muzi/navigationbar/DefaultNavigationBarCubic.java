package com.muzi.navigationbar;

import android.graphics.PointF;

/**
 * 作者: lipeng
 * 时间: 2019/6/17
 * 邮箱: lipeng@moyi365.com
 * 功能:
 */
public class DefaultNavigationBarCubic implements NavigationBarCubic {

    @Override
    public float getRadius(float width, float height) {
        return (width + height) / 4f;
    }

    @Override
    public PointF leftPoint1(float width, float height) {
        return new PointF(width / 2, height / 9);
    }

    @Override
    public PointF leftPoint2(float width, float height) {
        return new PointF(width / 12, height / 10 * 9);
    }

    @Override
    public PointF leftPoint3(float width, float height) {
        return new PointF(width, height);
    }

    @Override
    public PointF rightPoint1(float width, float height) {
        PointF target = leftPoint2(width, height);
        return new PointF(width - target.x, target.y - height);
    }

    @Override
    public PointF rightPoint2(float width, float height) {
        PointF target = leftPoint1(width, height);
        return new PointF(width - target.x, target.y - height);
    }

    @Override
    public PointF rightPoint3(float width, float height) {
        PointF target = leftPoint3(width, height);
        return new PointF(target.x, -target.y);
    }

}
