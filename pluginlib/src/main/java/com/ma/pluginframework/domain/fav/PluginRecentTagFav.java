package com.ma.pluginframework.domain.fav;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 最近赞该标签的人
 */
public class PluginRecentTagFav implements Parcelable {
    private long uin;
    private String nick;

    @Override
    public String toString() {
        return "QQ:" + getUin() + " nick:" + getNick();
    }

    public PluginRecentTagFav(long uin, String nick) {
        this.uin = uin;
        this.nick = nick;
    }

    protected PluginRecentTagFav(Parcel in) {
        uin = in.readLong();
        nick = in.readString();
    }

    public PluginRecentTagFav() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(uin);
        dest.writeString(nick);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PluginRecentTagFav> CREATOR = new Creator<PluginRecentTagFav>() {
        @Override
        public PluginRecentTagFav createFromParcel(Parcel in) {
            return new PluginRecentTagFav(in);
        }

        @Override
        public PluginRecentTagFav[] newArray(int size) {
            return new PluginRecentTagFav[size];
        }
    };

    public long getUin() {
        return uin;
    }

    public void setUin(long uin) {
        this.uin = uin;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }
}
