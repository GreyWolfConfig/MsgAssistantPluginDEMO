package com.ma.framework.demo;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.ma.pluginframework.PluginAppInterface;
import com.ma.pluginframework.domain.PluginInfo;
import com.ma.pluginframework.service.AbstractAppInterface;
import com.ma.pluginframework.service.WakeupService;

public class MsgProcessingService extends WakeupService {
    public MsgProcessingService() {
    }

    public class LocalBinder extends Binder {
        MsgProcessingService getService() {
            return MsgProcessingService.this;
        }
    }

    private final IBinder binder = new LocalBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    protected PluginAppInterface getAppInterface() {
        return new AppInterface(this, new PluginInfo("", "", "", ""));
    }

    /**
     * 这个类是监听主程序的各种消息
     */
    class AppInterface extends AbstractAppInterface {

        protected AppInterface(Context context, PluginInfo pluginInfo) {
            super(context, pluginInfo);
        }
    }
}
