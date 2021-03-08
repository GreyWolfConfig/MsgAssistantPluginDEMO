package com.ma.pluginframework.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.ma.pluginframework.PluginAppInterface;
import com.ma.pluginframework.PluginHelper;

public abstract class WakeupService extends Service {

    private HostConnection hostConnection;

    /**
     * 启动
     *
     * @param context
     * @param serviceClazz
     */
    public static void startup(Context context, Class<? extends Service> serviceClazz) {
        Intent intent = new Intent(context, serviceClazz);
        intent.putExtra("ma_wakeup", "wakeup");
        intent.setAction(PluginHelper.ACTION_WAKEUP);
        context.startService(intent);
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        hostConnection = new HostConnection(this, getAppInterface());
        hostConnection.bindHost();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if ("wakeup".equals(intent.getStringExtra("ma_wakeup"))) {
            this.onWakeup();
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (hostConnection != null) hostConnection.unbind();
    }

    /**
     * 收到主程序的唤醒消息
     * 可能触发该方法的地方有:
     * 1.用户打开了该插件的开关
     * 2.用户在主程序内启动了该软件
     * 3.插件后台被清理,主程序唤醒该插件
     */
    protected void onWakeup() {
        PluginHelper.log("onWakeup 收到主程序唤醒的消息");
        hostConnection.bindHost();
    }

    protected abstract PluginAppInterface getAppInterface();

    public HostConnection getHostConnection() {
        return hostConnection;
    }
}
