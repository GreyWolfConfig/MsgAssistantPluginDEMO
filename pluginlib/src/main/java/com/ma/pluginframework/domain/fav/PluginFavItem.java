package com.ma.pluginframework.domain.fav;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 已赞列表
 */
public class PluginFavItem implements Parcelable {
    private long uin;
    private long time;
    private String nick;
    private int count;

    public PluginFavItem() {
    }

    public PluginFavItem(long uin, long time, String nick, int count) {
        this.uin = uin;
        this.time = time;
        this.nick = nick;
        this.count = count;
    }

    protected PluginFavItem(Parcel in) {
        uin = in.readLong();
        time = in.readLong();
        nick = in.readString();
        count = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(uin);
        dest.writeLong(time);
        dest.writeString(nick);
        dest.writeInt(count);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PluginFavItem> CREATOR = new Creator<PluginFavItem>() {
        @Override
        public PluginFavItem createFromParcel(Parcel in) {
            return new PluginFavItem(in);
        }

        @Override
        public PluginFavItem[] newArray(int size) {
            return new PluginFavItem[size];
        }
    };

    public long getUin() {
        return uin;
    }

    public void setUin(long uin) {
        this.uin = uin;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "uin:" + uin + " time:" + time + " nick:" + nick + " count:" + count;
    }
}
