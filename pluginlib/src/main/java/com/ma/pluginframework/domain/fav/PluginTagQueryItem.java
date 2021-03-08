package com.ma.pluginframework.domain.fav;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取个性标签回调
 */
public class PluginTagQueryItem implements Parcelable {
    private long time;
    private String title;
    private long count;
    private long id;

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("时间:" + getTime() + " 标题:" + getTitle() + " 已赞数量:" + getCount() + " ID:" + getId());
        sb.append("\n最近赞该标签的人\n");
        for (PluginRecentTagFav pluginRecentTagFav : getPluginRecentTagFavs()) {
            sb.append(pluginRecentTagFav.toString()).append("\n");
        }
        return sb.toString();
    }

    public PluginTagQueryItem(long time, String title, long count, long id) {
        this.time = time;
        this.title = title;
        this.count = count;
        this.id = id;
    }

    private List<PluginRecentTagFav> pluginRecentTagFavs = new ArrayList<>();


    protected PluginTagQueryItem(Parcel in) {
        time = in.readLong();
        title = in.readString();
        count = in.readLong();
        id = in.readLong();
        pluginRecentTagFavs = in.createTypedArrayList(PluginRecentTagFav.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(time);
        dest.writeString(title);
        dest.writeLong(count);
        dest.writeLong(id);
        dest.writeTypedList(pluginRecentTagFavs);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PluginTagQueryItem> CREATOR = new Creator<PluginTagQueryItem>() {
        @Override
        public PluginTagQueryItem createFromParcel(Parcel in) {
            return new PluginTagQueryItem(in);
        }

        @Override
        public PluginTagQueryItem[] newArray(int size) {
            return new PluginTagQueryItem[size];
        }
    };

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<PluginRecentTagFav> getPluginRecentTagFavs() {
        return pluginRecentTagFavs;
    }

    public void setPluginRecentTagFavs(List<PluginRecentTagFav> pluginRecentTagFavs) {
        this.pluginRecentTagFavs = pluginRecentTagFavs;
    }
}
