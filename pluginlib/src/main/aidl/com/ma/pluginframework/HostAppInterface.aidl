// IHostApp.aidl
package com.ma.pluginframework;

import com.ma.pluginframework.PluginAppInterface;
import com.ma.pluginframework.domain.PluginInfo;
import com.ma.pluginframework.domain.HostInfo;
import com.ma.pluginframework.domain.HostData;
import com.ma.pluginframework.domain.Troop;
import com.ma.pluginframework.domain.Buddy;
import com.ma.pluginframework.domain.AccountData;


// 主程序提供接口

interface HostAppInterface {
    int ping(String client);

    //获取主程序信息
    HostInfo getInfo(String client);

    //注册回调
    int register(String client,PluginAppInterface plugin);
    //注销回调
    int unregister(String client);

    //获取主程序数据
    int obtainHostData(String client);

    //同步获取主程序数据 可能会延迟
    HostData obtainHostDataSync(String client);

    //获取群
    Troop getTroop(String client,long activeUin,long target);

    //获取好友
    Buddy getBuddy(String client,long activeUin,long target);

    //获取一个账号的数据
    AccountData getAccountData(String client,long activeUin);

    //发送好友消息
    int sendBuddyMessage(String client,long activeUin,long target,String richMsg);

    //发送群消息
    int sendTroopMessage(String client,long activeUin,long target,String richMsg);

    //发送群xml
    int sendTroopXml(String client,long activeUin,long target,String xml);

    //发送群xml 和 json
    //     * @param serviceId XML 一般为 60, JSON 一般为 1
          //     * @param type      0 xml 1json
    int sendTroopAdvanced(String client,long activeUin,long target,String content, int serviceId, int type);

    //群 抖一抖
    int sendTroopNudge(String client,long activeUin,long guin,long target);

    //好友 抖一抖
    int sendBuddyNudge(String client,long activeUin,long target);

    //发送群成员文本
    int sendTroopMemberText(String client,long activeUin,long guin,long target,String txt);

    //退群
    int leaveTroop(String client,long activeUin,long target);

    //删好友
    int deleteBuddy(String client,long activeUin,long target);

    //删群成员
    int removeTroopMember(String client,long activeUin,long guin,long targetUin);

    //群禁言 target=0是全员禁言
    int shutupTroop(String client,long activeUin,long guin, long target, int time);

    //解除群禁言
    int restoreShutupTroop(String client,long activeUin,long guin, long target);

    //修改群成员马甲
    int modifyTroopMemberMark(String client,long activeUin,long guin, long target,String mark);

    //获取群成员
    int getTroopMember(String client,long activeUin,long guin);

    //撤回消息
    int withDrawMsg(String client,long activeUin,long guin,long msgId, long rand3);

    //点赞
    int reqFavorite(String client,long activeUin,long target, int count, int type);

    //请求已赞列表
    int reqFavoriteList(String client,long activeUin,boolean fetchAll);

    //是否允许附近人点赞
    int cardSwitch(String client,long activeUin,boolean open);

    //收到赞时是否通知我
    int cardNotifySwitch(String client,long activeUin,boolean open );

    //查询获赞排行
    int reqPraiseRank(String client,long activeUin,boolean fetchAll);

    //查询个性标签
    int queryTag(String client,long activeUin,long uin);

    //赞个性标签
    int reqFavTag(String client,long activeUin,long targetUin, long tagId);

    //获取谁赞过我
    int reqVoterList(String client,long activeUin,boolean fetchAll);

    //查询用户是否勾选了某个群开启机器人 type 0群 1好友
    boolean queryRobotOpen(String client,long activeUin,int type,long uin);

    //查询用户是否勾选了某个群开启群发 type 0群 1好友
    boolean querySendOpen(String client,long activeUin,int type,long uin);
}
