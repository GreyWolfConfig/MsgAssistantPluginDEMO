// IPluginApp.aidl
package com.ma.pluginframework;

import com.ma.pluginframework.domain.PluginInfo;
import com.ma.pluginframework.domain.event.BuddyMsgEvent;
import com.ma.pluginframework.domain.event.TroopMemberChangeEvent;
import com.ma.pluginframework.domain.event.TroopMsgEvent;

import com.ma.pluginframework.domain.fav.PluginFavItem;
import com.ma.pluginframework.domain.fav.PluginPraiseItem;
import com.ma.pluginframework.domain.fav.PluginRecentTagFav;
import com.ma.pluginframework.domain.fav.PluginTagQueryItem;

import com.ma.pluginframework.domain.HostData;
// 插件提供接口

interface PluginAppInterface {
    boolean ping();

    //获取插件信息
    PluginInfo getInfo();

    //好友消息
    int onBuddyMessage(long uin, in BuddyMsgEvent event);

    //群消息
    int onTroopMessage(long uin, in TroopMsgEvent event);

    //成员改变
    int onTroopMemberChange(long uin, in TroopMemberChangeEvent event);

    //接收主程序数据,可能是切换了QQ
    int onReceiveData(in HostData hostData);

    //数据改变,需要重新获取数据 type 0主界面刷新 1获取群成员完成 2下线 3用户切换QQ
    //1 读取群成员完成 l1=群号
    int notifyDataChanged(long uin,int type,long l1,long l2);

    //获取已赞列表回调
    int onReqFavList(long uin, in List<PluginFavItem> list);

    //赞目标回调
    int onReqFav(long uin, long v);

    //查询个性标签回调
    int onReqTagList(long uin, in List<PluginTagQueryItem>list);

    //查询获赞排行榜
    int onReceivePraiseItem(long uin, in List<PluginPraiseItem>list);

    //获取谁赞过我回调
    int onReceiveVoterList(long uin, in List<PluginFavItem>list);
}

