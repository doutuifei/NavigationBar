package com.muzi.navigationbar;

import android.graphics.PointF;

/**
 * 作者: lipeng
 * 时间: 2019/6/17
 * 邮箱: lipeng@moyi365.com
 * 功能:
 */
public interface NavigationBarCubic {

    /**
     * 圆半径
     *
     * @return
     */
    float getRadius(float width, float height);

    /**
     * 控制点1
     *
     * @param width
     * @param height
     * @return
     */
    PointF leftPoint1(float width, float height);

    /**
     * 控制点2
     *
     * @param width
     * @param height
     * @return
     */
    PointF leftPoint2(float width, float height);

    /**
     * 终点
     *
     * @param width
     * @param height
     * @return
     */
    PointF leftPoint3(float width, float height);

    /**
     * 控制点1
     *
     * @param width
     * @param height
     * @return
     */
    PointF rightPoint1(float width, float height);

    /**
     * 控制点2
     *
     * @param width
     * @param height
     * @return
     */
    PointF rightPoint2(float width, float height);

    /**
     * 终点
     *
     * @param width
     * @param height
     * @return
     */
    PointF rightPoint3(float width, float height);

}
