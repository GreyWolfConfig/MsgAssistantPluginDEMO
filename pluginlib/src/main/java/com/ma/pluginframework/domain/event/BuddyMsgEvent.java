package com.ma.pluginframework.domain.event;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 好友消息
 */
public class BuddyMsgEvent extends PluginMsgEvent implements Parcelable {
    /**
     * 0文本 1图像
     */
    private int type;

    public BuddyMsgEvent(long currentUin, String extra, long msgId, long senderUin, String senderNick, long toUin, long time, String content, int type) {
        super(currentUin, extra, msgId, senderUin, senderNick, toUin, time, content);
        this.type = type;
    }

    protected BuddyMsgEvent(Parcel in) {
        super(in);
        type = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(type);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BuddyMsgEvent> CREATOR = new Creator<BuddyMsgEvent>() {
        @Override
        public BuddyMsgEvent createFromParcel(Parcel in) {
            return new BuddyMsgEvent(in);
        }

        @Override
        public BuddyMsgEvent[] newArray(int size) {
            return new BuddyMsgEvent[size];
        }
    };

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "FriendMsgEvent{" +
                "type=" + (type == 0 ? "文本" : "图片") +
                "} " + super.toString();
    }
}
