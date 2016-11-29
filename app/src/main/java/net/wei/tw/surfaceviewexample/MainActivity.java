package net.wei.tw.surfaceviewexample;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

    public static boolean bIFDebug = false;
    public static String TAG = "Koala_DEBUG";
    public static String strDebugPrefix = "MainActivity";

    private LinearLayout mLayout;
    // 自訂MyMovingImageSurface物件
    private MyMovingImageSurfaceView mMyMovingImageSurfaceView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化mLayout物件
        mLayout = (LinearLayout)findViewById(R.id.myLinearLayout);

        // 建構MyMovingImageSurfaceView物件
        mMyMovingImageSurfaceView = new MyMovingImageSurfaceView(
                getApplicationContext(), R.drawable.koala_small
        );
        // 在Layout當中，放上mMySurfaceView
        mLayout.addView(mMyMovingImageSurfaceView);
    }
}
