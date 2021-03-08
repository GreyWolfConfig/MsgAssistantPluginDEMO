package com.ma.pluginframework;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.Log;

public class PluginHelper {
    public static String SERVER_PN = "com.wolf.qupd";
    public static String SERVER_SVC_NAME = "com.wolf.qqts.MainService";
    public static final int PLUGIN_SDK_VERSION = 1;
    public static final long DEFAULT_ACTIVE_UIN = 0xffffffffffffl;
    public static final String TAG = "pluginframework_log";
    public static final String ACTION_WAKEUP = "com.ma.pluginframework.WAKEUP";
    public static boolean OPEN_LOG = false;
//    public static final boolean OPEN_LOG = BuildConfig.DEBUG;

    public static void log(String msg) {
        if (OPEN_LOG) {
            Log.i(TAG, msg);
        }
    }

    public static void log(String msg, Throwable e) {
        if (OPEN_LOG) {
            Log.i(TAG, msg + e);
            e.printStackTrace();
        }
    }

    /**
     * 文字转图片
     *
     * @param text
     * @param textSize
     * @return
     */
    public static Bitmap textAsBitmap(String text, float textSize) {

        TextPaint textPaint = new TextPaint();

//        textPaint.setARGB(0x31, 0x31, 0x31, 0);
        textPaint.setColor(Color.BLACK);

        textPaint.setTextSize(textSize);


        StaticLayout layout = new StaticLayout(text, textPaint, 450,
                Layout.Alignment.ALIGN_NORMAL, 1.3f, 0.0f, true);
        Bitmap bitmap = Bitmap.createBitmap(layout.getWidth() + 20,
                layout.getHeight() + 20, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.translate(10, 10);
        canvas.drawColor(Color.WHITE);

        layout.draw(canvas);
        Log.d("textAsBitmap",
                String.format("1:%d %d", layout.getWidth(), layout.getHeight()));
        return bitmap;
    }
}
