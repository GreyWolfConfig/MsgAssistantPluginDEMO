package com.ma.pluginframework.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import com.ma.pluginframework.HostAppInterface;
import com.ma.pluginframework.PluginAppInterface;
import com.ma.pluginframework.PluginHelper;
import com.ma.pluginframework.domain.AccountData;
import com.ma.pluginframework.domain.Buddy;
import com.ma.pluginframework.domain.HostData;
import com.ma.pluginframework.domain.HostInfo;
import com.ma.pluginframework.domain.Troop;
import com.ma.pluginframework.domain.richmsg.PluginMsg;

import org.json.JSONException;

/**
 * 主程序支持多账号
 * 每个接口都需要activeuin 即要操作的账号 可以传 com.ma.pluginframework.PluginHelper#DEFAULT_ACTIVE_UIN 即当前用户选择的账号
 */
public class HostConnection implements ServiceConnection {

    private final Context context;
    private final PluginAppInterface pluginAppInterface;
    private final String packageName;
    private StateListener stateListener;

    private HostAppInterface hostAppInterface;
    private HostInfo hostInfo;
    /**
     * 0 未连接
     * 1 ready
     * -1 error
     */
    private int state = 0;

    public HostConnection(Context context, PluginAppInterface pluginAppInterface, StateListener stateListener) {
        this.context = context;
        this.pluginAppInterface = pluginAppInterface;
        this.packageName = context.getPackageName();
        this.stateListener = stateListener;
    }

    public HostConnection(Context context, PluginAppInterface pluginAppInterface) {
        this(context, pluginAppInterface, null);
        this.stateListener = new DefaultStateListener();
    }


    public class DefaultStateListener implements StateListener {

        @Override
        public void onStateChanged(int state) {
            PluginHelper.log("onStateChanged " + state);
        }
    }

    public boolean bindHost() {
        Intent i = new Intent();
        ComponentName component = new ComponentName(PluginHelper.SERVER_PN, PluginHelper.SERVER_SVC_NAME);
        i.setComponent(component);
        return context.bindService(i, this, Context.BIND_AUTO_CREATE);
    }


    public void unbind() {
        try {
            if (this.hostAppInterface != null) {
                this.hostAppInterface.unregister(packageName);
            }
        } catch (Exception e) {
            PluginHelper.log("unregister error", e);

        }
        try {
            context.unbindService(this);
        } catch (Exception e) {
            PluginHelper.log("unbind error", e);
        }
        this.state = 0;
    }

    public PluginAppInterface getPluginAppInterface() {
        return pluginAppInterface;
    }

    public HostAppInterface getHostAppInterface() {
        return hostAppInterface;
    }

    public HostInfo getHostInfo() {
        return hostInfo;
    }

    public int getState() {
        return state;
    }

