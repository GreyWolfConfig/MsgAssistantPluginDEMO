package com.ma.pluginframework.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * 一个账号的数据
 */
public class AccountData implements Parcelable {

    /**
     * 昵称
     */
    private String name;

    /**
     * QQ
     */
    private long uin;

    /**
     * Cookie
     */
    private List<AccountCookie> cookies;

    private boolean isAndroidQQ;

    /**
     * 全部功能是否可用
     */
    private boolean unlimitedUser;

    /**
     * 授权信息
     */
    private String authorizationMessage;

    /**
     * 好友列表
     */
    private List<Buddy> buddies;

    /**
     * 群列表
     */
    private List<Troop> troops;


    private List<BuddyGroup> buddyGroups;
    private String extra;

    public AccountData(String name, long uin) {
        this.name = name;
        this.uin = uin;
        this.buddies = new ArrayList<>();
        this.troops = new ArrayList<>();
        this.cookies = new ArrayList<>();
        this.buddyGroups = new ArrayList<>();
    }

    protected AccountData(Parcel in) {
        name = in.readString();
        uin = in.readLong();
        cookies = in.createTypedArrayList(AccountCookie.CREATOR);
        isAndroidQQ = in.readByte() != 0;
        unlimitedUser = in.readByte() != 0;
        authorizationMessage = in.readString();
        buddies = in.createTypedArrayList(Buddy.CREATOR);
        troops = in.createTypedArrayList(Troop.CREATOR);
        buddyGroups = in.createTypedArrayList(BuddyGroup.CREATOR);
        extra = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeLong(uin);
        dest.writeTypedList(cookies);
        dest.writeByte((byte) (isAndroidQQ ? 1 : 0));
        dest.writeByte((byte) (unlimitedUser ? 1 : 0));
        dest.writeString(authorizationMessage);
        dest.writeTypedList(buddies);
        dest.writeTypedList(troops);
        dest.writeTypedList(buddyGroups);
        dest.writeString(extra);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AccountData> CREATOR = new Creator<AccountData>() {
        @Override
        public AccountData createFromParcel(Parcel in) {
            return new AccountData(in);
        }

        @Override
        public AccountData[] newArray(int size) {
            return new AccountData[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getUin() {
        return uin;
    }

    public void setUin(long uin) {
        this.uin = uin;
    }

    public List<AccountCookie> getCookies() {
        return cookies;
    }

    public void setCookies(List<AccountCookie> cookies) {
        this.cookies = cookies;
    }

    public List<Buddy> getBuddies() {
        return buddies;
    }

    public void setBuddies(List<Buddy> buddies) {
        this.buddies = buddies;
    }

    public List<Troop> getTroops() {
        return troops;
    }

    public void setTroops(List<Troop> troops) {
        this.troops = troops;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public boolean isAndroidQQ() {
        return isAndroidQQ;
    }

    public void setAndroidQQ(boolean androidQQ) {
        isAndroidQQ = androidQQ;
    }

    public boolean isUnlimitedUser() {
        return unlimitedUser;
    }

    public void setUnlimitedUser(boolean unlimitedUser) {
        this.unlimitedUser = unlimitedUser;
    }

    public String getAuthorizationMessage() {
        return authorizationMessage;
    }

    public void setAuthorizationMessage(String authorizationMessage) {
        this.authorizationMessage = authorizationMessage;
    }

    public List<BuddyGroup> getBuddyGroups() {
        return buddyGroups;
    }

    public void setBuddyGroups(List<BuddyGroup> buddyGroups) {
        this.buddyGroups = buddyGroups;
    }

    @Override
    public String toString() {
        return "AccountData{" +
                "name='" + name + '\'' +
                ", uin=" + uin +
                ", cookies=" + cookies +
                ", isAndroidQQ=" + isAndroidQQ +
                ", unlimitedUser=" + unlimitedUser +
                ", authorizationMessage='" + authorizationMessage + '\'' +
                ", buddies=" + buddies +
                ", troops=" + troops +
                ", extra='" + extra + '\'' +
                '}';
    }
}
