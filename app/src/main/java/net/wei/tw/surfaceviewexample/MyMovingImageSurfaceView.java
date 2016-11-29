package net.wei.tw.surfaceviewexample;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by henry on 2016/11/25.
 */

public class MyMovingImageSurfaceView extends SurfaceView implements SurfaceHolder.Callback{

    private mMovingThread mThread;
    private Bitmap mBitmap;             // 傳入的圖片
    private SurfaceHolder mSurfaceHolder;
    private Paint mPaint;
    private int CurrentX = 0;         // 定義好座標X
    private int CurrentY = 0;         // 定義好座標Y
    int destinationX = 0;
    int destinationY = 0;
    int diffX = destinationX - CurrentX;
    int diffY = destinationY - CurrentY;
    int incrementX = -1;
    int incrementY = -1;

    public MyMovingImageSurfaceView(Context context) {
        super(context);
    }

    public MyMovingImageSurfaceView(Context context, int intResID) {
        super(context);
        // 呼叫getHolder()取得SurfaceHolder物件
        mSurfaceHolder = getHolder();
        // 傳入實作界面SurfaceHolder.Callback
        mSurfaceHolder.addCallback(this);
        // 建構畫筆物件
        mPaint = new Paint();

        // 將傳入的圖片檔為Bitmap物件
        mBitmap = BitmapFactory.decodeResource(getResources(), intResID);
    }

    // Constructor
    public MyMovingImageSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyMovingImageSurfaceView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    protected void onDraw(Canvas canvas){
        try {
            canvas.drawColor(Color.WHITE);
        }catch (Exception e){
            e.printStackTrace();
        }
        super.onDraw(canvas);

        diffX = destinationX - CurrentX;
        diffY = destinationY - CurrentY;
                       
        if(diffX < 0){
            incrementX = 1;
        }else if(diffX > 0){
            incrementX = 0;
        }else{
            incrementX = -1;
        }

        if(diffY < 0){
            incrementY = 1;
        }else if(diffY > 0){
            incrementY = 0;
        }else{
            incrementY = -1;
        }


        if(diffX != 0){
            if(incrementX == 0){
                CurrentX++;
            }else if(incrementX == 1){
                CurrentX--;
            }
        }

        if(diffY != 0){
            if(incrementY == 0){
                CurrentY++;
            }else if(incrementY == 1){
                CurrentY--;
            }
        }

        try{
            // 將圖片繪製在SurfaceView上
            canvas.drawBitmap(mBitmap, CurrentX, CurrentY, mPaint);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            // 將觸控點座標設定為欲移動的目的座標
            destinationX = (int) event.getX();
            destinationY = (int) event.getY();
            // 計算現有座標與目的地座標的差異位移
            diffX = destinationX - CurrentX;
            diffY = destinationY - CurrentY;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        mThread = new mMovingThread(surfaceHolder, this);
        mThread.setFlag(true);
        mThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }
}
