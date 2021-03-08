package com.ma.pluginframework.service;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.RemoteException;

import com.ma.pluginframework.PluginAppInterface;
import com.ma.pluginframework.PluginHelper;
import com.ma.pluginframework.domain.HostData;
import com.ma.pluginframework.domain.PluginInfo;
import com.ma.pluginframework.domain.event.BuddyMsgEvent;
import com.ma.pluginframework.domain.event.TroopMemberChangeEvent;
import com.ma.pluginframework.domain.event.TroopMsgEvent;
import com.ma.pluginframework.domain.fav.PluginFavItem;
import com.ma.pluginframework.domain.fav.PluginPraiseItem;
import com.ma.pluginframework.domain.fav.PluginTagQueryItem;

import java.util.List;

public abstract class AbstractAppInterface extends PluginAppInterface.Stub {
    private final Context context;
    private final PluginInfo pluginInfo;

    protected AbstractAppInterface(Context context, PluginInfo pluginInfo) {
        this.context = context;
        this.pluginInfo = pluginInfo;
        this.pluginInfo.setPn(context.getPackageName());

        PackageManager pm = context.getPackageManager();
        PackageInfo info = null;

        try {
            info = pm.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            PluginHelper.log("error", e);
        }
        this.pluginInfo.setName(info.applicationInfo.loadLabel(pm).toString());
        this.pluginInfo.setVn(info.versionName);
        this.pluginInfo.setVc(info.versionCode);
        this.pluginInfo.setMinPluginSdkVersion(PluginHelper.PLUGIN_SDK_VERSION);

    }

    @Override
    public boolean ping() throws RemoteException {
        PluginHelper.log("received PING");
        return true;
    }

    @Override
    public PluginInfo getInfo() throws RemoteException {
        return pluginInfo;
    }

    /**
     * 收到好友消息
     *
     * @param uin
     * @param event
     * @return
     * @throws RemoteException
     */
    @Override
    public int onBuddyMessage(long uin, BuddyMsgEvent event) throws RemoteException {
        PluginHelper.log("onBuddyMessage uin:" + uin + " " + event);
        return 0;
    }

    /**
     * 收到群消息
     *
     * @param uin
     * @param event
     * @return
     * @throws RemoteException
     */
    @Override
    public int onTroopMessage(long uin, TroopMsgEvent event) throws RemoteException {
        PluginHelper.log("onTroopMessage uin:" + uin + " " + event);
        return 0;
    }

    /**
     * 群成员变动消息
     *
     * @param uin
     * @param event
     * @return
     * @throws RemoteException
     */
    @Override
    public int onTroopMemberChange(long uin, TroopMemberChangeEvent event) throws RemoteException {
        PluginHelper.log("onTroopMemberChange uin:" + uin + " " + event);

        return 0;
    }

    /**
     * 接收数据,异步获取主程序数据返回的
     *
     * @param hostData
     * @return
     * @throws RemoteException
     */
    @Override
    public int onReceiveData(HostData hostData) throws RemoteException {
        PluginHelper.log("onReceiveData " + hostData);
        return 0;
    }

    /**
     * 数据变动
     *
     * @param uin
     * @param type
     * @param l1
     * @param l2
     * @return
     * @throws RemoteException
     */
    @Override
    public int notifyDataChanged(long uin, int type, long l1, long l2) throws RemoteException {
        //数据改变,需要重新获取数据 type 0主界面刷新 1获取群成员完成 2下线 3用户切换QQ
        //1 读取群成员完成 l1=群号
        StringBuffer sb = new StringBuffer();
        switch (type) {
            case 0:
                sb.append("QQ:").append(uin).append("主界面刷新");
                break;
            case 1:
                sb.append("QQ:").append(uin).append("获取群成员完成 群:").append(l1);
                break;
            case 2:
                sb.append("QQ:").append(uin).append("已经下线");
                break;
            case 3:
                sb.append("用户已经切换到QQ:").append(uin);
                break;
        }
        PluginHelper.log(sb.toString() + " notifyDataChanged uin:" + uin + " " + type + " " + l1 + " " + l2);
        return 0;
    }


    /**
     * 已赞列表
     *
     * @param uin
     * @param list
     * @return
     * @throws RemoteException
     */
    @Override
    public int onReqFavList(long uin, List<PluginFavItem> list) throws RemoteException {
        PluginHelper.log("onReqFavList uin:" + uin + " " + list);
        return 0;
    }

    /**
     * 点赞回调
     *
     * @param uin
     * @param v
     * @return
     * @throws RemoteException
     */
    @Override
    public int onReqFav(long uin, long v) throws RemoteException {
        PluginHelper.log("onReqFavList uin:" + uin + " " + v);
        return 0;
    }

    /**
     * 个性标签
     *
     * @param uin
     * @param list
     * @return
     * @throws RemoteException
     */
    @Override
    public int onReqTagList(long uin, List<PluginTagQueryItem> list) throws RemoteException {
        PluginHelper.log("onReqFavList uin:" + uin + " " + list);
        return 0;
    }

    /**
     * 获赞排行榜
     *
     * @param uin
     * @param list
     * @return
     * @throws RemoteException
     */
    @Override
    public int onReceivePraiseItem(long uin, List<PluginPraiseItem> list) throws RemoteException {
        PluginHelper.log("onReqFavList uin:" + uin + " " + list);
        return 0;
    }

    /**
     * 谁赞过我
     *
     * @param uin
     * @param list
     * @return
     * @throws RemoteException
     */
    @Override
    public int onReceiveVoterList(long uin, List<PluginFavItem> list) throws RemoteException {
        PluginHelper.log("onReqFavList uin:" + uin + " " + list);
        return 0;
    }
}
