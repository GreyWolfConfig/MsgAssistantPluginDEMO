package com.ma.pluginframework.domain.event;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * 群消息
 */
public class TroopMsgEvent extends PluginMsgEvent implements Parcelable {
    private String groupName;
    private List<PluginAtEntity> atList;
    private long rand3;
    /**
     * 0文本 1图片 2闪照 3xml 4文本撤回 5图片撤回 6 json
     */
    private int type;

    public TroopMsgEvent(long currentUin, String extra, long msgId, long senderUin, String senderNick, long toUin, long time, String content, String groupName, List<PluginAtEntity> atList, long rand3, int type) {
        super(currentUin, extra, msgId, senderUin, senderNick, toUin, time, content);
        this.groupName = groupName;
        this.atList = atList;
        this.rand3 = rand3;
        this.type = type;
    }

    protected TroopMsgEvent(Parcel in) {
        super(in);
        groupName = in.readString();
        atList = in.createTypedArrayList(PluginAtEntity.CREATOR);
        rand3 = in.readLong();
        type = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(groupName);
        dest.writeTypedList(atList);
        dest.writeLong(rand3);
        dest.writeInt(type);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TroopMsgEvent> CREATOR = new Creator<TroopMsgEvent>() {
        @Override
        public TroopMsgEvent createFromParcel(Parcel in) {
            return new TroopMsgEvent(in);
        }

        @Override
        public TroopMsgEvent[] newArray(int size) {
            return new TroopMsgEvent[size];
        }
    };

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<PluginAtEntity> getAtList() {
        return atList;
    }

    public void setAtList(List<PluginAtEntity> atList) {
        this.atList = atList;
    }

    public long getRand3() {
        return rand3;
    }

    public void setRand3(long rand3) {
        this.rand3 = rand3;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        //0文本 1图片 2闪照 3xml 4文本撤回 5图片撤回
        String tn = "";
        switch (type) {
            case 0:
                tn = "文本";
                break;
            case 1:
                tn = "图片";
                break;
            case 2:
                tn = "闪照";
                break;
            case 3:
                tn = "XML";
                break;
            case 4:
                tn = "文本撤回";
                break;
            case 5:
                tn = "图片撤回";
                break;
            case 6:
                tn = "JSON";
                break;
        }

        return "GroupMsgEvent{" +
                "groupName='" + groupName + '\'' +
                "type='" + tn + '\'' +
                ", atList=" + atList +
                ", rand3=" + rand3 +
                ", type=" + type +
                "} " + super.toString();
    }
}
