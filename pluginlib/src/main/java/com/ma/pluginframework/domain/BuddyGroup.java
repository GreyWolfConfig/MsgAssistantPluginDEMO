package com.ma.pluginframework.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class BuddyGroup implements Parcelable {
    private String name = "";
    private int index;//id
    private int count;//该分组下面的好友数量
    private int onlineFriendCount;
    private byte seqid;
    private int sqqOnLineCount;

    public BuddyGroup(String name, int index, int count, int onlineFriendCount, byte seqid, int sqqOnLineCount) {
        this.name = name;
        this.index = index;
        this.count = count;
        this.onlineFriendCount = onlineFriendCount;
        this.seqid = seqid;
        this.sqqOnLineCount = sqqOnLineCount;
    }

    protected BuddyGroup(Parcel in) {
        name = in.readString();
        index = in.readInt();
        count = in.readInt();
        onlineFriendCount = in.readInt();
        seqid = in.readByte();
        sqqOnLineCount = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(index);
        dest.writeInt(count);
        dest.writeInt(onlineFriendCount);
        dest.writeByte(seqid);
        dest.writeInt(sqqOnLineCount);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BuddyGroup> CREATOR = new Creator<BuddyGroup>() {
        @Override
        public BuddyGroup createFromParcel(Parcel in) {
            return new BuddyGroup(in);
        }

        @Override
        public BuddyGroup[] newArray(int size) {
            return new BuddyGroup[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getOnlineFriendCount() {
        return onlineFriendCount;
    }

    public void setOnlineFriendCount(int onlineFriendCount) {
        this.onlineFriendCount = onlineFriendCount;
    }

    public byte getSeqid() {
        return seqid;
    }

    public void setSeqid(byte seqid) {
        this.seqid = seqid;
    }

    public int getSqqOnLineCount() {
        return sqqOnLineCount;
    }

    public void setSqqOnLineCount(int sqqOnLineCount) {
        this.sqqOnLineCount = sqqOnLineCount;
    }

    @Override
    public String toString() {
        return "BuddyGroup{" +
                "name='" + name + '\'' +
                ", index=" + index +
                ", count=" + count +
                ", onlineFriendCount=" + onlineFriendCount +
                ", seqid=" + seqid +
                ", sqqOnLineCount=" + sqqOnLineCount +
                '}';
    }
}