    /**
     * Called when a connection to the Service has been established, with
     * the {@link IBinder} of the communication channel to the
     * Service.
     *
     * @param name    The concrete component name of the service that has
     *                been connected.
     * @param service The IBinder of the Service's communication channel,
     */
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        this.hostAppInterface = HostAppInterface.Stub.asInterface(service);
        try {
            int r = this.hostAppInterface.register(packageName, this.pluginAppInterface);
            this.hostInfo = this.hostAppInterface.getInfo(packageName);
            if (r == 0) {
                this.state = 1;
                this.stateListener.onStateChanged(this.state);
                PluginHelper.log("host connected " + hostInfo);
            } else {
                this.state = r;
                this.stateListener.onStateChanged(this.state);
                PluginHelper.log("host register error " + this.state);
            }
        } catch (RemoteException e) {
            this.state = -1;
            PluginHelper.log("onServiceConnected error", e);
            this.stateListener.onStateChanged(this.state);
        }
    }

    /**
     * Called when a connection to the Service has been lost.  This typically
     * happens when the process hosting the service has crashed or been killed.
     * This does <em>not</em> remove the ServiceConnection itself -- this
     * binding to the service will remain active, and you will receive a call
     * to {@link #onServiceConnected} when the Service is next running.
     *
     * @param name The concrete component name of the service whose
     *             connection has been lost.
     */
    @Override
    public void onServiceDisconnected(ComponentName name) {
        this.state = -1;
        this.stateListener.onStateChanged(this.state);
    }

    /**
     * Called when the binding to this connection is dead.  This means the
     * interface will never receive another connection.  The application will
     * need to unbind and rebind the connection to activate it again.  This may
     * happen, for example, if the application hosting the service it is bound to
     * has been updated.
     *
     * @param name The concrete component name of the service whose
     *             connection is dead.
     */
    @Override
    public void onBindingDied(ComponentName name) {
        PluginHelper.log("onBindingDied " + name);
        this.state = -1;
        this.stateListener.onStateChanged(this.state);
    }

    /**
     * @return 0 OK -2 用户未打开本插件 -1需要重新连接主程序
     * @throws RemoteException
     */
    public int ping() throws RemoteException {
        return getHostAppInterface().ping(packageName);
    }


    /**
     * 获取主程序信息
     *
     * @return
     * @throws RemoteException
     */
    public HostInfo getInfo() throws RemoteException {
        return getHostAppInterface().getInfo(packageName);
    }


    /**
     * 获取主程序数据 异步
     *
     * @return
     * @throws RemoteException
     */
    public int obtainHostData() throws RemoteException {
        return getHostAppInterface().obtainHostData(packageName);
    }

    /**
     * 获取主程序数据 同步
     *
     * @return
     * @throws RemoteException
     */
    public HostData obtainHostDataSync() throws RemoteException {
        return getHostAppInterface().obtainHostDataSync(packageName);
    }

    /**
     * 获取群信息
     *
     * @param activeUin
     * @param target
     * @return
     * @throws RemoteException
     */
    public Troop getTroop(long activeUin, long target) throws RemoteException {
        return getHostAppInterface().getTroop(packageName, activeUin, target);
    }

    /**
     * 获取好友信息
     *
     * @param activeUin
     * @param target
     * @return
     * @throws RemoteException
     */
    public Buddy getBuddy(long activeUin, long target) throws RemoteException {
        return getHostAppInterface().getBuddy(packageName, activeUin, target);
    }

    /**
     * 获取账号信息
     *
     * @param activeUin
     * @return
     * @throws RemoteException
     */
    public AccountData getAccountData(long activeUin) throws RemoteException {
        return getHostAppInterface().getAccountData(packageName, activeUin);
    }

    /**
     * 发送好友消息
     *
     * @param activeUin
     * @param target
     * @param msg
     * @return
     * @throws RemoteException
     * @throws JSONException
     */
    public int sendBuddyMessage(long activeUin, long target, PluginMsg msg) throws RemoteException, JSONException {
        msg.processFileToUri(null, context);
        return getHostAppInterface().sendBuddyMessage(packageName, activeUin, target, msg.toJSONObject().toString());
    }

    /**
     * 发送群消息
     *
     * @param activeUin
     * @param target
     * @param msg
     * @return
     * @throws RemoteException
     * @throws JSONException
     */
    public int sendTroopMessage(long activeUin, long target, PluginMsg msg) throws RemoteException, JSONException {
        msg.processFileToUri(null, context);
        return getHostAppInterface().sendTroopMessage(packageName, activeUin, target, msg.toJSONObject().toString());
    }

    /**
     * 发送群XML
     *
     * @param activeUin
     * @param target
     * @param xml
     * @return
     * @throws RemoteException
     */
    public int sendTroopXml(long activeUin, long target, String xml) throws RemoteException {
        return getHostAppInterface().sendTroopXml(packageName, activeUin, target, xml);
    }

    /**
     * 发送群 xml 或 JSON
     *
     * @param activeUin
     * @param target
     * @param content
     * @param serviceId XML 一般为 60, JSON 一般为 1
     * @param type      0 xml 1json
     * @return
     * @throws RemoteException
     */
    public int sendTroopAdvanced(long activeUin, long target, String content, int serviceId, int type) throws RemoteException {
        return getHostAppInterface().sendTroopAdvanced(packageName, activeUin, target, content, serviceId, type);
    }

    /**
     * 发送群JSON
     *
     * @param activeUin
     * @param target
     * @param json
     * @return
     * @throws RemoteException
     */
    public int sendTroopJson(long activeUin, long target, String json) throws RemoteException {
        return sendTroopAdvanced(activeUin, target, json, 1, 1);
    }

    /**
     * 好友戳一戳
     *
     * @param activeUin
     * @param target
     * @return
     * @throws RemoteException
     */
    public int sendBuddyNudge(long activeUin, long target) throws RemoteException {
        return getHostAppInterface().sendBuddyNudge(packageName, activeUin, target);
    }

    /**
     * 群 戳一戳
     *
     * @param activeUin
     * @param guin
     * @param target
     * @return
     * @throws RemoteException
     */
    public int sendTroopNudge(long activeUin, long guin, long target) throws RemoteException {
        return getHostAppInterface().sendTroopNudge(packageName, activeUin, guin, target);

    }


    /**
     * 发送群成员消息
     *
     * @param activeUin
     * @param guin
     * @param target
     * @param txt
     * @return
     * @throws RemoteException
     */
    public int sendTroopMemberText(long activeUin, long guin, long target, String txt) throws RemoteException {
        return getHostAppInterface().sendTroopMemberText(packageName, activeUin, guin, target, txt);
    }

    /**
     * 退群
     *
     * @param activeUin
     * @param target
     * @return
     * @throws RemoteException
     */
    public int leaveTroop(long activeUin, long target) throws RemoteException {
        return getHostAppInterface().leaveTroop(packageName, activeUin, target);
    }

    /**
     * 删除好友
     *
     * @param activeUin
     * @param target
     * @return
     * @throws RemoteException
     */
    public int deleteBuddy(long activeUin, long target) throws RemoteException {
        return getHostAppInterface().deleteBuddy(packageName, activeUin, target);
    }

    /**
     * T群成员
     *
     * @param activeUin
     * @param guin
     * @param targetUin
     * @return
     * @throws RemoteException
     */
    public int removeTroopMember(long activeUin, long guin, long targetUin) throws RemoteException {
        return getHostAppInterface().removeTroopMember(packageName, activeUin, guin, targetUin);
    }

    /**
     * 禁言群
     *
     * @param activeUin
     * @param guin
     * @param target    0是全员禁言
     * @param time
     * @return
     * @throws RemoteException
     */
    public int shutupTroop(long activeUin, long guin, long target, int time) throws RemoteException {
        return getHostAppInterface().shutupTroop(packageName, activeUin, guin, target, time);
    }

    /**
     * 解除禁言群
     *
     * @param activeUin
     * @param guin
     * @param target
     * @return
     * @throws RemoteException
     */
    public int restoreShutupTroop(long activeUin, long guin, long target) throws RemoteException {
        return getHostAppInterface().restoreShutupTroop(packageName, activeUin, guin, target);
    }

    /**
     * 改群成员马甲
     *
     * @param activeUin
     * @param guin
     * @param target
     * @param mark
     * @return
     * @throws RemoteException
     */
    public int modifyTroopMemberMark(long activeUin, long guin, long target, String mark) throws RemoteException {
        return getHostAppInterface().modifyTroopMemberMark(packageName, activeUin, guin, target, mark);
    }

    /**
     * 获取群成员 异步
     *
     * @param activeUin
     * @param guin
     * @return
     * @throws RemoteException
     */
    public int getTroopMember(long activeUin, long guin) throws RemoteException {
        return getHostAppInterface().getTroopMember(packageName, activeUin, guin);
    }

    /**
     * 撤回群消息
     *
     * @param activeUin
     * @param guin
     * @param msgId
     * @param rand3
     * @return
     * @throws RemoteException
     */
    public int withDrawMsg(long activeUin, long guin, long msgId, long rand3) throws RemoteException {
        return getHostAppInterface().withDrawMsg(packageName, activeUin, guin, msgId, rand3);
    }

    /**
     * 点赞
     *
     * @param activeUin
     * @param target
     * @param count
     * @param type      0旧的 1新 2new
     * @return
     * @throws RemoteException
     */
    public int reqFavorite(long activeUin, long target, int count, int type) throws RemoteException {
        return getHostAppInterface().reqFavorite(packageName, activeUin, target, count, type);
    }

    /**
     * 请求已赞列表
     *
     * @param activeUin
     * @param fetchAll
     * @return
     * @throws RemoteException
     */
    public int reqFavoriteList(long activeUin, boolean fetchAll) throws RemoteException {
        return getHostAppInterface().reqFavoriteList(packageName, activeUin, fetchAll);
    }

    /**
     * 是否允许附近人点赞开关
     *
     * @param activeUin
     * @param open
     * @return
     * @throws RemoteException
     */
    public int cardSwitch(long activeUin, boolean open) throws RemoteException {
        return getHostAppInterface().cardSwitch(packageName, activeUin, open);
    }

    /**
     * 收到赞是否通知我
     *
     * @param activeUin
     * @param open
     * @return
     * @throws RemoteException
     */
    public int cardNotifySwitch(long activeUin, boolean open) throws RemoteException {
        return getHostAppInterface().cardNotifySwitch(packageName, activeUin, open);
    }

    /**
     * 获赞排行榜
     *
     * @param activeUin
     * @param fetchAll
     * @return
     * @throws RemoteException
     */
    public int reqPraiseRank(long activeUin, boolean fetchAll) throws RemoteException {
        return getHostAppInterface().reqPraiseRank(packageName, activeUin, fetchAll);
    }

    /**
     * 查询个性标签
     *
     * @param activeUin
     * @param uin
     * @return
     * @throws RemoteException
     */
    public int queryTag(long activeUin, long uin) throws RemoteException {
        return getHostAppInterface().queryTag(packageName, activeUin, uin);
    }

    /**
     * 赞个性标签
     *
     * @param activeUin
     * @param targetUin
     * @param tagId
     * @return
     * @throws RemoteException
     */
    public int reqFavTag(long activeUin, long targetUin, long tagId) throws RemoteException {
        return getHostAppInterface().reqFavTag(packageName, activeUin, targetUin, tagId);
    }

    /**
     * 谁赞过我
     *
     * @param activeUin
     * @param fetchAll
     * @return
     * @throws RemoteException
     */
    public int reqVoterList(long activeUin, boolean fetchAll) throws RemoteException {
        return getHostAppInterface().reqVoterList(packageName, activeUin, fetchAll);
    }

    /**
     * 查询用户是否开启了某个群|好友的机器人功能
     *
     * @param activeUin
     * @param type
     * @param uin
     * @return
     * @throws RemoteException
     */
    public boolean queryRobotOpen(long activeUin, int type, long uin) throws RemoteException {
        return getHostAppInterface().queryRobotOpen(packageName, activeUin, type, uin);
    }

    /**
     * @param activeUin
     * @param type
     * @param uin
     * @return
     * @throws RemoteException
     */
    public boolean querySendOpen(long activeUin, int type, long uin) throws RemoteException {
        return getHostAppInterface().querySendOpen(packageName, activeUin, type, uin);
    }

    public interface StateListener {
        /**
         * * 0 未连接
         * * 1 ready
         * * -1 error
         * -2 插件未开启
         * -6 在黑名单
         * -7 未在白名单
         *
         * @param state
         */
        void onStateChanged(int state);
    }
}
