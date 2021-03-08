package com.ma.pluginframework.domain;

import android.os.Parcel;
import android.os.Parcelable;

public abstract class HostContact implements Parcelable {
    /**
     * 当前是否被选中开启机器人
     */
    private boolean robotOpen;
    /**
     * 当前是否被选中发送
     */
    private boolean sendOpen;
    /**
     * 号码
     */
    private long uin;
    /**
     * 名字
     */
    private String name;

    /**
     * 备注
     */
    private String mark;


    /**
     * 附加数据
     */
    private String extra;

    public HostContact(boolean robotOpen, boolean sendOpen, long uin, String name, String mark, String extra) {
        this.robotOpen = robotOpen;
        this.sendOpen = sendOpen;
        this.uin = uin;
        this.name = name;
        this.mark = mark;
        this.extra = extra;
    }

    protected HostContact(Parcel in) {
        robotOpen = in.readByte() != 0;
        sendOpen = in.readByte() != 0;
        uin = in.readLong();
        name = in.readString();
        mark = in.readString();
        extra = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (robotOpen ? 1 : 0));
        dest.writeByte((byte) (sendOpen ? 1 : 0));
        dest.writeLong(uin);
        dest.writeString(name);
        dest.writeString(mark);
        dest.writeString(extra);
    }

    @Override
    public int describeContents() {
        return 0;
    }

//    public static final Creator<HostContact> CREATOR = new Creator<HostContact>() {
//        @Override
//        public HostContact createFromParcel(Parcel in) {
//            return new HostContact(in);
//        }
//
//        @Override
//        public HostContact[] newArray(int size) {
//            return new HostContact[size];
//        }
//    };

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public long getUin() {
        return uin;
    }

    public void setUin(long uin) {
        this.uin = uin;
    }

    public boolean isRobotOpen() {
        return robotOpen;
    }

    public void setRobotOpen(boolean robotOpen) {
        this.robotOpen = robotOpen;
    }

    public boolean isSendOpen() {
        return sendOpen;
    }

    public void setSendOpen(boolean sendOpen) {
        this.sendOpen = sendOpen;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "HostContact{" +
                "robotOpen=" + robotOpen +
                ", sendOpen=" + sendOpen +
                ", uin=" + uin +
                ", name='" + name + '\'' +
                ", mark='" + mark + '\'' +
                ", extra='" + extra + '\'' +
                '}';
    }
}
