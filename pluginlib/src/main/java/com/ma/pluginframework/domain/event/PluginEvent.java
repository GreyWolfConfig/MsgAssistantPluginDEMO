package com.ma.pluginframework.domain.event;

import android.os.Parcel;
import android.os.Parcelable;

public abstract class PluginEvent implements Parcelable {
    /**
     * 收到消息的QQ
     */
    private long currentUin;

    private String extra;

    public PluginEvent(long currentUin, String extra) {
        this.currentUin = currentUin;
        this.extra = extra;
    }

    protected PluginEvent(Parcel in) {
        currentUin = in.readLong();
        extra = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(currentUin);
        dest.writeString(extra);
    }

    @Override
    public int describeContents() {
        return 0;
    }

//    public static final Creator<PluginEvent> CREATOR = new Creator<PluginEvent>() {
//        @Override
//        public PluginEvent createFromParcel(Parcel in) {
//            return new PluginEvent(in);
//        }
//
//        @Override
//        public PluginEvent[] newArray(int size) {
//            return new PluginEvent[size];
//        }
//    };

    public long getCurrentUin() {
        return currentUin;
    }

    public void setCurrentUin(long currentUin) {
        this.currentUin = currentUin;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    @Override
    public String toString() {
        return "PluginEvent{" +
                "currentUin=" + currentUin +
                ", extra='" + extra + '\'' +
                '}';
    }
}
