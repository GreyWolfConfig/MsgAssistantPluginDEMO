package com.ma.pluginframework.domain.event;

import android.os.Parcel;
import android.os.Parcelable;

public class PluginAtEntity implements Parcelable {
    /**
     * 要艾特的人马甲,可以随便写
     */
    private String mark;
    /**
     * 要艾特人的QQ
     */
    private long uin;


    private String extra;

    public PluginAtEntity(String mark, long uin, String extra) {
        this.mark = mark;
        this.uin = uin;
        this.extra = extra;
    }

    protected PluginAtEntity(Parcel in) {
        mark = in.readString();
        uin = in.readLong();
        extra = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mark);
        dest.writeLong(uin);
        dest.writeString(extra);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PluginAtEntity> CREATOR = new Creator<PluginAtEntity>() {
        @Override
        public PluginAtEntity createFromParcel(Parcel in) {
            return new PluginAtEntity(in);
        }

        @Override
        public PluginAtEntity[] newArray(int size) {
            return new PluginAtEntity[size];
        }
    };

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public long getUin() {
        return uin;
    }

    public void setUin(long uin) {
        this.uin = uin;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    @Override
    public String toString() {
        return "PluginAtEntity{" +
                "mark='" + mark + '\'' +
                ", uin=" + uin +
                '}';
    }
}
