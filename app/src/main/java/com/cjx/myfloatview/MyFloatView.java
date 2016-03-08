package com.cjx.myfloatview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Objects;

import view.cjx.com.floatview.MainActivity;

/**
 * Created by bear on 16/3/3.
 */
public class MyFloatView extends View {
    /**
     * 上下文
     */
    private MainActivity con;
    /**
     * 浮动view的属性类
     */
    private FloatViewAttr attr = new FloatViewAttr();

    private WindowManager mWindowManager;
    /**
     * touchView布局
     */
    private View mView;
    /**
     * 浮动view
     */
    public ImageView mImageView;
    WindowManager.LayoutParams wmParams;
    Handler h=new Handler();
    String flag="";
    HashMap<String,Object>obj=new HashMap<String,Object>();

    public MyFloatView(MainActivity context, FloatViewAttr Attr, String flag) {
        super(context);
        this.con = context;
        this.attr = Attr;
        this.flag=flag;
        iniFloatView();
    }

    public void iniFloatView() {
        mWindowManager = (WindowManager) con.getSystemService(Context.WINDOW_SERVICE);
        mView = LayoutInflater.from(con).inflate(attr.viewLayoutID, null);
        mImageView = (ImageView) mView.findViewById(attr.viewID);
        mImageView.setBackgroundColor(Color.TRANSPARENT);
        mImageView.setOnTouchListener(mTouchListener);

        wmParams = new WindowManager.LayoutParams(0, 40);
        wmParams.width = 100;
        wmParams.height = 100;
        wmParams.x = (int) attr.iniX;//实际对应的坐标x＝屏幕宽/2+attr.iniX
        wmParams.y = (int) attr.iniY;//实际对应的坐标y＝屏幕高/2+attr.iniY
        mWindowManager.addView(mView, wmParams);
        obj.put("message","你点击了浮动按钮");
    }

    private OnTouchListener mTouchListener = new OnTouchListener() {
        float lastX, lastY;
        int paramsX, paramsY;

        @Override
        public boolean onTouch(View v, MotionEvent event) {

            final int action = event.getAction();
            float x = event.getRawX();
            float y = event.getRawY();
            System.out.println("x=" + x + ",y=" + y);
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    motionActionDownEvent(x, y);
                    break;

                case MotionEvent.ACTION_MOVE:
                    motionActionMoveEvent(x, y);
                    break;

                case MotionEvent.ACTION_UP:
                    motionActionUpEvent(x, y);
                    break;

                default:
                    break;


            }
            return true;
        }

        private void motionActionDownEvent(float x, float y) {
            lastX = x;
            lastY = y;
            paramsX = wmParams.x;
            paramsY = wmParams.y;
        }

        private void motionActionMoveEvent(float x, float y) {
            int dx = (int) (x - lastX);
            int dy = (int) (y - lastY);
            wmParams.x = paramsX + dx;
            wmParams.y = paramsY + dy;

            // 更新悬浮窗位置
            mWindowManager.updateViewLayout(mView, wmParams);
        }

        private void motionActionUpEvent(float x, float y) {
            int dx = (int) (x - lastX);
            int dy = (int) (y - lastY);
            if (dx == 0 && dy == 0) {
                //点击事件
               h.post(new Runnable() {
                   @Override
                   public void run() {
                    con.floatAction(flag,obj);
                   }
               });
            }
        }
    };

}