package com.ma.pluginframework.domain.event;

import android.os.Parcel;
import android.os.Parcelable;

public abstract class PluginMsgEvent extends PluginEvent implements Parcelable {

    private long msgId;

    /**
     * 发送者QQ
     */
    private long senderUin;
    /**
     * 发送者马甲
     */
    private String senderNick;
    /**
     * 收到消息的QQ|群
     */
    private long toUin;

    /***
     * 10位时间戳
     */
    private long time;

    private String content;

    public PluginMsgEvent(long currentUin, String extra, long msgId, long senderUin, String senderNick, long toUin, long time, String content) {
        super(currentUin, extra);
        this.msgId = msgId;
        this.senderUin = senderUin;
        this.senderNick = senderNick;
        this.toUin = toUin;
        this.time = time;
        this.content = content;
    }

    protected PluginMsgEvent(Parcel in) {
        super(in);
        msgId = in.readLong();
        senderUin = in.readLong();
        senderNick = in.readString();
        toUin = in.readLong();
        time = in.readLong();
        content = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeLong(msgId);
        dest.writeLong(senderUin);
        dest.writeString(senderNick);
        dest.writeLong(toUin);
        dest.writeLong(time);
        dest.writeString(content);
    }

    @Override
    public int describeContents() {
        return 0;
    }

//    public static final Creator<PluginMsgEvent> CREATOR = new Creator<PluginMsgEvent>() {
//        @Override
//        public PluginMsgEvent createFromParcel(Parcel in) {
//            return new PluginMsgEvent(in);
//        }
//
//        @Override
//        public PluginMsgEvent[] newArray(int size) {
//            return new PluginMsgEvent[size];
//        }
//    };

    public long getMsgId() {
        return msgId;
    }

    public void setMsgId(long msgId) {
        this.msgId = msgId;
    }

    public long getSenderUin() {
        return senderUin;
    }

    public void setSenderUin(long senderUin) {
        this.senderUin = senderUin;
    }

    public String getSenderNick() {
        return senderNick;
    }

    public void setSenderNick(String senderNick) {
        this.senderNick = senderNick;
    }

    public long getToUin() {
        return toUin;
    }

    public void setToUin(long toUin) {
        this.toUin = toUin;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "PluginMsgEvent{" +
                "msgId=" + msgId +
                ", senderUin=" + senderUin +
                ", senderNick='" + senderNick + '\'' +
                ", toUin=" + toUin +
                ", time=" + time +
                ", content='" + content + '\'' +
                "} " + super.toString();
    }
}
