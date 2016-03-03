package com.cjx.myfloatview;

import android.os.IBinder;

/**
 * Created by bear on 16/3/3.
 * 浮动view的属性
 */
public class FloatViewAttr {
    /**
     * 初始x坐标(以屏幕中心为基准)
     */
    public float iniX;
    /**
     * 初始Y坐标(以屏幕中心为基准)
     */
    public  float iniY;
    /**
     * touchView 布局
     */
    public int viewLayoutID;
    /**
     * touchView 空间ID
     */
    public int viewID;

    public IBinder token;


}
