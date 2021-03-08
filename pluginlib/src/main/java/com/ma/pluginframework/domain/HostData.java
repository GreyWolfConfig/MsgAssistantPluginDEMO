package com.ma.pluginframework.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * 主程序数据
 */
public class HostData implements Parcelable {

    /**
     * 当前主程序界面使用的QQ
     */
    private long activeUin;

    /**
     * 所有登录的QQ数据
     */
    private List<AccountData> accountDataList;

    /**
     * 附加数据
     */
    private String extra;

    public HostData(long activeUin, List<AccountData> accountDataList, String extra) {
        this.activeUin = activeUin;
        this.accountDataList = accountDataList;
        this.extra = extra;
    }

    protected HostData(Parcel in) {
        activeUin = in.readLong();
        accountDataList = in.createTypedArrayList(AccountData.CREATOR);
        extra = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(activeUin);
        dest.writeTypedList(accountDataList);
        dest.writeString(extra);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<HostData> CREATOR = new Creator<HostData>() {
        @Override
        public HostData createFromParcel(Parcel in) {
            return new HostData(in);
        }

        @Override
        public HostData[] newArray(int size) {
            return new HostData[size];
        }
    };

    public long getActiveUin() {
        return activeUin;
    }

    public void setActiveUin(long activeUin) {
        this.activeUin = activeUin;
    }

    public List<AccountData> getAccountDataList() {
        return accountDataList;
    }

    public void setAccountDataList(List<AccountData> accountDataList) {
        this.accountDataList = accountDataList;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    @Override
    public String toString() {
        return "HostData{" +
                "activeUin=" + activeUin +
                ", accountDataList=" + accountDataList +
                ", extra='" + extra + '\'' +
                '}';
    }
}
