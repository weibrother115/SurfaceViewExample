package net.wei.tw.surfaceviewexample;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by henry on 2016/11/25.
 */

public class mMovingThread extends Thread{
    private boolean flag;
    private SurfaceHolder mHolder;
    private MyMovingImageSurfaceView mSurfaceView;

    // 建構子，將SurfaceHolder與SurfaceView傳入
    public mMovingThread(SurfaceHolder holder, MyMovingImageSurfaceView sv){
        mHolder = holder;
        mSurfaceView = sv;
    }

    public void setFlag(boolean mFlag){
        flag = mFlag;
    }

    public void run(){
        Canvas canvas = null;
        while(flag){
            try{
                canvas = mHolder.lockCanvas(null); // 不斷更新SurfaceView繪圖的位置
                mSurfaceView.onDraw(canvas);
            }finally {
                if(canvas != null){
                    mHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }
}
