package com.cjx.myfloatview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by bear on 16/3/3.
 */
public class MyFloatView extends View {
    /**
     * 上下文
     */
    private Activity con;
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
    private ImageView mImageView;
    WindowManager.LayoutParams wmParams;
    /**
     * 最大屏幕的坐标
     */
    private float lagestX, lagestY;

    public MyFloatView(Activity context, FloatViewAttr Attr) {
        super(context);
        this.con = context;
        this.attr = Attr;
        iniFloatView();
    }

    public void iniFloatView() {
        mWindowManager = (WindowManager) con.getSystemService(Context.WINDOW_SERVICE);
        lagestX = mWindowManager.getDefaultDisplay().getWidth();
        lagestY = mWindowManager.getDefaultDisplay().getHeight();
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
    }

    private OnTouchListener mTouchListener = new OnTouchListener() {
        float lastX, lastY;
        int paramsX, paramsY;
        int[] viewXandY = new int[2];

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            mView.getLocationInWindow(viewXandY);
            lagestX = viewXandY[0];
            lagestY = viewXandY[1];
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
                Toast.makeText(con, "你点击了我", Toast.LENGTH_SHORT).show();
            }
        }
    };


}