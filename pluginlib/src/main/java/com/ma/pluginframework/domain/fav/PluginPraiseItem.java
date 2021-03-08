package com.ma.pluginframework.domain.fav;

import android.os.Parcel;
import android.os.Parcelable;

public class PluginPraiseItem implements Parcelable {
    private long uin;
    private long todayCount;
    private long rank;
    private long totalCount;


    @Override
    public String toString() {
        return "QQ:" + getUin() + " 今天获赞:" + getTodayCount() + " 排名:" + getRank() + " 累计获赞:" + getTotalCount();

    }

    public PluginPraiseItem(long uin, long todayCount, long rank, long totalCount) {
        this.uin = uin;
        this.todayCount = todayCount;
        this.rank = rank;
        this.totalCount = totalCount;
    }

    protected PluginPraiseItem(Parcel in) {
        uin = in.readLong();
        todayCount = in.readLong();
        rank = in.readLong();
        totalCount = in.readLong();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(uin);
        dest.writeLong(todayCount);
        dest.writeLong(rank);
        dest.writeLong(totalCount);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PluginPraiseItem> CREATOR = new Creator<PluginPraiseItem>() {
        @Override
        public PluginPraiseItem createFromParcel(Parcel in) {
            return new PluginPraiseItem(in);
        }

        @Override
        public PluginPraiseItem[] newArray(int size) {
            return new PluginPraiseItem[size];
        }
    };

    public long getUin() {
        return uin;
    }

    public void setUin(long uin) {
        this.uin = uin;
    }

    public long getTodayCount() {
        return todayCount;
    }

    public void setTodayCount(long todayCount) {
        this.todayCount = todayCount;
    }

    public long getRank() {
        return rank;
    }

    public void setRank(long rank) {
        this.rank = rank;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }
}
